/**
 * Created by dashab on 3/18/16.
 */
/**
 * You must include the dependency on 'ngMaterial'
 */


var app = angular.module('StarterApp', ['ngMaterial', 'ngMdIcons']);

app.controller('AppCtrl', ['$scope', '$mdSidenav', function ($scope, $mdSidenav) {
    $scope.toggleSidenav = function (menuId) {
        $mdSidenav(menuId).toggle();


    }
    var imagePath = 'img/list/60.jpeg';
    $scope.todos = [];
    for (var i = 0; i < 15; i++) {
        $scope.todos.push({
            face: imagePath,
            what: "Brunch this weekend?",
            who: "Min Li Chan",
            notes: "I'll be in your neighborhood doing errands."
        });
    }
    ;
}]);

// var app = angular.module('toolbarDemo2', ['ngMaterial']);
// app.controller('TitleController', function($scope) {
//     $scope.title = 'My App Title';
// });
// app.controller('AppCtrl', function($scope) {
//     var imagePath = 'img/list/60.jpeg';
//     $scope.todos = [];
//     for (var i = 0; i < 15; i++) {
//         $scope.todos.push({
//             face: imagePath,
//             what: "Brunch this weekend?",
//             who: "Min Li Chan",
//             notes: "I'll be in your neighborhood doing errands."
//         });
//     }
// });


/**
 Copyright 2016 Google Inc. All Rights Reserved.
 Use of this source code is governed by an MIT-style license that can be in foundin the LICENSE file at http://material.angularjs.org/license.
 **/

/**
 Copyright 2016 Google Inc. All Rights Reserved.
 Use of this source code is governed by an MIT-style license that can be in foundin the LICENSE file at http://material.angularjs.org/license.
 **/