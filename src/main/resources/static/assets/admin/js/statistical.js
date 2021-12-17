function generateColor() {
    let r = parseInt(Math.random() * 255);
    let g = parseInt(Math.random() * 255);
    let b = parseInt(Math.random() * 255);
    return `rgb(${r}, ${g}, ${b})`
}

function cateChart(labels = [], info = []) {
    const colors = [];
    // 
    for (let i = 0; i < info.length; i++)
        colors.push(generateColor());
    const data = {
        labels: labels,
        datasets: [{
            label: 'Doanh thu',
            data: info,
            backgroundColor: colors,
        }]
    };
    const config = {
        type: 'bar',
        data: data,
        options: {}
    };

    let ctx = document.getElementById('bar-chartcanvas').getContext('2d');

    new Chart(ctx, config);
}

function cateChartLoaiHang(labelsss = [], infoss = []) {
    const colors = [];
    for (let i = 0; i < infoss.length; i++)
        colors.push(generateColor());
    const data = {
        labels: labelsss,
        datasets: [{
            label: 'Loại Hàng',
            data: infoss,
            backgroundColor: colors,
        }]
    };
    const config = {
        type: 'pie',
        data: data,
        options: {}
    };

    let ctx = document.getElementById('bar-loaihang').getContext('2d');

    new Chart(ctx, config);
}

function cateCharts(labelss = [], infos = []) {
    const colors = [];
    for (let i = 0; i < infos.length; i++)
        colors.push(generateColor());
    const data = {
        labels: labelss,
        datasets: [{
            label: 'My First Dataset',
            data: infos,
            backgroundColor: colors,
        }]
    };
    const config = {
        type: 'doughnut',
        data: data,
        options: {}
    };

    let ctx = document.getElementById('bar-amount').getContext('2d');

    new Chart(ctx, config);
}

app.controller("thongke-ctrl", function($scope, $http, $location) {
    let labels = [],
        info = [];
    let labelss = [],
        infos = [];
    let labelsloaihang = [],
        infosloaihang = [];
    $scope.items = [];
    $scope.amount = [];
    $scope.category = [];
    $scope.ngay = [];
    $scope.initialize = function() {
        $http.get(`/rest/reportTotalProduct`).then(resp => {
            $scope.items = resp.data;
            for (let i = 0; i < $scope.items.length; i++) {
                obj = $scope.items[i];
                labelss.push(obj[0].name.name)
                infos.push(obj[0].soLuong)
            }
            cateCharts(labelss, infos)
            console.log($scope.items);
        })
    }
    $scope.reportMonth = function() {
        $http.get(`/rest/reportMonth`).then(resp => {
            $scope.amount = resp.data;
            console.log($scope.amount);
            for (let i = 0; i < $scope.amount.length; i++) {
                obj = $scope.amount[i];
                labels.push(obj[0])
                info.push(obj[1])
            }
            cateChart(labels, info)
            console.log("ok", labels);
        })
    }
    $scope.reportLoaiHang = function() {
        $http.get(`/rest/reportLoaiHang`).then(resp => {
            $scope.category = resp.data;
            console.log($scope.category);
            for (let i = 0; i < $scope.category.length; i++) {
                obj = $scope.category[i];
                labelsloaihang.push(obj[0])
                infosloaihang.push(obj[1])
            }
            cateChartLoaiHang(labelsloaihang, infosloaihang)
            console.log($scope.category);
        })
    }
    $scope.thongkengay = function(startDate, endDate) {
        var url = `/rest/date/${startDate}/${endDate}`;
        $http.get(url).then(resp => {
            $scope.ngay = resp.data;
            console.log("hi", resp.data);
        })
    }
    $scope.reportMonth();
    $scope.initialize();
    $scope.reportLoaiHang();
    $scope.thongkengay();
    $scope.getDay = function() {
        var startDate = $scope.startDate;
        var endDate = $scope.endDate;
        console.log(startDate);
        console.log(endDate);
        $scope.thongkengay(startDate, endDate);
    }
})