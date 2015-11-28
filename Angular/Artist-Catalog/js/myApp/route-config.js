(function() {
    'use strict'

    angular
        .module('myApp')
        .config(config)

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider
            .when('/artists', {
                templateUrl: '../js/myApp/artists/artist.html',
                controller: 'ArtistController'
            })
            .when('/details/:artistId', {
                templateUrl: '../js/myApp/details/detail.html',
                controller: 'DetailController'
            });

    }
}());