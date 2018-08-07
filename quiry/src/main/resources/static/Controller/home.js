// Find angular module module 
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('homeController', HomeController);
		
		function HomeController($scope, $http, StorageService, $window){
			$scope.userId = StorageService.getValue("userId");
			$scope.activePage = StorageService.getValue("activePage");
			StorageService.setValue("searchText", "");

			$scope.index = function() {
				StorageService.setValue("activePage", "index");
				$window.location.href = "./index.html";
				$scope.activePage = "index";

			}

			$scope.signOut = function(){
				StorageService.removeValue("userId");
				StorageService.setValue("activePage", "index");
				$window.location.href = "./index.html";
				$scope.activePage = "index";
				$scope.userId = null;
			}

			$scope.register = function(){
				StorageService.setValue("activePage", "register");
				$window.location.href = "./registration.html";
				$scope.activePage = "register";
			}

			$scope.signIn = function(){
				StorageService.setValue("activePage", "login");
				$window.location.href = "./login.html";
				$scope.activePage = "login";
			}

			$scope.upload = function(){
				StorageService.setValue("activePage", "upload");
				$window.location.href = "./upload.html";
				$scope.activePage = "upload";
			}

			$scope.user = function(){
				StorageService.setValue("activePage", "user");
				$window.location.href = "./user.html";
				$scope.activePage = "user";
			}


		}
})();