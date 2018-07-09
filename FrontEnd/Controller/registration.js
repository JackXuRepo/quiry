// Find angular module module 
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('registrationController', registrationController);

		function registrationController($scope, $window, StorageService){
			// $scope is provided by angular so that the view can refer to
			// the controller scope values
			$scope.username;
			$scope.password;
		    $scope.message;
		    $scope.userId;
		    $scope.firstName;
		    $scope.lastName;
		    $scope.email;
		    $scope.accountType;
		    $scope.passwordConf;
		    $scope.submit;


		    // Called when user clicks on submit button
			$scope.submitForm = function(form){
				$scope.submit = true;
				console.log(form);
				console.log(form.$valid);
				console.log($scope.password == $scope.passwordConf);
				if(form.$valid && ($scope.password == $scope.passwordConf)){
					console.log("nice");
					StorageService.setValue("userId", $scope.username);

					StorageService.setValue("username", $scope.username);
					StorageService.setValue("password", $scope.password);

					$window.location.href = "./index.html";
				}

			}



		}
})();
