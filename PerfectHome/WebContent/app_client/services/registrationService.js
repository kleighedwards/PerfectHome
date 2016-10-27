var app = angular.module('ngTodo');

app.factory('registrationService', function($http){

var createUser = function(data){
    var createUser ={ task: data, completed: false };

    return $http({
        method : 'POST',
        url : 'api/user',
        headers : {
            'Content-Type' : 'application/json'
        },
        data : createUser
    })
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