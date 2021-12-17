app.controller("product-ctrl", function($scope, $http) {
    $scope.items = [];
    $scope.categories = [];
    $scope.orderDetail = [];
    $scope.form = {};

    $scope.initialize = function() {
        //load order detail
        $http.get("/rest/list/orders").then(resp => {
            $scope.orderDetail = resp.data;
        });

        //load categories
        $http.get("/rest/categories").then(resp => {
            $scope.categories = resp.data;
        });
    }
    $scope.details = function() {
            alert("ok")
        }
        //khoi dau
    $scope.initialize();
})