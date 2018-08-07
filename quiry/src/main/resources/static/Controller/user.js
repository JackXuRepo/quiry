(function(){
	angular.module("quiryApp")
		.controller('userController', UserController);

	function UserController($scope, $window, StorageService, $http){

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

	  $scope.currentPage = 1;
      $scope.results;
      $scope.maxSize = 10;
      $scope.sortKey;
      $scope.reverse;
      $scope.Math = window.Math;

      $scope.clearSort = function() {
          $scope.sortKey = '';
          $scope.reverse = null;
      }

      $scope.sort = function(key) {
          $scope.sortKey = key;
          $scope.reverse = !$scope.reverse;
      }

      $scope.setPage = function(pageNo) {
          $scope.currentPage = pageNo;
      };

      $scope.getIcon = function(type) {

          var iconMap = {
                  "0": "fas fa-file-pdf pdf-col",
                  "1": "fas fa-file-alt txt-col",
                  "2":"fab fa-internet-explorer html-col"
                };
            return iconMap[type];

      }

      $scope.filterContentType = function(input) {
            var map = {
                  "0": "Past Exam",
                  "1": "Notes",
                  "2": "Journal"
                };
            return map[input];
      };

      $scope.download = function(idFile){
          $http.get('/file/download', {
            responseType: 'arraybuffer',
            params: { fileId: idFile }
          })
          .then(
            function(response) {
              var blob = new Blob([response.data], {type: response.headers('Content-Type')});
              saveAs(blob, response.headers('File-Name'));
            }
          )
          .catch(
            function(response) {
              alert("There was an error with the download");
            }
          );
      }

		$scope.remove = function(idFile, index){
			if (!confirm("Confirm deletion?")) {
				return;
			}

			$http.get('/file/delete', {
				transformResponse: angular.identity,
				params: { fileId: idFile }
			})
			.then(
				function(response) {
					var i = $scope.maxSize * ($scope.currentPage - 1) + index;
					$scope.results.splice(i, 1);
					setChartData();
				}
			)
			.catch(
				function(response) {
				}
			);
		}

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
			var passwordRegex = /^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])([a-zA-Z0-9]+)$/
			if ($scope.newPassword != null && $scope.newPassword.length
					&& !passwordRegex.test($scope.newPassword)) {
				$scope.message = "Password must meet complexity requirements";
				return;
			}

			var emailRegex = /^[a-z|A-Z|\.|0-9]+@([a-z|A-Z|0-9]+\.)*(utoronto\.ca|toronto\.edu)$/
			if (!emailRegex.test($scope.email)) {
				$scope.message = "Not a valid UofT email";
				return;
			}

			$http.post('/user/modify', JSON.stringify($scope.parseData()))
				.then(
					function(response) {
						$scope.userData = response.data;
						$scope.userId = $scope.userData.userId;

						StorageService.setValue("userId", $scope.userId);
						StorageService.setValue("userData", $scope.userData);

						$scope.editing = false;
					}
				)
				.catch(
					function(response) {
						$scope.message = "Wrong Password?";
					}
				);

		}

		$scope.cancel = function() {
			$scope.editing = false;
		}


		$scope.pageChanged = function(page) {
			$scope.currentPage = page;
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

	  function setChartData(){

        fileTypeHeaders = ['PDF', 'TXT', 'HTML'];
        fileTypeNumbers = [0, 0, 0];
        contentTypeHeaders = ['Past Exams', 'Course Notes', 'Journals'];
        contentTypeNumbers = [0, 0, 0];

        for(result in $scope.results){
          fileTypeNumbers[$scope.results[result].fileType]++;
          contentTypeNumbers[$scope.results[result].contentType]++;
        }

        var fileTypeArray = new Array();
        fileTypeArray.push(["File Type", "Number of documents found"]);
        fileTypeArray.push([fileTypeHeaders[0], fileTypeNumbers[0]]);
        fileTypeArray.push([fileTypeHeaders[1], fileTypeNumbers[1]]);
        fileTypeArray.push([fileTypeHeaders[2], fileTypeNumbers[2]]);

        var contentTypeArray = new Array();
        contentTypeArray.push(["Content Type", "Number of documents found"]);
        contentTypeArray.push([contentTypeHeaders[0], contentTypeNumbers[0]]);
        contentTypeArray.push([contentTypeHeaders[1], contentTypeNumbers[1]]);
        contentTypeArray.push([contentTypeHeaders[2], contentTypeNumbers[2]]);

        drawChart(fileTypeArray, "Uploaded File Types", "piechartFile");
        drawChart(contentTypeArray, "Uploaded Content Types", "piechartContent");

      }

      function drawChart(array, chartTitle, elementId) {
        var data = google.visualization.arrayToDataTable(array);

        var options = {
          title: chartTitle
        };

        var chart = new google.visualization.PieChart(document.getElementById(elementId));

        chart.draw(data, options);
      }

		// load user files
		var init = function() {
			$http.get( "/file/user", {
				params: { userId : $scope.userId }
			})
			.then(function(response){
				$scope.results = response.data;
			 })
			.catch(function(response){
			});
		};
		init();

		$('#page1').ready(function() {
			$scope.pageChanged(1);
		});

		// load google charts
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(setChartData);
	}
})();

