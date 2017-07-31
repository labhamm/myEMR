// Login Controller
wndApp.controller('loginCtrl',function($scope, $location, $log, $rootScope, $sessionStorage, $http) {
			// set the default value
			$scope.isauthFailded = false;
			$rootScope.login = false;

			// create the payload for login
			$scope.login = function() {
				// set page location after login
				$location.path('/home');
				//set login flag true in rootScope
				$rootScope.login = true;
				//set the login flag in session
				$sessionStorage.login = true;

			}

})
// Home Controller
.controller('homeCtrl', function($scope, $http, $log, $route) {
	$scope.name = "home";
	
	$scope.getAllTimelines = function(){
		var responseValue = $http({
			method : "GET",
			url : "api/v1/timeline",
			accept : "application/json"
		});

		responseValue.success(function(data) {
			$scope.timelines = data.response;
		});
	}
})
// Upload
.controller('uploadCtrl', function($scope, $http, $log, $location, $rootScope, $window) {
	  var vm = $scope;
	  var url = "/api/v1/File";
	  var config = { headers: {
	                   "Content-Type": undefined,
	                  }
	               };

	  vm.upload = function() {
	    
	    var formData = new $window.FormData();
	    
	    formData.append("file", vm.files[0]);
	    
	    $http.post(url, formData, config).
	     then(function(response) {
	      vm.result = "SUCCESS";
	    }).catch(function(response) {
	       console.log('failed', response);
	      vm.result = "ERROR "+response.status;
	    });
	  };

}).controller('logoutCtrl', function($scope, $rootScope) {
	$scope.name = "logout";
	$rootScope.login = false;
})
// Directive
.directive("myFiles", function($parse) {
			  return function linkFn (scope, elem, attrs) {
				    elem.on("change", function (e) {
				      scope.$eval(attrs.myFiles + "=$files", {$files: e.target.files});
				      scope.$apply();
				    })
				  }
});