// userController.js

var app = angular.module('ngApp');

app.controller('userController', function($scope, $location, $compile, userService, $rootScope, todoService, authenticationService, zillowService, noteService){
	console.log('User controller');
	$rootScope.bodyClass = 'cd-fixed-bg cd-bg-3';
	
	$scope.user = {};
	$scope.activeHome = {};
	$scope.currentHomeUserId = null;
	$scope.todos = [];
	$scope.notes = [];
	$scope.rating;
	var homeuserId ;
	
    if (authenticationService.isLoggedIn()){
        userService.getUser(authenticationService.currentUser().id)
        .then(function(response){
        	$scope.user = response;
        	console.log($scope.user);
        	$scope.currentHomeUserId = $scope.user.data.homeUsers[0].id;
        	homeuserId = $scope.currentHomeUserId;
    	    console.log($scope.currentHomeUserId);
	       	console.log(homeuserId);
	       	$scope.loadData();
//            $scope.todos = $scope.user.data.homeUsers[0].todos;
            console.log($scope.todos);
            $scope.notes = $scope.user.data.homeUsers[0].notes;
            $scope.rating = $scope.user.data.homeUsers[0].rating;
            zillowService.getZillowInfo($scope.user.data.homeUsers[0].homeZpID)
           .then(function(response){
         	  $scope.activeHome = response;
         	  console.log($scope.activeHome);
           })
        });
     }
    
	$scope.remove = function(homeUserId) {
		console.log(homeUserId)
		userService.deleteHome(homeUserId)
		.then(function(){
			userService.getUser(authenticationService.currentUser().id)
			.then(function(response){
				$scope.user =  response;
				console.log($scope.user)
			})
		})
	}
	
    //	Cloudinary Upload Widget API call
	$scope.uploadImage = function() {
		console.log("Ready to upload your picture");
		var results;
		var errors;
		$.cloudinary.init();
		$.cloudinary.config({ cloud_name: 'dyllookxn', api_key: '392338393876672'});
		cloudinary.openUploadWidget({ cloud_name: 'dyllookxn', upload_preset: 'perfectHome', form: '#my_image', cropping: 'server'}, 
				function(error, result) 
				{ 
				    if (error != null) {
				    	console.log(error, result);
				    	console.log(error);
				    	errors = error;
				    	console.log(errors.message);
				    }
				    else if (error === null) {
						results = result[0];
						console.log(results);
						console.log(result[0].url);
						console.log(result[0].secure_url);
						console.log(result[0].signature);
						$("#my_image").attr("src",results.secure_url);
						console.log("sending image to userService");
						var newImage = {};
						newImage.url = result[0].secure_url;
						userService.createPhoto(newImage,homeuserId);
				    }
	            });
	}
	
	$scope.addHome = function(home){
		console.log(home)
		userService.addHome(authenticationService.currentUser().id, home)
		.then(function(){
			userService.getUser(authenticationService.currentUser().id)
			.then(function(response){
				$scope.user =  response;
				console.log($scope.user)
			})
		})
	}
	
	$scope.click = function(zillowId, HomeUserId){
		zillowService.getZillowInfo(zillowId)
		.then(function(response){
			console.log(response)
			$scope.activeHome = response;
			$scope.currentHomeUserId = HomeUserId;
			homeuserId = $scope.currentHomeUserId;
			todoService.getTodos(HomeUserId)
			.then(function(response){
				$scope.todos = response.data;
				console.log($scope.todos);
			})
			//$scope.loadNotes();
//			userService.getUser(authenticationService.currentUser().id)
//	        .then(function(response){
//	        	$scope.user = response;
//	        })
		})
	}
	
	var placeSearch, autocomplete, address, zillowSearchAddress;
    
	var componentForm = {
      street_number: 'short_name',
      route: 'long_name',
      locality: 'long_name',
      administrative_area_level_1: 'short_name',
      country: 'long_name',
      postal_code: 'short_name'
    };
	
    function fillInAddress() {
        // Get the place details from the autocomplete object.
        var place = $scope.autocomplete.getPlace();
        // Parse address compenents out of place.
        
        console.log(place);
        
        address = place.address_components;

        var addressNum = address[0].long_name;
        var street = address[1].long_name;
        var city = address[2].long_name;
        var state = address[4].short_name;
        if (state.length > 2) {
        	state = address[5].short_name;
        }

        zillowSearchAddress = makeApiString(addressNum, street, city, state);
    }
    
    // Bias the autocomplete object to the user's geographical location,
    // as supplied by the browser's 'navigator.geolocation' object.
    $scope.geolocate = function() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
          var geolocation = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
          };
          var circle = new google.maps.Circle({
            center: geolocation,
            radius: position.coords.accuracy
          });
          $scope.autocomplete.setBounds(circle.getBounds());
        });
      }
    }

    var makeApiString = function(number, street, city, state) {
      number = number.replace(/ /g , '+');
      state = state.replace(/ /g , '+');
      street = street.replace(/ /g , '+');
      city = city.replace(/ /g , '+');
      var string = 'http://www.zillow.com/webservice/GetDeepSearchResults' +
      '.htm?zws-id=X1-ZWz1fidpnpqc5n_5886x&address=' + number + '+' +
      street + '&citystatezip='+ city +'%2C+'+ state;
      console.log(string);
      userService.getHomeZpid(string)
      		.then(function(response){
      			console.log(response)
      			$scope.zillowResult = response;
      			$scope.activeHome = response;
      			$scope.currentHomeUserId = null;
      			});
    }	
	
    $scope.autocomplete = new google.maps.places.Autocomplete((document.getElementById('autocomplete')),{types: ['geocode']});
    $scope.autocomplete.addListener('place_changed', fillInAddress);

    $scope.submit = function(task){
        console.log("Task Received");
        console.log(homeuserId);
        if (task.task){
          var newTask = {};
          newTask.task = task.task;
          newTask.date = task.date;
          newTask.completed =  false;
          $scope.createTodo(newTask,homeuserId);
          task.task = undefined;
          homeuserId = homeuserId;
        }
     }
     
     $scope.createTodo = function(todo){
  	   console.log("Request received to create selected Task");
       console.log(homeuserId);
       todoService.createTodo(todo,homeuserId)
  	   .then(function(response){
  		   $scope.loadData(homeuserId);
  	   })
     }
     
     $scope.loadData = function(){
       console.log("Reloading Todo's");
       console.log(homeuserId);
  	   todoService.getTodos(homeuserId)
           .then(function(response){
          	 console.log(response);
          	 $scope.todos = response.data;
          	 console.log($scope.todos);
         });
     }
     
     $scope.removeTodo = function(todo){
        console.log("Request received to remove selected Task");
        console.log(homeuserId);
        todoService.removeTodo(todo,homeuserId)
        .then(function(response){
            $scope.loadData(homeuserId);
        });
    }

     $scope.editTodo = function(todo){
        console.log("Request received to edit selected Task");
        console.log(homeuserId);
        todoService.editTodo(todo,homeuserId)
        .then(function(response){
            $scope.loadData(homeuserId);
        });
    }
     
     
     $scope.loadNotes = function(){
         console.log("Reloading Note's");
         console.log(homeuserId);
    	   noteService.getNotes(homeuserId)
             .then(function(response){
            	 console.log(response);
            	 $scope.notes = response.data;
            	 console.log($scope.notes);
           });
       }

     $scope.createNote = function(note) {
    	 console.log(note)
    	 noteService.createNote(note, homeuserId)
    	 .then(function(response){
    		 console.log('Created Note')
    		 $scope.loadNotes();
    		 
    	 })
     }
     
     $scope.editNote = function(note) {
    	 console.log(note)
    	 noteService.editNote(note, homeuserId)
    	  .then(function(response){
    		 console.log(response)
    		 $scope.loadNotes();
    	 })
              
     }
     
     $scope.removeNote = function(note) {
    	 console.log(note)
    	 noteService.removeNote(note, homeuserId)
    	 .then(function(response){
    		 console.log(response)
    		 $scope.loadNotes();
    	 })
     }
     
     
     
     $scope.styleTooMany = function(tasks) {
       return (tasks > 3) ? "yellow" : "green";
     }

});
