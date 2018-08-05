// Find angular module module
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('userController', UserController);

	function UserController($scope, $window, StorageService, $http){
		// $scope is provided by angular so that the view can refer to
		// the controller scope values

		// to be consistent with other pages
		$scope.userId = StorageService.getValue("userId");

		$scope.editing = false;
		$scope.message = "";
		$scope.userData = StorageService.getValue("userData");

		$scope.firstName;
		$scope.lastName;
		$scope.email;
		$scope.oldPassword;
		$scope.newPassword;
		$scope.confirmPassword;

		$scope.getAccountType = function(type){
			var map = {
				"1": "Student",
				"2": "Instructor"
			};
			return map[type];
		}

		$scope.edit = function() {
			$scope.message = null;
			$scope.firstName = $scope.userData.firstName;
			$scope.lastName = $scope.userData.lastName;
			$scope.email = $scope.userData.email;
			$scope.oldPassword = null;
			$scope.newPassword = null;
			$scope.confirmPassword = null;
			$scope.editing = true;
		}

		$scope.modify = function() {
			$http.post('/user/modify', JSON.stringify($scope.parseData()))
				.then(
					function(response) {
						console.log(response);
						$scope.userData = response.data;
						$scope.userId = $scope.userData.userId;

						StorageService.setValue("userId", $scope.userId);
						StorageService.setValue("userData", $scope.userData);

						$scope.editing = false;
					}
				)
				.catch(
					function(response) {
						console.log(response);
						$scope.message = "Wrong Password?";
					}
				);

		}

		$scope.cancel = function() {
			$scope.editing = false;
		}

		$scope.parseData = function(){
			return (
				{
					'userId' : $scope.userData.userId,
					'password' : $scope.oldPassword,
					'firstName' : $scope.firstName,
					'lastName' : $scope.lastName,
					'email' : $scope.email,
					'newPassword' : $scope.newPassword
				}
			);
		}

		$scope.signOut = function(){;
			StorageService.removeValue("userId");
			$scope.userId = null;
		}
	}
})();
