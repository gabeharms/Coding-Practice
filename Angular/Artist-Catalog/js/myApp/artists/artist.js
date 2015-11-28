(function () {
    'use strict';

    angular
        .module('myApp')
        .controller('ArtistController', ArtistController) // scope is a variable to pass info from javascript to application and template

        ArtistController.$inject = ['$scope', '$http'];

        function ArtistController($scope, $http) {
            $http.get('../js/myApp/artists/artists.json').success(function(data) {
                $scope.artists = data;
                $scope.artistOrder = 'name';
            });
        }
}());