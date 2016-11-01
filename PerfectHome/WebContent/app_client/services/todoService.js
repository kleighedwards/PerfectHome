// todoService.js

var app = angular.module('ngApp');

app.factory('todoService', function($http, authenticationService) {

	var todoService = {};

	var getTodos = function(homeuserId) {
		var userId = null;
		console.log(homeuserId);
		if (authenticationService.isLoggedIn()) {
			userId = authenticationService.currentUser().id;
		}
		return $http({
			method : 'GET',
			url : 'api/homeuser/' + homeuserId + '/todos',
			headers : {
				'x-access-token' : authenticationService.getToken()
			}
		})
	};

	var createTodo = function(todo,homeuserId) {
		var userId = null;
		if (authenticationService.isLoggedIn()) {
			userId = authenticationService.currentUser().id;
		}
		console.log(userId);
		console.log(todo);
		return $http({
			method : 'POST',
			url : 'api/homeuser/' + homeuserId + '/todos',
			headers : {
				'Content-Type' : 'application/json',
				'x-access-token' : authenticationService.getToken()
			},
			data : todo
		})
	};

	var removeTodo = function(todo) {
		var userId = null;
		if (authenticationService.isLoggedIn()) {
			userId = authenticationService.currentUser().id;
		}
		console.log(userId);
		console.log(todo);
		return $http({
			method : 'DELETE',
			url : 'api/todos/' + todo.id,
			headers : {
				'x-access-token' : authenticationService.getToken()
			}
		})
	};

	var editTodo = function(todo) {
		var userId = null;
		if (authenticationService.isLoggedIn()) {
			userId = authenticationService.currentUser().id;
		}
		console.log(userId);
		console.log(todo);
		
		return $http({
			method : 'PUT',
			url : 'api/users/' + userId + '/todos/' + todo.id,
			headers : {
				'Content-Type' : 'application/json',
				'x-access-token' : authenticationService.getToken()
			},
			data : todo
		})
	};

	return {
		getTodos   : getTodos,
		createTodo : createTodo,
		removeTodo : removeTodo,
		editTodo   : editTodo
	};
});
