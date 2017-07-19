var wndApp = angular.module('wndApp',
		[ 'ngRoute', 'ngStorage', 'ui.bootstrap' ]).run(
		function($rootScope, $log, $location, $modal, $modalStack) {

			// change page title based on current template
			$rootScope.$on('$routeChangeSuccess', function(event, current,
					previous) {
				if (current.hasOwnProperty('$$route')) {
					$rootScope.title = current.$$route.title;
				}
			});

			// to add active class for menu list item
			$rootScope.getClass = function(path) {
				if ($location.path().substr(0, path.length) == path) {
					return "active"
				} else {
					return ""
				}
			}
		}).config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'pages/login.html',
		controller : 'loginCtrl'
	}).when('/home', {
		templateUrl : 'pages/home.html',
		controller : 'homeCtrl',
		title : 'Hackathon | Home'
	}).when('/upload', {
		templateUrl : 'pages/upload.html',
		controller : 'uploadCtrl',
		title : 'Hackathon | Upload'
	}).when('/logout', {
		templateUrl : 'pages/login.html',
		controller : 'logoutCtrl',
		title : 'Hackathon | Logout'
	}).otherwise({
		redirectTo : '/'
	});
});