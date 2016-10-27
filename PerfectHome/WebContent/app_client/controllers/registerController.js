// registerController.js

var app = angular.module('ngApp');

app.controller('registerController', function($scope, registrationService){
	console.log('Register controller');
	
	$scope.makeUser = function(user){
		console.log(user);
		registrationService.createUser(user);
	}
});
