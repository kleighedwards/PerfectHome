// tableController.js

var app = angular.module('ngApp');

app.controller("tableController", function ($scope) {
 
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
    }
 
});