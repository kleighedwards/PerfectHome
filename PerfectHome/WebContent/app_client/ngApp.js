// ngApp.js

var app = angular.module('ngApp', ['ngRoute'])
.config(function($routeProvider){
  $routeProvider
    .when('/', {
       templateUrl: 'public/views/templates/home.view.html',
       controller: 'homeController'
    })
    .when('/login', {
      templateUrl: 'public/views/templates/login.view.html',
      controller: 'loginController'
    })
    .when('/register', {
      templateUrl: 'public/views/templates/register.view.html',
      controller: 'registerController'
    })
    .when('/todos', {
      templateUrl: 'public/views/templates/todos.view.html',
      controller: 'todosController'
    })
    .otherwise({

    })
});
