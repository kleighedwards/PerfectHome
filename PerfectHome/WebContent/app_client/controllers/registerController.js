// registerController.js

var app = angular.module('ngApp');

app.controller('registerController', function($scope, $location, registrationService, authenticationService){
	console.log('Register controller');
	
	$scope.makeUser = function(user){
		console.log(user);
		registrationService.createUser(user)
		.then(function(response){
			console.log(response)
			if (response.status === 201) {
				authenticationService.saveToken(response.data.jwt);
				return response;
			}
		})
		.then(function(response) {
			$location.path('/user')
		})
		.catch(function(response){
			console.log(response)
			if (response.status === 400){
				alert("This user name is not available");
			}
			if (response.status >= 500){
				alert("System ofline, please thr");
			}
			return response;
		})
	}
});
