// registerController.js

var app = angular.module('ngApp');

app.controller('registerController', function($scope, $location, registrationService, authenticationService, $rootScope){
	console.log('Register Controller');
	$rootScope.bodyClass = 'windowImg';
	
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
				alert("Server is offline, please try again later");
				$location.path('/')
			}
			return response;
		})
	}
});
