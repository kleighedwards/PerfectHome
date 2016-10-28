// authenticationService.js

var app = angular.module('ngApp')
app.factory('userService', function($window, $http, authenticationService){
	console.log("Here in userService");
	var userService = {};
	
	userService.getHomeZpid = function (url) {
		return $http({
			method: 'POST' ,
			url : 'api/home/zillow',
			headers : {
				'Content-Type' : 'text/plain'
			},
			data : url
		})
	}
	return userService;
	
	
});