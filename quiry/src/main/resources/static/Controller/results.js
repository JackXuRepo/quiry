  angular.module('quiryApp').controller('resultsController', function($http, $scope, $log, StorageService) {
      $scope.currentPage = 1;
      $scope.userId = StorageService.getValue("userId");
      $scope.results = StorageService.getValue("results");
      $scope.maxSize = 5;
      $scope.sortKey;
      $scope.reverse;
      $scope.expandAll = false;
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(setChartData);

      $scope.clearSort = function() {
          $scope.sortKey = '';
          $scope.reverse = null;
      }

      $scope.sort = function(key) {
          $scope.sortKey = key;
          $scope.reverse = !$scope.reverse;
      }
      $scope.setPage = function(pageNo) {
          $scope.currentPage = pageNo;
      };

      $scope.getIcon = function(type) {

          var iconMap = {
                  "0": "fas fa-file-pdf pdf-col",
                  "1": "fas fa-file-alt txt-col",
                  "2":"fab fa-internet-explorer html-col"
                };
            return iconMap[type];
            
      }

      $scope.isInstructor = function(type){

          var map = {
                  "1": "",
                  "2": "fas fa-graduation-cap"
                };
            return map[type];
      }


      $scope.pageChanged = function() {
          $log.log('Page changed to: ' + $scope.currentPage);
          $log.log($scope.results);
      };

      $scope.filterContentType = function(input) {
            var map = {
                  "0": "Past Exam",
                  "1": "Notes",
                  "2": "Journal"
                };
            return map[input];
      };

      $scope.download = function(idFile){
          $http.get('/file/download', {
            responseType: 'arraybuffer',
            params: { fileId: idFile }
          })
          .then(
            function(response) {
              console.log(response);
              console.log(response.headers('Content-Type'));
              console.log(response.headers('File-Name'));
              
              var blob = new Blob([response.data], {type: response.headers('Content-Type')});
              saveAs(blob, response.headers('File-Name'));
            }
          )
          .catch(
            function(response) {
              alert("error");
            }
          );
      }

      $scope.signOut = function(){
        StorageService.removeValue("userId");
        $scope.userId = null;
      }

      function setChartData(){

        var results = StorageService.getValue("results");

        accountTypeHeaders = ['Student', 'Instructor'];
        accountTypeNumbers = [0, 0];
        fileTypeHeaders = ['PDF', 'TXT', 'HTML'];
        fileTypeNumbers = [0, 0, 0];
        contentTypeHeaders = ['Past Exams', 'Course Notes', 'Journals'];
        contentTypeNumbers = [0, 0, 0];
        console.log(results);

        for(result in results){
          console.log("AUTHOR-TYPE" + result.authorType-1);
          console.log("FILE-TYPE" + result.fileType);
          console.log("CONTENT-TYPE" + result.contentType);
          accountTypeNumbers[results[result].authorType - 1]++;
          fileTypeNumbers[results[result].fileType]++;
          contentTypeNumbers[results[result].contentType]++;
        }

        var accountTypeArray = new Array();
        accountTypeArray.push(["Account Type", "Number of documents found"]);
        accountTypeArray.push([accountTypeHeaders[0], accountTypeNumbers[0]]);
        accountTypeArray.push([accountTypeHeaders[1], accountTypeNumbers[1]]);

        var fileTypeArray = new Array();
        fileTypeArray.push(["File Type", "Number of documents found"]);
        fileTypeArray.push([fileTypeHeaders[0], fileTypeNumbers[0]]);
        fileTypeArray.push([fileTypeHeaders[1], fileTypeNumbers[1]]);
        fileTypeArray.push([fileTypeHeaders[2], fileTypeNumbers[2]]);

        var contentTypeArray = new Array();
        contentTypeArray.push(["Content Type", "Number of documents found"]);
        contentTypeArray.push([contentTypeHeaders[0], contentTypeNumbers[0]]);
        contentTypeArray.push([contentTypeHeaders[1], contentTypeNumbers[1]]);
        contentTypeArray.push([contentTypeHeaders[2], contentTypeNumbers[2]]);

        // console.log(accountTypeArray);
        // console.log(fileTypeArray);
        // console.log(contentTypeArray);

        drawChart(accountTypeArray, "Results by Account Types", "piechartAccount");
        drawChart(fileTypeArray, "Results by File Types", "piechartFile");
        drawChart(contentTypeArray, "Results by Content Types", "piechartContent");

      }

      function drawChart(array, chartTitle, elementId) {
        var data = google.visualization.arrayToDataTable(array);

        var options = {
          title: chartTitle
        };

        var chart = new google.visualization.PieChart(document.getElementById(elementId));

        chart.draw(data, options);
      }
  });