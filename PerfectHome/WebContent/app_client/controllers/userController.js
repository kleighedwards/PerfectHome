// userController.js

var app = angular.module('ngApp');

app.controller('userController', function($scope, $location, userService){
	console.log('User controller');
	
	$scope.geolocate = userService.geolocate;
	console.log($scope.geolocate);
	console.log(userService.geolocate);
	$scope.initAutocomplete = userService.initAutocomplete

});
