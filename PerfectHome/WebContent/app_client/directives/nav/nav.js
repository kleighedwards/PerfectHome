// nav.js

angular.module("ngApp").directive("nav", function() {
  return {
    restrict: 'A',
    templateUrl: 'partials/nav.html',
    scope: true,
    transclude : false,
    controller: 'navController'
  };
});