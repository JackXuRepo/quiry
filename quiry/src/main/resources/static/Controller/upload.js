// Find angular module module 
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('uploadController', uploadController);

		function uploadController($scope, $window, StorageService){
			// $scope is provided by angular so that the view can refer to
			// the controller scope values
			$scope.file;
			$scope.description;
			$scope.fileTitle;
			$scope.visibility;
			$scope.contentType;
			$scope.course;
			$scope.userId = StorageService.getValue("userId");


		    // Called when user clicks on submit button
			$scope.uploadDocument = function(form){
				console.log($scope.file);

			}



		}
})();
