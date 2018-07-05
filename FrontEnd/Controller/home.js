// Find angular module module 
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('homeController', HomeController);
		
		function HomeController($scope){
			$scope.searchText;
			$scope.advancedSearchOn = false;
			$scope.advancedSearchText = "Show Advanced Search +";


			$scope.search = function(){
				// Need to call back-end
			}

			$scope.toggleAdvancedSearch = function(){
				$scope.advancedSearchOn = !$scope.advancedSearchOn;
				showAdvancedSearchText();
			}

			function showAdvancedSearchText(){
				if($scope.advancedSearchOn == false){
					$scope.advancedSearchText = "Show Advanced Search +";
				}
				else {
					$scope.advancedSearchText = "Hide Advanced Search -";
				}
			}

		}
})();
