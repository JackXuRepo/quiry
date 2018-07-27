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
                  "pdf": "fas fa-file-pdf pdf-col",
                  "txt": "fas fa-file-alt txt-col",
                  "html":"fab fa-internet-explorer html-col"
                };
            return iconMap[type];
            
      }


      $scope.pageChanged = function() {
          $log.log('Page changed to: ' + $scope.currentPage);
          $log.log($scope.results);
      };
  });