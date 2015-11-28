(function () {
    'use strict';

    angular
        .module('myApp')
        .controller('DetailController', DetailController) // scope is a variable to pass info from javascript to application and template

        DetailController.$inject = ['$scope', '$http', '$routeParams'];

        function DetailController($scope, $http, $routeParams) {
            $http.get('../js/myApp/artists/artists.json').success(function(data) {
                  var artists = data;

                  var whichArtist = parseInt($routeParams.artistId);
                  $scope.artist = artists[whichArtist];

                  if ( whichArtist < artists.length-1 )
                    $scope.nextArtist = whichArtist+1;
                  else
                    $scope.nextArtist = 0;

                  if ( whichArtist == 0 )
                    $scope.previousArtist = artists.length-1;
                  else
                    $scope.previousArtist = whichArtist-1;

                console.log(whichArtist);
                console.log($scope.nextArtist);
                console.log($scope.previousArtist)
            });
        }
}());