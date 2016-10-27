// loginForm.js

angular.module("ngApp").directive("loginForm", function() {
  return {
    restrict: 'A',
    templateUrl: 'partials/loginForm.html',
    scope: true,
    transclude : false,
    controller: 'loginController'
  };
});