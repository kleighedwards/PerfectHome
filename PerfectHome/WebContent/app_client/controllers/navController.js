// navController.js

var app = angular.module('ngApp');

app.controller("navController", function ($scope, $location, authenticationService, userService) {

    
    $scope.isLoggedIn = authenticationService.isLoggedIn;
    
    if ($scope.isLoggedIn()){
     var userId = authenticationService.currentUser().id;
     $scope.user = userService.getUser(userId);
     console.log($scope.user)
    }
    
    console.log(authenticationService.isLoggedIn());
    console.log($scope.isLoggedIn());
    
    $scope.logOutUser = function(){
  	   console.log("Request received to logout user");
  	   authenticationService.logout();
  	   $location.path("/login");
    };
 
});