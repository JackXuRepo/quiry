// Find angular module module
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('homeController', HomeController);

		function HomeController($scope, $http, StorageService, $window){
			$scope.userId = StorageService.getValue("userId");
			StorageService.setValue("searchText", "");



			$scope.signOut = function(){
				StorageService.removeValue("userId");
				$scope.userId = null;
			}


		}
})();
