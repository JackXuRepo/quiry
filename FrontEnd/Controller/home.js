// Find angular module module 
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('homeController', HomeController);
		$scope.searchText;

		function HomeController($scope){
			
			$scope.search = function(){
				// Need to call back-end
			}

		}
})();
