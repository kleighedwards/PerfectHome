// authenticationService.js

var app = angular.module('ngApp')
app.factory('zillowService', function($window, $http){
	console.log("Here in zillowService");
	var zillowService = {};
	
	zillowService.getZillowInfo = function(id){
		  var zillow = { zillowId : id }
			return $http({
		        method : 'POST',
		        url : 'api/home/zillow/second',
		        headers : {
		            'Content-Type' : 'application/json'
		        },
		        data : zillow
		    })
		    
		}
	
	return zillowService;
	
	
});