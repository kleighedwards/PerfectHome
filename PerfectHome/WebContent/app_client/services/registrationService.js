var app = angular.module('ngApp');

app.factory('registrationService', function($http){

var createUser = function(user){

    return $http({
        method : 'POST',
        url : 'api/auth/account',
        headers : {
          'Content-Type' : 'application/json'
        },
        data : user
      })
      .then(function(response){
        if (response.status == 200){
        	return $http({
                method : 'POST',
                url : 'api/auth/signup',
                headers : {
                    'Content-Type' : 'application/json'
                },
                data : user
            })
        }
        return response;
      });
};

var deleteUser = function(id) {
    return $http({
        method : "DELETE",
        url : 'api/user/' + id
    })
};

    return {
        createUser: createUser, 
        deleteUser: deleteUser
    }

});