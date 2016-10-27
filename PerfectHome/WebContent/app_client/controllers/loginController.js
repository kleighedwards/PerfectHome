// loginController.js

var app = angular.module('ngApp');

app.controller('loginController', function($scope, authenticationService){
	console.log('Login controller');
	
	$scope.authenticate = function(user) {
		console.log(user);
		authenticationService.login(user);
	}
});
