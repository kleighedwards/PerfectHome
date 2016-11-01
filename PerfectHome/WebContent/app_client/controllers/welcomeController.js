// welcomeController.js

var app = angular.module('ngApp');

app.controller('welcomeController', function($scope, $rootScope, authenticationService){
	console.log('Welcome Controller');
	$rootScope.bodyClass = 'cd-fixed-bg cd-bg-2';
	
    //if a user token exists at first run, remove it!
	if (authenticationService.isLoggedIn()) {
		console.log("A user token has been detected in this new browser session");
		authenticationService.logout();
		console.log("It has been removed");
	}

});
