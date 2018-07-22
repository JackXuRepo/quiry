// Find angular module module 
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('loginController', LoginController);

		function LoginController($scope, $window, StorageService){
			// $scope is provided by angular so that the view can refer to
			// the controller scope values
			$scope.username;
			$scope.password;
		    $scope.message;
		    $scope.userId;

		    // Called when user clicks on login button
			$scope.loginUser = function(){

				// For debugging
				console.log("Click Registered");
				// To view the console, open ur browsers developer tools by pressing F12 (at least for chrome)

				// Just mocking for now
				// Notice how the view and the model changes dynamically
				if(($scope.username == "admin" && $scope.password == "admin" )|| ($scope.username == StorageService.getValue("username") && $scope.password == StorageService.getValue("password"))){
					$scope.message = "Access Granted";
					document.getElementById("password").className = "input-valid";
					document.getElementById("username").className = "input-valid";
					document.getElementById("statusMessage").style = "color:green";

					$scope.userId = $scope.username;
					StorageService.setValue("userId", $scope.userId);
					console.log(StorageService.getValue("userId"));
					$window.location.href = "./index.html";
				}
				else{
					$scope.message = "WRONG PASSWORD";
					document.getElementById("password").className = "input-error";
					document.getElementById("username").className = "input-error";
					document.getElementById("statusMessage").style = "color:red";
				}


			}
		}
})();
