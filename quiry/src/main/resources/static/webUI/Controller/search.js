// Find angular module module 
// This is the controller for the login page
//                   controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('searchController', searchController);
		
		function searchController($scope, $http, StorageService, $window, uibButtonConfig){
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
			uibButtonConfig.activeClass = "button-green";

			$scope.search = function(){
				// Need to call back-end
				$scope.loadBar = true;
				console.log($scope.userId);
				console.log(StorageService.getValue("userId"));
				StorageService.removeValue("results");

				$http.get("../json/mock.json")
					.then(function(response){
						StorageService.setValue("results", response.data);
						StorageService.setValue("searchText", $scope.searchText);
						$window.location.href = "./results.html";
					});
				// var paramConfig = {params: {   
				// 					searchText: $scope.searchText,
				// 					author: $scope.author,
				// 		    		dateUploaded: parseTimeOptions($scope.dateUploaded),
				// 					instructorSearch: $scope.instructorSearch,
				// 					studentSearch: $scope.studentSearch,
				// 					pastExams: $scope.pastExams,
				// 					notes: $scope.notes,
				// 					journals: $scope.journals,
				// 					pdfType: $scope.pdfType,
				// 					txtType: $scope.txtType,
				// 					htmlType: $scope.htmlType,
				// 					courses: parseCourseObject($scope.coursesSelected)
				//     			}};
				// console.log(paramConfig.params);
				// $http.get( "/search/advancedSearch", paramConfig)
				// 	.then(function(response){
				// 	 	console.log(response.data);
				// 	 	StorageService.setValue("results", response.data);
				// 	 	StorageService.setValue("searchText", $scope.searchText);
				// 	 	$window.location.href = "./results.html";
				// 	 })
				// 	.catch(function(response){
				// 		console.log(response.data);
				// 		$scope.loadBar = false;
				// 	 	//$window.location.href = "./results.html";
				// 	});
			}

			$scope.resetAdvancedSearch = function(){
				console.log($scope.studentSearch);
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

			$scope.toggleButton = function(id, value){
				if(value == true){
					document.getElementById(id).className = "button-green";
				}
				else{
					document.getElementById(id).className = "button-black";
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
				console.log(courseArray);
				return courseArray;
			}


		}
})();
