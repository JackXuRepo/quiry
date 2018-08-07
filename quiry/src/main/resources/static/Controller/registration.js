// Find angular module module
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('registrationController', registrationController);

		function registrationController($scope, $window, StorageService, $http, $mdToast){

			$scope.username;
			$scope.password;
		    $scope.message;
		    $scope.userId;
		    $scope.firstName;
		    $scope.lastName;
		    $scope.email;
		    $scope.accountType = 1;
		    $scope.passwordConf;
		    $scope.submit;
		    $scope.submitted = false;
		    $scope.userExists = false;
		    $scope.activateLoading = false;


			$scope.submitForm = function(form){

				$scope.activateLoading = true;

				console.log($scope.password == $scope.passwordConf);
				if (form.$valid && ($scope.password == $scope.passwordConf) && !$scope.submitted) {
					$scope.submitted = true;
					$http.post('/user/register', JSON.stringify($scope.parseData()))
				    .then(
				      	function(response){
				      		$scope.activateLoading = false;
				      		var responseData = response.data;
				      		$scope.showToast("Registration successful. Please ACTIVATE your Account via your U of T E-mail.", "success");
				      	}
				    )
				    .catch(
				    	function(response){
				    		$scope.submitted = false;
				    		$scope.activateLoading = false;
				      		var responseData = response.data;
				      		$scope.userExists = responseData.userExists;
				    	}
				     );
				}
			}

			$scope.parseData = function(){
				return (
					{
						'userId' : $scope.username,
						'password' : $scope.password,
						'firstName' : $scope.firstName,
						'lastName' : $scope.lastName,
						'email' : $scope.email,
						'accessLevel' : $scope.accountType
					}
				);
			}

			$scope.showToast = function(textContent, type) {
				var toast = $mdToast.simple()
					.textContent(textContent)
					.action('Return to Home')
					.hideDelay(17000)
					.position('bottom')
					.highlightAction(true);

				$mdToast.show(toast).then(function(response) {
					if ( response == 'ok' || (response == null && type == "success")) {
						$window.location.href = "./index.html";
					}
				});
			}

		}
})();
