// todosController.js

var app = angular.module('ngApp');
// a getter method that retrieves the myApp module and stores it in a variable

app.controller('todosController', function($location, $scope, todoService, authenticationService, userService){
   $scope.todos = [];
	
   $scope.submit = function(task,homeuserId){
      console.log("Task Received");
      console.log(homeuserId);
      if (task.task){
        var newTask = {};
        newTask.task = task.task;
        newTask.date = task.date;
        newTask.completed =  false;
        $scope.createTodo(newTask,homeuserId);
        task.task = undefined;
        homeuserId = homeuserId;
      }
   }
   
   $scope.createTodo = function(todo,homeuserId){
	   console.log("Request received to create selected Task");
       todoService.createTodo(todo,homeuserId)
	   .then(function(response){
		   $scope.loadData(homeuserId);
	   })
   }
   
   $scope.loadData = function(homeuserId){
	   todoService.getTodos(homeuserId)
         .then(function(response){
           $scope.todos = response.data;
           console.log($scope.todos);
       });
   }

   $scope.removeTodo = function(todo,homeuserId){
      console.log("Request received to remove selected Task");
      todoService.removeTodo(todo,homeuserId)
      .then(function(response){
          $scope.loadData(homeuserId);
      });
  }

   $scope.editTodo = function(todo,homeuserId){
      console.log("Request received to edit selected Task");
      todoService.editTodo(todo,homeuserId)
      .then(function(response){
          $scope.loadData(homeuserId);
      });
  }

   $scope.styleTooMany = function(tasks) {
     return (tasks > 3) ? "yellow" : "green";
   }

});

