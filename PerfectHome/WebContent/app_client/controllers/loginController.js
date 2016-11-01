// loginController.js

var app = angular.module('ngApp');


app.controller('loginController', function($scope, authenticationService, $location, $rootScope){
	console.log('Login Controller');
	$rootScope.bodyClass = 'cd-fixed-bg cd-bg-1';
	
	$scope.authenticate = function(user) {
		console.log(user);
		authenticationService.login(user)
		.then(function(response){
			if (response.status === 200) {
				authenticationService.saveToken(response.data.jwt)
				return response;
			}
		})
		.then(function(response) {
			$location.path('/user')
		})
		.catch(function(response){
			console.log(response)
			if ((response.status === 400) || (response.status === 401)){
				alert("Error, please provide correct account data");
				$location.path('/login')
			}
			if (response.status >= 500){
				alert("Server is offline, please try again later");
				$location.path('/')
			}
			return response;
		})
	}
});
