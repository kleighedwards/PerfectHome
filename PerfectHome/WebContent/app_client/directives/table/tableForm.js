// tableForm.js

angular.module("ngApp").directive("tableForm", function() {
  return {
    restrict: 'A',
    templateUrl: 'partials/tableForm.html',
    scope: true,
    transclude : false,
    controller: 'tableController'
  };
});