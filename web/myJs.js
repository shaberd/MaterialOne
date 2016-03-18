/**
 * Created by dashab on 3/18/16.
 */
/**
 * You must include the dependency on 'ngMaterial'
 */
var app = angular.module('BlankApp', ['ngMaterial', 'ngMdIcons', 'ngMessages']);
app.controller('myCtrl', function ($scope) {
    $scope.beer = beerDb;
//            $scope.firstName = "John ";
//            $scope.lastName = "Doe";
//            $scope.isOpen = false;
//            $scope.demo = {
//                isOpen: false,
//                count: 0,
//                selectedDirection: 'left'
//            };
    $scope.title = "All my Beers";
});
app.controller('TitleController', function ($scope) {
    $scope.title = 'My App Title';
});