// Find angular module module 
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('homeController', HomeController);
		
		function HomeController($scope, $http){
			$scope.searchText;
			$scope.timeOptions = ["Today", "This Week", "This Month", "This Year", "Anytime"];
			$scope.advancedSearchOn = false;
			$scope.advancedSearchText = "Show Advanced Search +";
			$scope.dateUploaded = "Anytime";
			$scope.lastUpdated = "Anytime";
			$scope.studentSearch = true;
			$scope.instructorSearch = true;
			$scope.pastExams = true;
			$scope.notes = true;
			$scope.journals = true;
			$scope.pdfType = true;
			$scope.txtType = true;
			$scope.htmlType = true;
			$scope.coursesSelected = [];


			$scope.search = function(){
				// Need to call back-end
			}

			$scope.resetAdvancedSearch = function(){
				$scope.dateUploaded = "Anytime";
				$scope.lastUpdated = "Anytime";
				$scope.studentSearch = false;
				$scope.instructorSearch = false;
				$scope.toggleStudent();
				$scope.toggleInstructor();
				$scope.pastExams = true;
				$scope.notes = true;
				$scope.journals = true;
				$scope.pdfType = true;
				$scope.txtType = true;
				$scope.htmlType = true;
				$scope.author = "";
				$scope.fileName = "";
				$scope.coursesSelected = [];
			}


			$scope.loadOptions = function(query, path) {
    			return $http.get(path);
  			}

			$scope.toggleAdvancedSearch = function(){
				$scope.advancedSearchOn = !$scope.advancedSearchOn;
				showAdvancedSearchText();
			}

			$scope.toggleStudent = function(){
				$scope.studentSearch = !$scope.studentSearch;
				if($scope.studentSearch == true){
					document.getElementById("StudentButton").className = "button-on";
				}
				else{
					document.getElementById("StudentButton").className = "button-off";
				}

			}

			$scope.toggleInstructor = function(){
				$scope.instructorSearch = !$scope.instructorSearch;
				if($scope.instructorSearch == true){
					document.getElementById("InstructorButton").className = "button-on";
				}
				else{
					document.getElementById("InstructorButton").className = "button-off";
				}

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
