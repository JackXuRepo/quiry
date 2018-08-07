(function(){
	angular.module("quiryApp")
		.controller('homeController', HomeController);

		function HomeController($scope, $http, StorageService, $window){
			$scope.userId = StorageService.getValue("userId");

			if (document.getElementById("indexPage")) {
				StorageService.setValue("searchText", "");
			}

			$scope.index = function() {
				$window.location.href = "./index.html";
				$scope.activePage = "index";
			}

			$scope.signOut = function(){
				StorageService.removeValue("userId");
				StorageService.removeValue("userData");
				$window.location.href = "./index.html";
				$scope.activePage = "index";
				$scope.userId = null;
			}

			$scope.register = function(){
				$window.location.href = "./registration.html";
				$scope.activePage = "register";
			}

			$scope.signIn = function(){
				$window.location.href = "./login.html";
				$scope.activePage = "login";
			}

			$scope.upload = function(){
				$window.location.href = "./upload.html";
				$scope.activePage = "upload";
			}

			$scope.user = function(){
				$window.location.href = "./user.html";
				$scope.activePage = "user";
			}

		}
})();