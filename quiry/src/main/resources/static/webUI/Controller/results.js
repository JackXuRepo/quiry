 angular.module('quiryApp').controller('resultsController', function($http, $scope, $log, StorageService) {
      $scope.currentPage = 1;
      $scope.userId = StorageService.getValue("userId");
      $scope.results = StorageService.getValue("results");
      $scope.maxSize = 5;
      $scope.sortKey = "relevance";
      $scope.sortOrder = false;
      $scope.expandAll = false;
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(setChartData);
      $scope.sortOptions = { "Relevance" : "relevance", "Upload Date" : "uploadDate", "File Title" : "title", "Author Name" : "author",
                            "Content Type" : "contentType", "File Type" : "fileType", "Course Code" : "course" };
      $scope.sortOrderOptions = { Ascending : false, Descending : true};

      $scope.setPage = function(pageNo) {
          $scope.currentPage = pageNo;
      };

      // $scope.getIcon = function(type) {

      //     var iconMap = {
      //             "0": "images/pdf.png",
      //             "1": "images/txt.png",
      //             "2": "images/html.png"
      //           };
      //       console.log(iconMap[type]);
      //       return iconMap[type];
            
      // }
      $scope.getIcon = function(type) {

          var iconMap = {
                  "0": "fa fa-file-pdf",
                  "1": "fa fa-file-txt",
                  "2": "fa fa-file-html"
                };
            return iconMap[type];
            
      }

      $scope.getIconBackground = function(type) {

          var backMap = {
                  "0": "blog-left-left pdf-bg",
                  "1": "blog-left-left txt-bg",
                  "2": "blog-left-left html-bg"
                };
            return backMap[type];
            
      }

      $scope.isInstructor = function(type){

          var map = {
                  "1": "",
                  "2": "fa fa-check"
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

        for(result in results){
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
          title: chartTitle,
          backgroundColor: { fill:'transparent' }
        };

        var chart = new google.visualization.PieChart(document.getElementById(elementId));

        chart.draw(data, options);
      }
  });