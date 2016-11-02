// todoRow.js

var app = angular.module('ngApp');

app.directive('attrDirective', function($compile) {
    return {
        restrict: 'A',
        template: `
                    <td style="text-align: left" id="td1">  {{todo.task}}</td>
        			<td id="td1">{{todo.date}}</td>
                    <td id="td1"><input type="checkbox" ng-model="todo.completed" ng-class="strike(todo.completed)" ng-change="edit(todo)"/></td>
                    <td id="td1">{{todo.completed}}</td>
                    <td id="td1"><button id="todo.id" class="btn btn-primary" name="submit" ng-click="update(todo)">Edit</button></td>
                    <td id="td1"><button id="todo.id" class="btn btn-primary" name="submit" ng-click="delete(todo)">Delete</button></td>
                  `,
        scope: {
            todo: "=",
            visible: "=",
            edit: "=",
            delete: "=",
        },
        link: function($scope, $element, $attr) {
            $scope.strike = function(completed) {
                if (completed) {
                    $element.css('text-decoration', 'line-through');
                    $element.css('color', 'grey');
                } else {
                    $element.css('text-decoration', 'none');
                    $element.css('color', 'black');
                }
            }

            $scope.todoCopy = {};
            var editForm = null;

            $scope.update = function(todo) {
                if (editForm === null) {
                    $scope.todoCopy = angular.copy(todo);
                    var $inputRow =
                      `
                        <tr id="tr1">
                        <td id="td1">
                        <input type="text" class="form-control" ng-model="todoCopy.task" />
                        </td>
                        <td id="td1" colspan="3">
                        <input type="date" class="form-control" ng-model="todoCopy.date" />
                        </td>
                        <td id="td1">
                        <button ng-click="save(todoCopy)" class="btn btn-primary">Save</button>
                        </td>
                        <td id="td1">
                        <button ng-click="cancel()" class="btn btn-primary">Cancel</button>
                        </td>
                        </tr>
                      `

                    var compiledRow = $compile($inputRow)($scope);
                    editForm = compiledRow;
                    $element.after(compiledRow);
                }
            }

            $scope.cancel = function() {
                if (editForm != null) {
                    editForm.remove();
                    editForm = null;
                }
            }
            
            $scope.save = function(todo,homeuserId) {
                $scope.edit(todo,homeuserId); //todoService.updateTodo(todo);
                editForm.remove();
                editForm = null;
                $scope.todoCopy = {};
            }
        }
    }
});