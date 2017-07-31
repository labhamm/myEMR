wndApp
		.factory(
				"GPlusAuthService",
				function($q, $window) {
					var signIn;
					signIn = function() {
						var defered = $q.defer();
						$window.signinCallback = function(response) {
							$window.signinCallback = undefined;
							defered.resolve(response);
						};

						gapi.auth
								.signIn({
									clientid : "998679777714-5r573f4lvf7is865qjt6t5p2qn5ruk2s.apps.googleusercontent.com",
									cookiepolicy : "single_host_origin",
									requestvisibleactions : "http://schemas.google.com/AddActivity",
									scope : "https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read",
									callback : "signinCallback"

								})
						return defered.promise;
					};
					return {
						signIn : signIn
					}

				});