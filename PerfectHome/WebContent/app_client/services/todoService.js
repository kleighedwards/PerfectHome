// todoService.js 

var app = angular.module("ngTodo");

app.factory('todoService', function($http,authenicationService){

    var todos = [];

    var getTodos = function(){
        var userId = null;
        if (authenticationService.isLoggedIn()) {
            userId = authenticationService.currentUser().id;
        }
        return $http({
            method : 'GET',
            url : 'api/users/' + userId + '/todos',
            headers : {
                'x-access-token' : authenticationService.getToken()
            }
        })
    };

    var createTodo = function(todo) {
        var userId = null;
        if (authenticationService.isLoggedIn()) {
            userId = authenticationService.currentUser().id;
        }
        return $http({
            method : 'POST',
            url : 'api/users/' + userId + '/todos',
            headers : {
                'Content-Type': 'application/json',
                'x-access-token' : authenticationService.getToken()
            },
            data : todo
        })
    };

    var deleteTodo = function(todo) {
        var userId = null;
        if (authenticationService.isLoggedIn()) {
            userId = authenticationService.currentUser().id;
        }
        return $http({
            method : 'DELETE',
            url : 'api/users/' + userId + '/todos/' + todo._id,
            headers : {
                'x-access-token' : authenticationService.getToken()
            }
        })
    };

    var updateTodo = function(todo) {
        var userId = null;
        if (authenticationService.isLoggedIn()) {
            userId = authenticationService.currentUser().id;
        }
        return $http({
            method : 'PUT',
            url : 'api/users/' + userId + '/todos/' + todo._id,
            headers : {
                'Content-Type' : 'application/json',
                'x-access-token' : authenticationService.getToken()
            },
            data : todo
        })
    };
    

    return {
        getTodos: getTodos,
        createTodo : createTodo,
        deleteTodo : deleteTodo,
        updateTodo : updateTodo
    }
    
});

