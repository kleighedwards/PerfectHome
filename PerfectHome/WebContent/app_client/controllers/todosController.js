// todosController.js

var app = angular.module('ngApp');
// a getter method that retrieves the myApp module and stores it in a variable

app.controller('todosController', function($location, $scope, todoService, authenticationService, userService){
   $scope.todos = [];
   var homeuserId = $scope.currentHomeUserId;
   
   if (authenticationService.isLoggedIn()){
       userService.getUser(authenticationService.currentUser().id)
       .then(function(response){
	       	console.log(response);    
	       	console.log($scope.user);
    	    console.log($scope.currentHomeUserId);
	       	homeuserId = $scope.currentHomeUserId;
	       	console.log(homeuserId)
	       	console.log($scope.activeHome)
	       	$scope.loadData(homeuserId);
	       	console.log($scope.todos);
        });
   };
	
   $scope.submit = function(task){
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
   
   $scope.createTodo = function(todo){
	   console.log("Request received to create selected Task");
       todoService.createTodo(todo,homeuserId)
	   .then(function(response){
		   $scope.loadData(homeuserId);
	   })
   }
   
   $scope.loadData = function(){
	   todoService.getTodos(homeuserId)
         .then(function(response){
        	 console.log(response);
        	 $scope.todos = response.data;
        	 console.log($scope.todos);
       });
   }

   $scope.removeTodo = function(todo){
      console.log("Request received to remove selected Task");
      todoService.removeTodo(todo,homeuserId)
      .then(function(response){
          $scope.loadData(homeuserId);
      });
  }

   $scope.editTodo = function(todo){
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
