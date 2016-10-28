// tableController.js

var app = angular.module(ngApp, []);

app.controller("tableController", function($scope) {
    $scope.useCode = [];
    $scope.bedrooms = [];
    $scope.bathrooms = [];
    $scope.finishedSqFt = [];
    $scope.lotSizeSqFt = [];
    $scope.yearBuilt = [];
    $scope.numFloor = [];
    $scope.useCode = [];
    
    $scope.addHome = function() {
        $scope.useCode,
        $scope.bedrooms,
        $scope.bathrooms,
        $scope.finishedSqFt,
        $scope.lotSizeSqFt,
        $scope.yearBuilt,
        $scope.numFloor,
        $scope.useCode};
        
    $scope.useCode = "";
    $scope.bedrooms = "";
    $scope.bathrooms = "";
    $scope.finishedSqFt = "";
    $scope.lotSizeSqFt = "";
    $scope.yearBuilt = "";
    $scope.numFloor = ""
	});
    
    // get count of home in list from the number of useCodes
    $scope.getTotalItems = function () {
        return $scope.useCode.length;
    };