// loginController.js

var app = angular.module('ngApp');

app.controller('loginController', function($scope, authenticationService, $location){
	console.log('Login controller');
	
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
	}
});
