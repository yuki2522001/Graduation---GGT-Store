app = angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
        .when("/product", {
            templateUrl: "/assets/admin/product/index.html",
            controller: "product-ctrl"
        })
        .when("/comments", {
            templateUrl: "/assets/admin/product/comment.html",
            controller: "product-ctrl"
        })
        .when("/flashsale", {
            templateUrl: "/assets/admin/product/flashsale.html",
            controller: "product-ctrl"
        })
        .when("/news", {
            templateUrl: "/assets/admin/product/news.html",
            controller: "product-ctrl"
        })
        .when("/feedback", {
            templateUrl: "/assets/admin/product/feedback.html",
            controller: "product-ctrl"
        })
        .when("/category", {
            templateUrl: "/assets/admin/product/_category.html",
            controller: "product-ctrl"
        })
        .when("/orderDetail", {
            templateUrl: "/assets/admin/product/_order-detail.html",
            controller: "product-ctrl"
        })
        .when("/authorize", {
            templateUrl: "/assets/admin/authority/index.html",
            controller: "authority-ctrl"
        })
        .when("/users", {
            templateUrl: "/assets/admin/authority/users.html",
            controller: "authority-ctrl"
        })
        .when("/statistical", {
            templateUrl: "/assets/admin/product/statistical.html",
            controller: "authority-ctrl"
        })
        .when("/chart", {
            templateUrl: "/assets/admin/product/chart.html",
            controller: "thongke-ctrl"
        })
        .when("/charts", {
            templateUrl: "/assets/admin/product/chart.html",
            controller: "thongke-ctrl"
        })
        .when("/unauthoried", {
            templateUrl: "/assets/admin/authority/unauthorized.html",
            controller: "authority-ctrl"
        })
        .otherwise({
            templateUrl: "/assets/admin/home-page.html",
            controller: "thongke-ctrl"
        });
    // var myModal = document.getElementById('myModal')
    // var myInput = document.getElementById('myInput')

    // myModal.addEventListener('shown.bs.modal', function() {
    //     myInput.focus()
    // })
});