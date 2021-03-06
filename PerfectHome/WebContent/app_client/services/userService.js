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
	
	userService.createPhoto = function(newImage,homeuserId) {
		console.log(newImage);
		console.log(newImage);
		console.log(homeuserId);
		return $http({
			method : 'POST',
			url : 'api/homeuser/' + homeuserId + '/images',
			headers : {
				'Content-Type' : 'application/json',
				'x-access-token' : authenticationService.getToken()
			},
			data : newImage
		})
	};
	
	userService.getPhotos = function(homeuserId) {
		console.log(homeuserId);
		return $http({
			method : 'GET',
			url : 'api/homeuser/' + homeuserId + '/images',
			headers : {
				'x-access-token' : authenticationService.getToken()
			}
		})
	};
	
	userService.addHome = function (userId, home) {
		console.log(home)
		console.log(home.data.zillowId)
		var newHome = {'zpId' : home.data.zillowId, 'address' : home.data.street, 'zillowImage' : home.data.imageUrl}
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
	
	userService.updateRating = function (rating, homeUserId){
		return $http({
			method: 'PUT' ,
			url : 'api/homeuser/'+homeUserId+'/rating/'+rating,
			headers : {
				'Content-Type' : 'text/plain'
			},
		})
		
	}
	
	return userService;
	
	
});