  angular.module('quiryApp').controller('resultsController', function($scope, $log, StorageService) {
      $scope.currentPage = 1;
      $scope.userId = StorageService.getValue("userId");
      $scope.results = StorageService.getValue("results");
      $scope.maxSize = 5;
      $scope.sortKey;
      $scope.reverse;

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
          console.log('TYPE : ' + type);

          var iconMap = {
                  "0": "fas fa-file-pdf pdf-col",
                  "1": "fas fa-file-alt txt-col",
                  "2":"fab fa-internet-explorer html-col"
                };
            return iconMap[type];
            
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

      $scope.download = function(fileId){
            return;
      }

      $scope.signOut = function(){
        StorageService.removeValue("userId");
        $scope.userId = null;
      }
  });