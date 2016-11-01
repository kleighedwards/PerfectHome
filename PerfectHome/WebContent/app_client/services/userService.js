// authenticationService.js

var app = angular.module('ngApp')
app.factory('userService', function($window, $http, authenticationService){
	console.log("userService");
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
	
	userService.getUser = function (id) {
		return $http({
			method: 'GET',	
			url : 'api/user/' + id 
			})
	}
	
	userService.addHome = function (userId, home) {
		console.log(home)
		console.log(home.zillowId)
		var newHome = {'zpId' : home.zillowId, 'address' : home.street, 'zillowImage' : home.imageUrl}
		console.log(newHome)
		return $http({
			method: 'POST' ,
			url : 'api/home/user/' + userId,
			headers : {
				'Content-Type' : 'text/plain'
			},
			data : newHome
		})
	}
	
	userService.deleteHome = function (homeUserId){
		return $http({
			method: 'DELETE',	
			url : 'api/homeuser/' + homeUserId
			})
	}
	
	
	return userService;
	
	
});