// Find angular module module
// This is the controller for the upload page
//					controller name , function(dependency, ....)
(function(){
	angular.module("quiryApp")
		.controller('uploadController', uploadController);

	angular.module("quiryApp")
		.directive('fileModel', [ '$parse', function($parse) {
		return {
			restrict : 'A',
			link : function(scope, element, attrs) {
				var model = $parse(attrs.fileModel);
				var modelSetter = model.assign;

				element.bind('change', function() {
					scope.$apply(function() {
						modelSetter(scope, element[0].files[0]);
					});
				});
			}
		};
		} ]);

		function uploadController($scope, $window, StorageService, $http, $mdToast){
			// $scope is provided by angular so that the view can refer to
			// the controller scope values
			$scope.file;
			$scope.description;
			$scope.fileTitle;
			$scope.visibility;
			$scope.contentType = 1;
			$scope.course;
			$scope.userId = StorageService.getValue("userId");
			$scope.activateLoading = false;

			// Called when user clicks on submit button
			$scope.uploadDocument = function(form){
				$scope.activateLoading = true;
				var fd = new FormData();
				fd.append('file', $scope.file);
				fd.append('userId', $scope.userId);
				fd.append('description', $scope.description);
				fd.append('fileTitle', $scope.fileTitle);
				fd.append('contentType', $scope.contentType);
				fd.append('course', $scope.course);
				fd.append('courseRestricted', $scope.visibility);

				$http.post('/file/upload', fd, {
					transformRequest: angular.identity,
					transformResponse: angular.identity,
					headers: {'Content-Type': undefined}
				})
				.then(
					function(response) {
						$scope.showToast("File was uploaded successfully", "success");
					}
				)
				.catch(
					function(response) {
						$scope.showToast("File failed to upload", "error");
					}
				);
			}


			$scope.showToast = function(textContent, type) {
               var toast = $mdToast.simple()
                  .textContent(textContent)
                  .action('Return to Home')
                  .hideDelay(7000)
                  .position('bottom right')
                  .highlightAction(true);                     
               
               $mdToast.show(toast).then(function(response) {
                  if ( response == 'ok' || (response == null && type == "success")) {
                  	$scope.activateLoading = false;
                    $window.location.href = "./index.html";
                  }
               });
            }	


		}


})();
