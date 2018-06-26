// We will create a new module for now
var quiryApp = angular.module("quiryApp", []);

// This is the controller for the login page
//                   controller name , function(dependency, ....)
quiryApp.controller('loginController', function($scope){
	// $scope is provided by angular so that the view can refer to
	// the controller scope values
	$scope.username;
	$scope.password;
    $scope.message;

    // Called when user clicks on login button
	$scope.loginUser = function(){

		// For debugging
		console.log("Click Registered");
		// To view the console, open ur browsers developer tools by pressing F12 (at least for chrome)

		// Just mocking for now
		// Notice how the view and the model changes dynamically
		if($scope.username == "admin" && $scope.password == "admin"){
			$scope.message = "Access Granted";
		}
		else{
			$scope.message = "WRONG PASSWORD";
		}
	}
});