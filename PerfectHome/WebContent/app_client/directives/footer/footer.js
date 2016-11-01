// myFooter.js

  var app = angular.module('ngApp');

  app.directive('footDirective', function(){
      return {
        restrict: 'E',
        template: `
        <footer>
          <div>
            <h4>Todos Completed: <span ng-class="stylefunc(countComplete())">{{countComplete()}}</span></h4>
          </div>
        </footer>
        `,
        scope : {
          todos : '=',
          stylefunc : '=',
        },
        link : function($scope,$element,$attr) {
          $scope.countComplete = function(){
            var count = 0;
            if($scope.todos === undefined) $scope.todos = [];
            $scope.todos.forEach(function(todo){
              if (todo.completed) {
                count++;
              }
            });
            return count;
          }
        }
      }
    });
 