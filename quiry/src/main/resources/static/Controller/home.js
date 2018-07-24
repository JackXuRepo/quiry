// Find angular module module 
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('homeController', HomeController);
		
		function HomeController($scope, $http, StorageService, $window){
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
			$scope.userId = StorageService.getValue("userId");


			$scope.search = function(){
				// Need to call back-end
				console.log($scope.userId);
				console.log(StorageService.getValue("userId"));
				StorageService.setValue("results", $scope.results);
				$window.location.href = "./results.html";
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

			/**
			$scope.query = function(searchText) {
			    return $http.
			      .get(BACKEND_URL + '/files/' + searchText)
			      .then(function(data) {
			        return data;
			      });
			  };
			  **/

			$scope.signOut = function(){
				StorageService.removeValue("userId");
				$scope.userId = null;
			}

			 $scope.query = function(searchText) {
			 	return [
						  "CSCA01 Winter 2016 Final Exam",
						  "Journals on Artificial Intelligence",
						  "PSYA01 Lecture 11 Notes",
						  "Calculus Formula Sheet",
						  "Statistics - Distributions",
						  "PSYA01 Midterm Fall 2017"
						]
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

    $scope.results = [
      {
        title: "Final Exam CSC01 2017",
        fileType: "pdf",
        contentType: "Past Exams",
        author: "A. Abbas",
        course: "CSC01"

      },
      {
        title: "How to Code in Haskell",
        fileType: "html", 
        contentType: "Notes",
        author: "H. Askell",
        course: "CSC24"
      },
      {
        title: "A Study of Text Files",
        fileType: "txt",
        contentType: "Journals",
        author: "T. Extee",
        course: "CSC01"
      },
      {
        title: "How to Program",
        fileType: "html",
        contentType: "Notes",
        author: "P. Rogers",
        course: "CSC02"
      },
      {
        title: "Notes for CSC43",
        fileType: "pdf",
        contentType: "Notes",
        author: "T. Lee",
        course: "CSC43"
      },
    ];


		}
})();
