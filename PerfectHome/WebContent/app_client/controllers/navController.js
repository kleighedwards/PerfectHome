// navController.js

var app = angular.module('ngApp');

app.controller("navController", function ($scope, $location, authenticationService, userService, $rootScope) {
	console.log('Nav Controller');
    $scope.isLoggedIn = authenticationService.isLoggedIn;
        
    $scope.logOutUser = function(){
  	   console.log("Request received to logout user");
  	   authenticationService.logout();
  	   $location.path("/login");
    };
 
});