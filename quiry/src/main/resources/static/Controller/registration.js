// Find angular module module
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('registrationController', registrationController);

		function registrationController($scope, $window, StorageService, $http){
			// $scope is provided by angular so that the view can refer to
			// the controller scope values
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
		    $scope.userExists = false;


		    // Called when user clicks on submit button
			$scope.submitForm = function(form){
				$scope.submit = true;
				console.log(form);
				console.log(form.$valid);
				console.log($scope.password == $scope.passwordConf);
				if(form.$valid && ($scope.password == $scope.passwordConf)){
					console.log(JSON.stringify($scope.parseData()));
					$http.post('/user/register', JSON.stringify($scope.parseData()))
				    .then(
				      	function(response){
				      		var responseData = response.data;
				      		StorageService.setValue("userId", $scope.username);
				      		StorageService.setValue("userData", responseData);
							$window.location.href = "./index.html";


				      	}

				    )
				    .catch(
				    	function(response){
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




		}
})();
