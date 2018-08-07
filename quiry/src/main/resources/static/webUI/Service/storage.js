(function(){
	angular.module("quiryApp")
		.factory("StorageService", storageService);

		function storageService() {
        	return {
		    setValue: function(key, value) {
		        window.localStorage.setItem(key, JSON.stringify(value));
		    },
		    getValue: function(key) {
		        try {
		            return JSON.parse(window.localStorage.getItem(key));
		        } catch (e) {

		        }
		    },
		    removeValue: function(key){
		        try {
		            return JSON.parse(window.localStorage.removeItem(key));
		        } catch (e) {

		        }
		    }
		}
	    }
})();

