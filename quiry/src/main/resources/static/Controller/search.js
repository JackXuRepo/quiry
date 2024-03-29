// Find angular module module
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('searchController', searchController);

		function searchController($scope, $http, StorageService, $window){
			$scope.searchText = StorageService.getValue("searchText");
			$scope.author = "";
			$scope.timeOptions = ["Today", "This Week", "This Month", "This Year", "Anytime"];
			$scope.advancedSearchOn = false;
			$scope.advancedSearchText = "Show Advanced Search +";
			$scope.dateUploaded = "Anytime";
			$scope.studentSearch = true;
			$scope.instructorSearch = true;
			$scope.pastExams = true;
			$scope.notes = true;
			$scope.journals = true;
			$scope.pdfType = true;
			$scope.txtType = true;
			$scope.htmlType = true;
			$scope.coursesSelected = [];
			$scope.loadBar = false;


			$scope.search = function(){

				$scope.loadBar = true;
				StorageService.removeValue("results");

				// Mock get request
				//console.log(parseCourseJson($scope.coursesSelected));

				// $http.get("../json/mock.json")
				// 	.then(function(response){
				// 		StorageService.setValue("results", response.data);
				// 		StorageService.setValue("searchText", $scope.searchText);
				// 		$window.location.href = "./results.html";
				// 	});
				var paramConfig = { params: {
									searchText: $scope.searchText,
									author: $scope.author,
						    		dateUploaded: parseTimeOptions($scope.dateUploaded),
									instructorSearch: $scope.instructorSearch,
									studentSearch: $scope.studentSearch,
									pastExams: $scope.pastExams,
									notes: $scope.notes,
									journals: $scope.journals,
									pdfType: $scope.pdfType,
									txtType: $scope.txtType,
									htmlType: $scope.htmlType,
									courses: parseCourseObject($scope.coursesSelected)
				    			}};

				$http.get( "/search/advancedSearch", paramConfig)
					.then(function(response){

					 	StorageService.setValue("results", response.data);
					 	StorageService.setValue("searchText", $scope.searchText);
					 	$window.location.href = "./results.html";
					 })
					.catch(function(response){

						$scope.loadBar = false;

					});
			}

			$scope.resetAdvancedSearch = function(){

				$scope.dateUploaded = "Anytime";
				$scope.author = "";
				$scope.studentSearch = true;
				$scope.instructorSearch = true;
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


			$scope.signOut = function(){
				StorageService.removeValue("userId");
				$scope.userId = null;
			}

			$scope.loadOptions = function(query, path) {
    			return $http.get(path, { cache: true}).then(function(response) {
			      var courses = response.data;
			      return courses.filter(function(course) {
			        return course.text.toLowerCase().indexOf(query.toLowerCase()) != -1;
      				});
    			});
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

			function parseTimeOptions(timeString){
				var timeMap = {
                  "Today": "1",
                  "This Week": "7",
                  "This Month": "31",
                  "This Year": "365",
                  "Anytime": "0"
                };
            	return timeMap[timeString];
			}

			function parseCourseObject(courseObject){
				var courseArray = new Array();
				for(courseBracket in courseObject){
					courseArray.push(courseObject[courseBracket].text);
				}
				return courseArray;
			}


		}
})();
