app.controller("authority-ctrl", function($scope, $http, $location) {
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];
    $scope.authoritiesCust = [];
    $scope.users = [];
    $scope.itemsUser = [];
    $scope.form = {};

    $scope.statisticalUsers = [];
    $scope.statisticalCategories = [];
    $scope.statisticalProducts = [];
    $scope.statisticalOrders = [];
    $scope.statisticalOrderDetails = [];

    $scope.orderDetailSuccess = [];
    $scope.orderDetailFail = [];
    $scope.orderDetailOnAir = [];
    $scope.orderDetailCancel = [];
    $scope.totalRevenue = [];
    $scope.totalQuantity = [];

    $scope.authorityDirector = [];
    $scope.authorityStaff = [];
    $scope.item = -1;

    $scope.initialize = function() {
        //load all roles
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        })
        $http.get("/rest/accounts").then(resp => {
                $scope.admins = resp.data;
            })
            //load staffs and directors (adminstrators)
        $http.get("/rest/accounts?admin=true").then(resp => {
            $scope.admins = resp.data;
        })

        //load customers
        $http.get("/rest/accounts/user?cust=true").then(resp => {
            $scope.cust = resp.data;
        })

        //load authorities of staffs and directors
        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {})

        //load authorities of customers
        $http.get("/rest/authorities/user?cust=true").then(resp => {
            $scope.authoritiesCust = resp.data;
        }).catch(error => {})

        //load all users
        $http.get("/rest/accounts/users").then(resp => {
            $scope.users = resp.data;
        })

        //load statistical users
        $http.get("/rest/statistical/users").then(resp => {
            $scope.statisticalUsers = resp.data;
        })

        //load statistical categories
        $http.get("/rest/statistical/categories").then(resp => {
            $scope.statisticalCategories = resp.data;
        })

        //load statistical products
        $http.get("/rest/statistical/products").then(resp => {
            $scope.statisticalProducts = resp.data;
        })

        //load statistical orders
        $http.get("/rest/statistical/orders").then(resp => {
            $scope.statisticalOrders = resp.data;
        })

        //load statistical orders details
        $http.get("/rest/statistical/orderDetails").then(resp => {
            $scope.statisticalOrderDetails = resp.data;
        })

        //load all orderDetail success
        $http.get("/rest/list/orders/success").then(resp => {
            $scope.orderDetailSuccess = resp.data;
        })

        //load all orderDetail fail
        $http.get("/rest/list/orders/fail").then(resp => {
            $scope.orderDetailFail = resp.data;
        })

        //load all orderDetail onair
        $http.get("/rest/list/orders/onair").then(resp => {
            $scope.orderDetailOnAir = resp.data;
        })

        //load all orderDetail cancel
        $http.get("/rest/list/orders/cancel").then(resp => {
            $scope.orderDetailCancel = resp.data;
        })

        //load totalRevenue
        $http.get("/rest/list/orders/totalRevenue").then(resp => {
            $scope.totalRevenue = resp.data;
        })

        //load totalQuantity
        $http.get("/rest/list/orders/totalQuantity").then(resp => {
            $scope.totalQuantity = resp.data;
        })

        //load authorityDirector
        $http.get("/rest/authorities/directors").then(resp => {
            $scope.authorityDirector = resp.data;
        })

        //load authorityStaff
        $http.get("/rest/authorities/staffs").then(resp => {
            $scope.authorityStaff = resp.data;
        })
    }

    //xoa form
    $scope.reset = function() {
        $scope.form = {
            image: 'no-image.png',
        };
    }

    $scope.authority_of = function(acc, role) {
        if ($scope.authorities) {
            return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
        }
    }

    $scope.authority_of_cust = function(acc, role) {
        if ($scope.authoritiesCust) {
            return $scope.authoritiesCust.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
        }
    }

    $scope.authority_changed = function(acc, role) {
        var authority = $scope.authority_of(acc, role);
        if (authority) { //da cap quyen => thu hoi quyen (xoa)
            $scope.revoke_authority(authority);
        } else { //chua duoc cap quyen => cap quyen(them moi)
            authority = { account: acc, role: role };
            $scope.grant_authority(authority);
        }
    }

    $scope.authority_changed_cust = function(acc, role) {
        var authority = $scope.authority_of_cust(acc, role);
        if (authority) { //da cap quyen => thu hoi quyen (xoa)
            $scope.revoke_authority(authority);
        } else { //chua duoc cap quyen => cap quyen(them moi)
            authority = { account: acc, role: role };
            $scope.grant_authority(authority);
        }
    }

    //them moi authority
    $scope.grant_authority = function(authority) {
        $http.post(`/rest/authorities`, authority).then(resp => {
            $scope.authorities.push(resp.data)
            swal("Thành công!", "Đã cấp quyền sử dụng!", "success");
            $scope.initialize();
        }).catch(error => {
            sweetAlert("Thất bại!", "Lỗi cấp quyền sử dụng", "error");
            console.log("error", error);
        })
    }

    //xoa authority
    $scope.revoke_authority = function(authority) {
        $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
            var index = $scope.authorities.findIndex(a => a.id == authority.id);
            $scope.authorities.splice(index, 1);
            swal("Thành công!", "Đã thu hồi quyền sử dụng!", "success");
            $scope.initialize();
        }).catch(error => {
            sweetAlert("Thất bại!", "Lỗi thu quyền sử dụng", "error");
            console.log("error", error);
        })
    }

    // Xóa form
    $scope.reset = function() {
        $scope.item = -1;
        $scope.form = {
            createDate: new Date(), // ngày mặc định
            image: "no-image.png", // ảnh mắc định
            available: true,
        };
    }

    // HIEN THI LEN FORM
    $scope.edit = function(item) {
            $scope.item = item;
            $scope.form = angular.copy(item);
            $(".nav-tabs a:eq(0)").tab('show')
        }
        // $scope.create = function() {
        //     var item = angular.copy($scope.form);
        //     var username = document.myform.username.value;
        //     var password = document.myform.password.value;
        //     var gender = document.myform.gender.value;
        //     var phone = document.myform.phone.value;
        //     var fullname = document.myform.fullname.value;
        //     var address = document.myform.address.value;
        //     var email = document.myform.email.value;
        //     var atposition = email.indexOf("@");
        //     var dotposition = email.lastIndexOf(".");
        //     var image = document.myform.image.value;
        //     var status = false;
        //     if (username.length < 1) {
        //         document.getElementById("username").innerHTML =
        //             "Vui lòng nhập tên đăng nhập";
        //         status = false;
        //     } else {
        //         document.getElementById("username").innerHTML =
        //             " ";
        //         status = true;
        //     }
        //     if (password.length < 6) {
        //         document.getElementById("password").innerHTML =
        //             "Mật khẩu ít nhất 6 kí tự";
        //         status = false;
        //     } else {
        //         document.getElementById("password").innerHTML =
        //             " ";
        //     }
        //     if (image.length < 1) {
        //         document.getElementById("image").innerHTML =
        //             "Vui lòng chọn hình ảnh";
        //         status = false;
        //     } else {
        //         document.getElementById("image").innerHTML =
        //             " ";
        //     }
        //     if (fullname.length < 1) {
        //         document.getElementById("fullname").innerHTML =
        //             "Vui lòng nhập tên đầy đủ";
        //         status = false;
        //     } else {
        //         document.getElementById("fullname").innerHTML =
        //             " ";
        //         status = true;
        //     }
        //     if (address.length < 1) {
        //         document.getElementById("address").innerHTML =
        //             "Vui lòng nhập địa chỉ";
        //         status = false;
        //     } else {
        //         document.getElementById("address").innerHTML =
        //             " ";
        //         status = true;
        //     }
        //     if (atposition < 1 || dotposition < (atposition + 2) ||
        //         (dotposition + 2) >= email.length) {
        //         document.getElementById("email").innerHTML =
        //             "Email nhập đúng định dạng";
        //         status = false;
        //     } else {
        //         document.getElementById("email").innerHTML =
        //             " ";
        //         status = true;
        //     }
        //     if (gender.length < 1) {
        //         document.getElementById("gender").innerHTML =
        //             "Vui lòng chọn giới tính";
        //         status = false;
        //     } else {
        //         document.getElementById("gender").innerHTML =
        //             " ";
        //         status = true;
        //     }
        //     if (phone.length < 1) {
        //         document.getElementById("phone").innerHTML =
        //             "Vui lòng nhập số điện thoại";
        //         status = false;
        //     } else {
        //         document.getElementById("phone").innerHTML =
        //             " ";
        //         status = true;
        //     }
        //     $http.post(`/rest/accounts`, item).then(resp => {
        //         $scope.itemsUser.push(resp.data);
        //         $scope.reset();
        //         $scope.initialize();
        //         swal("Thành công!", "Đã thêm 1 người dùng!", "success");
        //     }).catch(error => {
        //         sweetAlert("Thất bại!", "Lỗi thêm mới", "error");
        //         console.log("Error", error);
        //     })
        // }

    $scope.update = function() {
        var item = angular.copy($scope.form);
        $http.put(`/rest/accounts/${item.id}`, item).then(resp => {
            var index = $scope.itemsUser.findIndex(p => p.id == item.id);
            $scope.itemsUser[index] = item;
            swal("Thành công!", "Cập nhật thông tin thành công!", "success");
            $scope.reset();
            $scope.initialize();
        }).catch(error => {
            sweetAlert("Thất bại!", "Lỗi cập nhật thông tin", "error");
            console.log("Error", error);
        });
    }

    //xoa account
    $scope.delete = function(item) {
        swal({
            title: "Bạn có muốn xóa người dùng này không?",
            icon: "info",
            buttons: true,
            dangerMode: true,
        }).then((purchase) => {
            if (purchase) {
                swal("Thành công! Người dùng đã được xóa", {
                    icon: "success",
                });
                $http.delete(`/rest/accounts/users/${item.username}`).then(resp => {
                    var index = $scope.itemsUser.findIndex(p => p.username == item.username);
                    $scope.itemsUser.splice(index, 1);
                    $scope.initialize();
                })
            } else {
                swal("Hủy thao tác!");
            }
        });
    }

    // upload hình
    $scope.imageChanged = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images/', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.image = resp.data.name;

        }).catch(error => {
            alert("Lỗi Upload Hình Ảnh");
            console.log("Error", error);
        })
    }
    $scope.initialize();
    $scope.pager = {
        page: 0,
        size: 8,
        get users() {
            var start = this.page * this.size;
            return $scope.users.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.users.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }
});