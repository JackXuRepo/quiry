// Find angular module module 
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('userController', UserController);

	function UserController($scope, $window, StorageService, $http){
		// $scope is provided by angular so that the view can refer to
		// the controller scope values
		$scope.userData = StorageService.getValue("userData");
		$scope.emailPassword;
		$scope.newEmail;
		$scope.newEmailConf;


		$scope.getAccountType = function(type){
			var map = {
				"1": "Student",
				"2": "Instructor"
			};
			return map[type];
		}

		$scope.changeEmail = function(password, newEmail, newEmailConf) {
			alert("Email successfully changed");
			return
		}

		$scope.changePassword = function(oldPassword, newPassword, newPasswordConf) {
			alert("Password successfully changed");
			return
		}

		$scope.changeName = function() {
			alert("Name successfully changed");
			return
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
