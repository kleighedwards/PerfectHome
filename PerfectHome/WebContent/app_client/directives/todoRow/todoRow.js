// table.js

var app = angular.module("ngTodo");

app.directive('todoRow', function($compile, todoService){
      return {
        restrict: 'A',
        template: `<td ng-class="todo.completed ? 'strike' : 'clean'">{{todo.task}}</td>
          <td><input type="checkbox" ng-model="todo.completed" /></td>
          <td><button ng-click="update(todo)" class="btn btn-warning">Edit</button></td>
          <td><button ng-click="remove(todo)" >Delete</button></td>
        `,
        scope : {
          todo : '=',
          remove : '=',
          edit : "="
        },
        link : function($scope, $element, $attr){
          $scope.todoCopy = {};
          var editForm = null;

          $scope.update = function(todo) {
            if (editForm === null) {
              $scope.todoCopy = angular.copy(todo);
              var $inputRow = 
              '<table class="table">' +
              '<tr>' +
                '<td>' + 
                  '<input type="text" class="form-control" ng-model="todoCopy.task" />' +
                '</td>' +
                '<td>' + 
                  '<button ng-click="save(todoCopy)" class="btn btn-primary">Save</button>' +
                '</td>' +
                '<td>' + 
                  '<button ng-click="cancel()" class="btn btn-primary">Cancel</button>' +
                '</td>' +
              '</tr>'+
              '</table>'

              var compiledRow = $compile($inputRow)($scope);
              editForm = compiledRow;
              $element.after(compiledRow);
            }
          }

          $scope.cancel = function(){
            if (editForm != null) {
              editForm.remove(editForm);
              editForm = null;
            }
          }

          $scope.save = function(todo){
            $scope.edit(todo); //todoService.updateTodo(todo);
            editForm.remove();
            editForm = null;
            $scope.todoCopy = {};
          }
        }
      }
    });