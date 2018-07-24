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

		function uploadController($scope, $window, StorageService, $http){
			// $scope is provided by angular so that the view can refer to
			// the controller scope values
			$scope.file;
			$scope.description;
			$scope.fileTitle;
			$scope.visibility;
			$scope.contentType;
			$scope.course;
			$scope.userId = StorageService.getValue("userId");

			// Called when user clicks on submit button
			$scope.uploadDocument = function(form){
				console.log($scope.file);

				var fd = new FormData();
				fd.append('file', $scope.file);
				fd.append('userId', $scope.userId);
				fd.append('description', $scope.description);
				fd.append('fileTitle', $scope.fileTitle);
				fd.append('contentType', $scope.contentType);
				fd.append('courseRestricted', $scope.visibility);

				$http.post('/file/upload', fd, {
					transformRequest: angular.identity,
					transformResponse: angular.identity,
					headers: {'Content-Type': undefined}
				})
				.then(
					function(response) {
						$window.location.href = "./index.html";
					}
				)
				.catch(
					function(response) {
						$window.location.href = "./index.html";
					}
				);
			}
		}
})();
