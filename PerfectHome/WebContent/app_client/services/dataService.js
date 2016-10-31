// dataService.js

var app = angular.module('ngApp');

app.factory('dataService', function($http){

	
	
	
    var getData = function(){
        return $http({
             method : 'GET',
             url : 'api/todos'
        })
  };

var createTodo = function(data){
    var createTodo ={ task: data, completed: false };

    return $http({
        method : 'POST',
        url : 'api/todos',
        headers : {
            'Content-Type' : 'application/json'
        },
        data : createTodo
    })
};

var deleteData = function(id) {
    return $http({
        method : "DELETE",
        url : 'api/todos/' + id
    })
};

    return {
    	
    	getZillowInfo : getZillowInfo,
        getData: getData,
        createTodo: createTodo, 
        deleteData: deleteData
    }

});