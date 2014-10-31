var phoneBookApp = angular.module("phoneBookApp", []);

phoneBookApp.controller("PhoneBookController", function($scope){
    $scope.entries = [
        {"lastname":"Somerfield", "firstname": "Daniel"},
        {"lastname":"Foreman", "firstname": "George"},
        {"lastname":"Fowler", "firstname": "Martin"}
    ];

    $scope.$watch('filter', function (key) {
        console.log("$scope.$watch")
    });
});
