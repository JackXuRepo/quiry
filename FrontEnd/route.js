// Please follow this format to create new
// views / controllers / services
// if you want to work on the front end
// Please whenever you add a new view
// or module, please include the
// routing in the section below:
//
// - Jack

// PS: I am using the AngularJS framework of Javascript
// I also added some comments for you to better understand the code
// Let me know if nothing makes sense


// We are declaring a "module" in this line
// "quiryApp" is the name of our main application module
// whatever is on the second parameter, those would be the list of dependency
// module names (ie services, other API names) we will inject for our front-end
// For now we only have ngRoute which handles routing of urls
(function(){
    angular.module("quiryApp", ["ngRoute", "ngAnimate", "ngTagsInput"]);

    angular.module("quiryApp")
    .constant("constant", {
        backEndUrl : "http://localhost:8080"
    });

    // Whenever u see "$something", those are AngularJS services 
    // Any service that is provided by the angularJS framework
    // has the prefix "$".
    // We are using the default router the is provided by Angular
    angular.module("quiryApp")
        .config(function($routeProvider) {
        // This binds each unique url request to a view (HTML) and a
        // Controller module (ie: .js) that handles the actions from
        // view. (Controller classes will then handle the request
        // by calling Services injected into it)
        $routeProvider
        .when("/", {
            templateUrl : "index.html",
            controller : "homeController"
        })
        .when("/login", {
            templateUrl : "login.html",
            controller : "loginController"
        })
        .when("/registration", {
            templateUrl : "registration.html",
            controller : "registrationController"
        })
        .when("/upload", {
            templateUrl : "upload.html",
            controller : "uploadController"
        })
        .otherwise({
            redirectTo:"/"
        });
        // Usually this would be a 404 error
        // redirectTo:"/404", but lets just return to main
    });
})();



