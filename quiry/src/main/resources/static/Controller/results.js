angular.module('quiryApp').controller('resultsController', function ($scope, $log, StorageService) {
  $scope.totalItems = 64;
  $scope.currentPage = 4;
  $scope.userId = StorageService.getValue("userId");
  $scope.results = StorageService.getValue("results");
  $scope.maxSize = 5;
  $scope.sortKey;
  $scope.reverse;

  $scope.sort = function(key){
  	$scope.sortKey = key;
  	$scope.reverse = !$scope.reverse;
  }
  $scope.setPage = function (pageNo) {
    $scope.currentPage = pageNo;
  };

  $scope.getIcon = function(type){
  	console.log('TYPE : '+ type)

  	if(type == "pdf"){
  		return "fas fa-file-pdf pdf-col";
  	}
  	if(type == "txt"){
  		return "fas fa-file-alt txt-col";
  	}
  	if(type == "html"){
 		return "fab fa-internet-explorer html-col"
  	}

/*
  	var iconMap = {
				  	"pdf": "fas fa-file-pdf pdf-col",
				  	"txt": "fas fa-file-alt txt-col",
				  	"html":"fab fa-internet-explorer html-col"
				  }
	return iconMap;
	*/
  }

  $scope.pageChanged = function() {
    $log.log('Page changed to: ' + $scope.currentPage);
    $log.log($scope.results);
  };
});