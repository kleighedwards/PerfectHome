// todoService.js

var app = angular.module('ngApp');

app.factory('noteService', function($http, authenticationService) {

	var todoService = {};

	var getNotes = function(homeuserId) {
		console.log(homeuserId);
		return $http({
			method : 'GET',
			url : 'api/homeuser/' + homeuserId + '/notes',
			headers : {
				'x-access-token' : authenticationService.getToken()
			}
		})
	};

	var createNote = function(note, homeuserId) {
		console.log(note);
		var newNote = { 'notes' : note , 'date' :  Date.now() }
		console.log(homeuserId);
		return $http({
			method : 'POST',
			url : 'api/homeuser/' + homeuserId + '/notes',
			headers : {
				'Content-Type' : 'application/json',
				'x-access-token' : authenticationService.getToken()
			},
			data : newNote
		})
	};

	var removeNote = function(note,homeuserId) {
		console.log(note);
		console.log(note.id);
		console.log(homeuserId);
		return $http({
			method : 'DELETE',
			url : 'api/homeuser/'+homeuserId+'/notes/'+note.id,
			headers : {
				'x-access-token' : authenticationService.getToken()
			}
		})
	};

	var editNote = function(note,homeuserId) {
		console.log(note);
		console.log(note.id);
		console.log(homeuserId);
		return $http({
			method : 'PUT',
			url : 'api/homeuser/notes/' + note.id,
			headers : {
				'Content-Type' : 'application/json',
				'x-access-token' : authenticationService.getToken()
			},
			data : note
		})
	};

	return {
		getNotes   : getNotes,
		createNote : createNote,
		removeNote : removeNote,
		editNote   : editNote
	};
});
