// authenticationService.js

var app = angular.module('ngApp')
app.factory('userService', function($window, $http, authenticationService){

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
		.then(function(response){
			console.log(response);
		})
	}
	
	return userService;
	
	
});