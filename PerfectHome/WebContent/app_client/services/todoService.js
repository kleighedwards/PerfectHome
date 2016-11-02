// todoService.js

var app = angular.module('ngApp');

app.factory('todoService', function($http, authenticationService) {

	var todoService = {};

	var getTodos = function(homeuserId) {
		console.log(homeuserId);
		return $http({
			method : 'GET',
			url : 'api/homeuser/' + homeuserId + '/todos',
			headers : {
				'x-access-token' : authenticationService.getToken()
			}
		})
	};

	var createTodo = function(todo,homeuserId) {
		console.log(todo);
		console.log(todo.id);
		console.log(homeuserId);
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

	var removeTodo = function(todo,homeuserId) {
		console.log(todo);
		console.log(todo.id);
		console.log(homeuserId);
		return $http({
			method : 'DELETE',
			url : 'api/homeuser/'+homeuserId+'/todos/'+todo.id,
			headers : {
				'x-access-token' : authenticationService.getToken()
			}
		})
	};

	var editTodo = function(todo,homeuserId) {
		console.log(todo);
		console.log(todo.id);
		console.log(homeuserId);
		return $http({
			method : 'PUT',
			url : 'api/homeuser/todos/' + todo.id,
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
