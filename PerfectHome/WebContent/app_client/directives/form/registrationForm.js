// registrationForm.js

angular.module("ngApp").directive("registrationForm", function() {
  return {
    restrict: 'A',
    templateUrl: 'partials/registrationForm.html',
    scope: true,
    transclude : false,
    controller: 'registrationController'
  };
});