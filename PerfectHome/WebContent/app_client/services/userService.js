// authenticationService.js

var app = angular.module('ngApp')
app.factory('userService', function($window, $http){
    // Place JWT into local storage
	
	var placeSearch, autocomplete, address;
    
	var componentForm = {
      street_number: 'short_name',
      route: 'long_name',
      locality: 'long_name',
      administrative_area_level_1: 'short_name',
      country: 'long_name',
      postal_code: 'short_name'
    };

    var initAutocomplete = function() {
      // Create the autocomplete object, restricting the search to geographical
      // location types.
      autocomplete = new google.maps.places.Autocomplete(
          /** @type {!HTMLInputElement} */(document.getElementById('autocomplete')),
          {types: ['geocode']});

      // When the user selects an address from the dropdown, populate the address
      // fields in the form.
      autocomplete.addListener('place_changed', fillInAddress);
    }

    function fillInAddress() {
      // Get the place details from the autocomplete object.
      var place = autocomplete.getPlace();
      // Parse address compenents out of place.
      address = place.address_components;

      var addressNum = address[0].long_name;
      var street = address[1].long_name;
      var city = address[2].long_name;
      var state = address[4].short_name;

      makeApiString(addressNum, street, city, state);

      for (var component in componentForm) {
        document.getElementById(component).value = '';
        document.getElementById(component).disabled = false;
      }

      // Get each component of the address from the place details
      // and fill the corresponding field on the form.
      for (var i = 0; i < place.address_components.length; i++) {
        var addressType = place.address_components[i].types[0];
        if (componentForm[addressType]) {
          var val = place.address_components[i][componentForm[addressType]];
          document.getElementById(addressType).value = val;
        }
      }
    }

    // Bias the autocomplete object to the user's geographical location,
    // as supplied by the browser's 'navigator.geolocation' object.
    var geolocate = function() {
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
          autocomplete.setBounds(circle.getBounds());
        });
      }
    }

    var makeApiString = function(number, street, city, state) {
      street = street.replace(/ /g , '+');
      city = city.replace(/ /g , "+");
      var string = 'http://www.zillow.com/webservice/GetDeepSearchResults' +
      '.htm?zws-id=X1-ZWz1fhzcpm7ymj_4zss3&address=' + number + '+' +
      street + '&citystatezip='+ city +'%2C+'+ state;
      console.log(string);
      return string;
    }	

    return {
    	initAutocomplete : initAutocomplete,
    	geolocate : geolocate
    }
});