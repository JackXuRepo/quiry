// Find angular module module
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('loginController', LoginController);

		function LoginController($scope, $window, StorageService, $http){
			// $scope is provided by angular so that the view can refer to
			// the controller scope values
			$scope.username;
			$scope.password;
		    $scope.message;
		    $scope.resend = null;

		    // Called when user clicks on login button
			$scope.loginUser = function(){

				// For debugging
				console.log("Click Registered");
				// To view the console, open ur browsers developer tools by pressing F12 (at least for chrome)

				$http.post('/user/login', JSON.stringify($scope.parseData()))
				.then( function(response){
					var responseStatus = response.status;
					var responseData = response.data;

					$scope.message = "Access Granted";
					$scope.resend = null;
					document.getElementById("password").className = "input-valid";
					document.getElementById("username").className = "input-valid";
					document.getElementById("statusMessage").style = "color:green";

					StorageService.setValue("userId", $scope.username);
					StorageService.setValue("userData", responseData);
					console.log(StorageService.getValue("userId"));
					$window.location.href = "./index.html";
				})
				.catch( function(response){
					console.log(response);
					var reason = response.data.error;
					if (reason == "incorrect") {
						document.getElementById("password").className = "input-error";
						document.getElementById("username").className = "input-error";
						document.getElementById("statusMessage").style = "color:red";
						$scope.message = "The username or password entered is incorrect. Please try again.";

					} else if (reason == "unactivated") {
						document.getElementById("statusMessage").style = "color:blue";
						$scope.message = "Account is not activated."
						$scope.resend = "Resend email?";
					}
				});
			}

			$scope.emailUser = function() {
				$http.post('/user/email', JSON.stringify(
					{ 'userId' : $scope.username }
				));

				$scope.message = "Request sent.";
				$scope.resend = null;
				document.getElementById("statusMessage").style = "color:blue";
			}

			$scope.parseData = function(){
				return (
					{
						'userId' : $scope.username,
						'password' : $scope.password
					}
				);
			}

		}
})();
