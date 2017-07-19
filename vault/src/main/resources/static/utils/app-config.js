wndApp.config(function($httpProvider) {
  $httpProvider.defaults.headers.common = {
    'X-Requested-With': 'XMLHttpRequest'
  };
 
}).config(function($provide,$httpProvider) {
	$provide.factory('AuthHttpInterceptor', function ($q, $location) {
	    return {
	      responseError: function (rejection) {
	        if(rejection.status === 403)
	        	//set page location after login
	        	$location.path('/logout');
	        return $q.reject(rejection);
	      }
	    };
	  });
  $httpProvider.interceptors.push('AuthHttpInterceptor');
});