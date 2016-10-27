// navController.js

var app = angular.module('ngApp');

app.controller("navController", function ($scope, $location, authenticationService) {
 
    $scope.submitFeedback = function(action) {
        //good or bad...
        if (action) {
            //good
 
            //submit using $http or service
 
        }
        else {
            //bad
 
            //submit using $http or service
        }
    };
    
    $scope.isLoggedIn = authenticationService.isLoggedIn;
    
    $scope.logOutUser = function(){
  	   console.log("Request received to logout user");
  	   authenticationService.logout();
  	   $location.path("/login");
    };
 
});