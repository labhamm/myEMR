var wndApp = angular.module('wndApp',
		[ ]);

wndApp.controller('hackCtrl', function($scope, $http) {

	console.log(" in hack ctl");
	var getAllTimelines = function() {

		
		var responseValue = $http({
			method : "GET",
			url : "http://ec2-13-126-115-105.ap-south-1.compute.amazonaws.com:8282/api/v1/timeline",
			accept : "application/json"
		});

		responseValue.success(function(data) {
			$scope.timelines = data.response;
		});
	};
	
	
$scope.filterTimelines = function(tag) {

		
		var responseValue = $http({
			method : "GET",
			url : "http://ec2-13-126-115-105.ap-south-1.compute.amazonaws.com:8282/api/v1/timeline?tags="+tag,
			accept : "application/json"
		});

		responseValue.success(function(data) {
			$scope.timelines = data.response;
		});
	}
	
	
	getAllTimelines();
});