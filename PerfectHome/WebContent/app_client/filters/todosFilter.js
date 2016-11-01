// todosFilter.js

var app = angular.module('ngApp');

app.filter('todosFilter', function(){
  return function(tasks,showCompleted) {
    var results = [];
    if (tasks === undefined) tasks = [];
    tasks.forEach(function(task){
      if((showCompleted) || (task.completed == false)) {
        results.push(task);
      }
    });
    return results;
  }
});