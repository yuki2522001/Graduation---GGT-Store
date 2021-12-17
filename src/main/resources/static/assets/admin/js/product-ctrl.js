app.controller("product-ctrl", function($scope, $http) {
    $scope.items = [];
    $scope.itemsFlash = [];
    $scope.itemsCategory = [];
    $scope.itemsPercent = [];
    $scope.itemsProducer = [];
    $scope.itemsOrder = [];
    $scope.itemsOrderDetail = [];
    $scope.orderDetail = [];
    $scope.form = {};
    $scope.formFlash = {};
    $scope.formCategory = {};
    $scope.formPercent = {};
    $scope.formProducer = {};
    $scope.formOrder = {};
    $scope.formOrderDetail = {};
    $scope.categories = [];
    $scope.comments = [];
    $scope.producers = [];
    $scope.percents = [];
    $scope.item = -1;


    $scope.initialize = function() {
        //load products
        $http.get("/rest/products").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate)
            })
        });

        //load flash sales
        $http.get("/rest/flashsales").then(resp => {
            $scope.itemsFlash = resp.data;
            $scope.itemsFlash.forEach(item => {
                item.createDate = new Date(item.createDate)
            })
        });

        //load categories
        $http.get("/rest/categories").then(resp => {
            $scope.categories = resp.data;
        });

        //load producers
        $http.get("/rest/producers").then(resp => {
            $scope.producers = resp.data;
        });

        //load order detail
        $http.get("/rest/list/orders").then(resp => {
            $scope.orderDetail = resp.data;
        });

        //load comment
        $http.get("/rest/comments").then(resp => {
            $scope.comments = resp.data;
        });

        //load percent
        $http.get("/rest/percents").then(resp => {
            $scope.percents = resp.data;
            $scope.percents.forEach(item => {
                item.createDate = new Date(item.createDate)
            })
        });
    }
    $scope.edit = function(item) {
        // $scope.item = item;
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show')
    }


    //khoi dau
    $scope.initialize();

    //xoa form
    $scope.reset = function() {
        $scope.item = -1;
        $scope.form = {
            createDate: new Date(),
            image: 'no-image.png',
            available: true
        };
    }

    //xoa form flash sale
    $scope.resetFlashsale = function() {
        $scope.item = -1;
        $scope.formFlash = {
            createDate: new Date(),
            image: 'no-image.png',
            available: true
        };
    }

    //xoa form category
    $scope.resetCategory = function() {
            $scope.item = -1;
            $scope.formCategory = {
                id: "",
                name: ""
            };
        }
        //xoa form prducer
    $scope.resetProducer = function() {
            $scope.item = -1;
            $scope.formProducer = {
                id: "",
                name: ""
            };
        }
        //xoa form discount
    $scope.resetDiscount = function() {
        $scope.item = -1;
        $scope.formPercent = {
            createDate: new Date(),
            id: "",
            name: ""
        };
    }

    //xoa form orderDetail
    $scope.resetOrderDetail = function() {
        $scope.formOrderDetail = {
            id: ""
        };
    }

    //hien thi len form
    $scope.edit = function(item) {
        $scope.item = item;
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show')
    }

    //hien thi flash sale len form
    $scope.editFlashSale = function(item) {
        $scope.item = item;
        $scope.formFlash = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show')
    }

    //hien thi category len form
    $scope.editCategory = function(item) {
        $scope.item = item;
        $scope.formCategory = angular.copy(item);
    }

    //hien thi discount len form
    $scope.editPercent = function(item) {
        $scope.item = item;
        $scope.formPercent = angular.copy(item);
    }

    //hien thi producer len form
    $scope.editProducer = function(item) {
        $scope.item = item;
        $scope.formProducer = angular.copy(item);
    }

    //hien thi orderDetail len form
    $scope.editOrderDetail = function(item) {
            $scope.formOrderDetail = angular.copy(item);
        }
        // Them sp
    $scope.create = function() {
            $scope.item = -1;
            var item = angular.copy($scope.form);
            var name = document.myyform.name.value;
            var image = document.myyform.image.value;
            var price = document.myyform.price.value;
            var quantity = document.myyform.quantity.value;
            // var ids = document.myyform.ids.value;
            // var idc = document.myyform.idc.value;
            var insurance = document.myyform.insurance.value;
            var createDates = document.myyform.createDates.value;
            var available = document.myyform.available.value;
            var status = false;
            if (name.length < 1) {
                document.getElementById("name").innerHTML = "Vui lòng nhập tên sản phẩm";
                status = false;
            } else {
                document.getElementById("name").innerHTML = " ";
                status = true;
            }
            if (image.length < 1) {
                document.getElementById("image").innerHTML = "Vui lòng chọn hình ảnh";
                status = false;
            } else {
                document.getElementById("image").innerHTML = " ";
            }
            if (price.length < 1) {
                document.getElementById("price").innerHTML = "Vui lòng nhập giá sản phẩm";
                status = false;
            } else {
                document.getElementById("price").innerHTML = " ";
                status = true;
            }
            if (quantity.length < 1) {
                document.getElementById("quantity").innerHTML = "Vui lòng nhập số lượng";
                status = false;
            } else {
                document.getElementById("quantity").innerHTML = " ";
                status = true;
            }
            if (createDates.length < 1) {
                document.getElementById("createDates").innerHTML = "Vui lòng nhập ngày tạo";
                status = false;
            } else {
                document.getElementById("createDates").innerHTML = " ";
                status = true;
            }
            if (available.length < 1) {
                document.getElementById("available").innerHTML = "Vui lòng nhập trạng thái";
                status = false;
            } else {
                document.getElementById("available").innerHTML = " ";
                status = true;
            }
            if (insurance.length < 1) {
                document.getElementById("insurance").innerHTML = "Vui lòng chọn gói bảo hành";
                status = false;
            } else {
                document.getElementById("insurance").innerHTML = " ";
                status = true;
            }
            $http.post(`/rest/products`, item).then(resp => {
                resp.data.createDate = new Date(resp.data.createDate)
                $scope.items.push(resp.data);
                $scope.reset();
                $scope.initialize();
                swal("Thành công!", "Đã thêm 1 sản phẩm!", "success");
            }).catch(error => {
                sweetAlert("Thất bại!", "Lỗi thêm mới", "error");
                console.log("Error", error);
            })
        }
        // them flas
    $scope.createflas = function() {
            $scope.item = -1;
            var item = angular.copy($scope.formFlash);
            var name = document.myyform.name.value;
            var image = document.myyform.image.value;
            var price = document.myyform.price.value;
            var quantity = document.myyform.quantity.value;
            var createDate = document.myyform.createDate.value;
            var available = document.myyform.available.value;
            var status = false;
            if (name.length < 1) {
                document.getElementById("name").innerHTML = "Vui lòng nhập tên sản phẩm";
                status = false;
            } else {
                document.getElementById("name").innerHTML = " ";
                status = true;
            }
            if (image.length < 1) {
                document.getElementById("image").innerHTML = "Vui lòng chọn hình ảnh";
                status = false;
            } else {
                document.getElementById("image").innerHTML = " ";
            }
            if (price.length < 1) {
                document.getElementById("price").innerHTML = "Vui lòng nhập giá sản phẩm";
                status = false;
            } else {
                document.getElementById("price").innerHTML = " ";
                status = true;
            }
            if (quantity.length < 1) {
                document.getElementById("quantity").innerHTML = "Vui lòng nhập số lượng";
                status = false;
            } else {
                document.getElementById("quantity").innerHTML = " ";
                status = true;
            }
            if (createDate.length < 1) {
                document.getElementById("createDate").innerHTML = "Vui lòng nhập ngày nhập hàng";
                status = false;
            } else {
                document.getElementById("createDate").innerHTML = " ";
                status = true;
            }
            if (available.length < 1) {
                document.getElementById("available").innerHTML = "Vui lòng nhập trạng thái";
                status = false;
            } else {
                document.getElementById("available").innerHTML = " ";
                status = true;
            }
            $http.post(`/rest/flashsales`, item).then(resp => {
                resp.data.createDate = new Date(resp.data.createDate)
                $scope.itemsFlash.push(resp.data);
                $scope.resetFlashsale();
                $scope.initialize();
                swal("Thành công!", "Đã thêm 1 flash sale!", "success");
            }).catch(error => {
                sweetAlert("Thất bại!", "Lỗi thêm mới", "error");
                console.log("Error", error);
            })
        }
        //them danh muc san pham
    $scope.createCategory = function() {
        $scope.item = -1;
        var item = angular.copy($scope.formCategory);
        var name = document.myform.name.value;
        var id = document.myform.id.value;
        var status = false;
        if (id.length < 1) {
            document.getElementById("id").innerHTML =
                "Vui lòng nhập mã";
            status = false;
        } else {
            document.getElementById("id").innerHTML =
                " ";
            status = true;
        }
        if (name.length < 1) {
            document.getElementById("name").innerHTML =
                "Vui lòng nhập danh mục";
            status = false;
        } else {
            document.getElementById("name").innerHTML =
                " ";
            status = true;
        }
        $http.post(`/rest/categories`, item).then(resp => {
            $scope.items.push(resp.data);
            $scope.reset();
            $scope.initialize();
            swal("Thành công!", "Đã thêm 1 loại sản phẩm!", "success");
        }).catch(error => {
            sweetAlert("Thất bại!", "Lỗi thêm mới", "error");
            console.log("Error", error);
        })
    }

    //them nha san xuat
    $scope.createProducer = function() {
        $scope.item = -1;
        var item = angular.copy($scope.formProducer);
        var names = document.myyform.names.value;
        var ids = document.myyform.ids.value;
        var status = false;
        if (ids.length < 1) {
            document.getElementById("ids").innerHTML =
                "Vui lòng nhập mã NSX";
            status = false;
        } else {
            document.getElementById("ids").innerHTML =
                " ";
            status = true;
        }
        if (names.length < 1) {
            document.getElementById("names").innerHTML =
                "Vui lòng nhập NSX";
            status = false;
        } else {
            document.getElementById("names").innerHTML =
                " ";
            status = true;
        }
        $http.post(`/rest/producers`, item).then(resp => {
            $scope.items.push(resp.data);
            $scope.reset();
            $scope.initialize();
            swal("Thành công!", "Đã thêm 1 NSX!", "success");
        }).catch(error => {
            sweetAlert("Thất bại!", "Lỗi thêm mới", "error");
            console.log("Error", error);
        })
    }

    //them discount
    $scope.createDiscount = function() {
        $scope.item = -1;
        var item = angular.copy($scope.formPercent);
        var id = document.myform.id.value;
        var nameDiscount = document.myform.nameDiscount.value;
        var percents = document.myform.percents.value;
        var createDate = document.myform.createDate.value;
        var status = false;
        if (id.length < 1) {
            document.getElementById("id").innerHTML =
                "Vui lòng nhập mã giảm giá";
            status = false;
        } else {
            document.getElementById("id").innerHTML =
                " ";
            status = true;
        }
        if (nameDiscount.length < 1) {
            document.getElementById("nameDiscount").innerHTML =
                "Vui lòng nhập tên giảm giá";
            status = false;
        } else {
            document.getElementById("nameDiscount").innerHTML =
                " ";
            status = true;
        }
        if (percents.length < 1) {
            document.getElementById("percents").innerHTML =
                "Vui lòng nhập % giảm giá";
            status = false;
        } else {
            document.getElementById("percents").innerHTML =
                " ";
            status = true;
        }
        if (createDate.length < 1) {
            document.getElementById("createDate").innerHTML =
                "Vui lòng nhập ngày tạo";
            status = false;
        } else {
            document.getElementById("createDate").innerHTML =
                " ";
            status = true;
        }
        $http.post(`/rest/percents`, item).then(resp => {
            $scope.items.push(resp.data);
            $scope.reset();
            $scope.initialize();
            swal("Thành công!", "Đã thêm 1 mã giảm giá!", "success");
        }).catch(error => {
            sweetAlert("Thất bại!", "Lỗi thêm mới", "error");
            console.log("Error", error);
        })
    }

    //cap nhat san pham
    $scope.update = function() {
            var item = angular.copy($scope.form);
            $http.put(`/rest/products/${item.id}`, item).then(resp => {
                var index = $scope.items.findIndex(p => p.id == item.id);
                $scope.items[index] = item;
                swal("Thành công!", "Thông tin sản phẩm đã được cập nhật!", "success");
                $scope.initialize();
            }).catch(error => {
                sweetAlert("Thất bại!", "Lỗi cập nhật thông tin", "error");
                console.log("Error", error);
            });
        }
        //cap nhat flash sale
    $scope.updateflash = function() {
        var item = angular.copy($scope.formFlash);
        $http.put(`/rest/flashsales/${item.id}`, item).then(resp => {
            var index = $scope.itemsFlash.findIndex(p => p.id == item.id);
            $scope.itemsFlash[index] = item;
            swal("Thành công!", "Thông tin lash sale đã được cập nhật!", "success");
            $scope.initialize();
        }).catch(error => {
            sweetAlert("Thất bại!", "Lỗi cập nhật thông tin", "error");
            console.log("Error", error);
        });
    }

    //cap nhat danh muc
    $scope.updateCategory = function() {
        var item = angular.copy($scope.formCategory);
        $http.put(`/rest/categories/${item.id}`, item).then(resp => {
            var index = $scope.itemsCategory.findIndex(p => p.id == item.id);
            $scope.itemsCategory[index] = item;
            $scope.resetCategory();
            $scope.initialize();
            swal("Thành công!", "Thông tin danh mục được cập nhật!", "success");
        }).catch(error => {
            sweetAlert("Thất bại!", "Lỗi cập nhật thông tin", "error");
            console.log("Error", error);
        });
    }

    //cap nhat giam gia
    $scope.updateDiscount = function() {
        var item = angular.copy($scope.formPercent);
        $http.put(`/rest/percents/${item.id}`, item).then(resp => {
            var index = $scope.itemsPercent.findIndex(p => p.id == item.id);
            $scope.itemsPercent[index] = item;
            $scope.resetCategory();
            $scope.initialize();
            swal("Thành công!", "Thông tin giảm giá được cập nhật!", "success");
        }).catch(error => {
            sweetAlert("Thất bại!", "Lỗi cập nhật thông tin", "error");
            console.log("Error", error);
        });
    }

    //cap nhat NSX
    $scope.updateProducer = function() {
        var item = angular.copy($scope.formProducer);
        $http.put(`/rest/producers/${item.id}`, item).then(resp => {
            var index = $scope.itemsProducer.findIndex(p => p.id == item.id);
            $scope.itemsProducer[index] = item;
            $scope.resetProducer();
            $scope.initialize();
            swal("Thành công!", "Thông tin NSX được cập nhật!", "success");
        }).catch(error => {
            sweetAlert("Thất bại!", "Lỗi cập nhật thông tin", "error");
            console.log("Error", error);
        });
    }

    //cap nhat don hang chi tiet
    $scope.updateOrderdetail = function() {
        var item = angular.copy($scope.formOrderDetail);
        $http.put(`/rest/list/orders/${item.id}`, item).then(resp => {
            var index = $scope.itemsOrderDetail.findIndex(p => p.id == item.id);
            $scope.itemsOrderDetail[index] = item;
            $scope.resetOrderDetail();
            $scope.initialize();
            swal("Thành công!", "Cập nhật đơn hàng thành công!", "success");
        }).catch(error => {
            sweetAlert("Thất bại!", "Vui lòng điền đầy đủ thông tin", "error");
            console.log("Error", error);
        })
    }

    // xoa sp
    $scope.delete = function(item) {
        swal({
            title: "Bạn có muốn xóa sản phẩm này không?",
            icon: "info",
            buttons: true,
            dangerMode: true,
        }).then((purchase) => {
            if (purchase) {
                swal("Thành công! Sản phẩm đã được xóa", {
                    icon: "success",
                });
                $http.delete(`/rest/products/${item.id}`).then(resp => {
                    var index = $scope.items.findIndex(p => p.id == item.id);
                    $scope.items.splice(index, 1);
                    $scope.reset();
                    $scope.initialize();
                })
            } else {
                swal("Hủy thao tác!");
            }
        });
    }

    // xoa flash
    $scope.deleteflash = function(item) {
        swal({
            title: "Bạn có muốn xóa flash sale này không?",
            icon: "info",
            buttons: true,
            dangerMode: true,
        }).then((purchase) => {
            if (purchase) {
                swal("Thành công! Flash sale đã được xóa", {
                    icon: "success",
                });
                $http.delete(`/rest/flashsales/${item.id}`).then(resp => {
                    var index = $scope.itemsFlash.findIndex(p => p.id == item.id);
                    $scope.itemsFlash.splice(index, 1);
                    $scope.reset();
                    $scope.initialize();
                })
            } else {
                swal("Hủy thao tác!");
            }
        });
    }

    $scope.deleteDiscount = function(item) {
        swal({
            title: "Bạn có muốn xóa sản phẩm này không?",
            icon: "info",
            buttons: true,
            dangerMode: true,
        }).then((purchase) => {
            if (purchase) {
                swal("Thành công! Sản phẩm đã được xóa", {
                    icon: "success",
                });
                $http.delete(`/rest/percents/${item.id}`).then(resp => {
                    var index = $scope.itemsPercent.findIndex(p => p.id == item.id);
                    $scope.itemsPercent.splice(index, 1);
                    $scope.resetDiscount();
                    $scope.initialize();
                })
            } else {
                swal("Hủy thao tác!");
            }
        });
    }

    $scope.deleteCategory = function(item) {
        swal({
            title: "Bạn có muốn xóa sản phẩm này không?",
            icon: "info",
            buttons: true,
            dangerMode: true,
        }).then((result) => {
            if (result) {
                swal("Thành công! Sản phẩm đã được xóa", {
                    icon: "success",
                });
                $http.delete(`/rest/categories/${item.id}`).then(resp => {
                    var index = $scope.itemsCategory.findIndex(p => p.id == item.id);
                    $scope.itemsCategory.splice(index, 1);
                    $scope.resetCategory();
                    $scope.initialize();
                })
            } else {
                swal("Hủy thao tác!");
            }
        });
    }
    $scope.deleteProducer = function(prod) {
        swal({
            title: "Bạn có muốn xóa NSX này không?",
            icon: "info",
            buttons: true,
            dangerMode: true,
        }).then((result) => {
            if (result) {
                swal("Thành công! NSX đã được xóa", {
                    icon: "success",
                });
                $http.delete(`/rest/producers/${prod.id}`).then(resp => {
                    var index = $scope.itemsProducer.findIndex(p => p.id == prod.id);
                    $scope.itemsProducer.splice(index, 1);
                    $scope.resetProducer();
                    $scope.initialize();
                })
            } else {
                swal("Hủy thao tác!");
            }
        });
    }

    $scope.deleteOrder = function(item) {
        swal({
            title: "Bạn có muốn xóa đơn hàng này không?",
            icon: "info",
            buttons: true,
            dangerMode: true,
        }).then((result) => {
            if (result) {
                swal("Thành công! Đơn hàng đã được xóa", {
                    icon: "success",
                });
                $http.delete(`/rest/orders/${item.id}`).then(resp => {
                    var index = $scope.itemsOrder.findIndex(p => p.id == item.id);
                    $scope.itemsOrder.splice(index, 1);
                    $scope.resetOrderDetail();
                    alert("Xóa đơn hàng thành công");
                    $scope.initialize();
                })
            } else {
                swal("Hủy thao tác!");
            }
        });
    }

    $scope.deleteOrderDetail = function(item) {
        swal({
            title: "Bạn có muốn xóa đơn hàng này không?",
            icon: "info",
            buttons: true,
            dangerMode: true,
        }).then((result) => {
            if (result) {
                swal("Thành công! Đơn hàng đã được xóa", {
                    icon: "success",
                });
                $http.delete(`/rest/list/orders/${item.id}`).then(resp => {
                    var index = $scope.itemsOrderDetail.findIndex(p => p.id == item.id);
                    $scope.itemsOrderDetail.splice(index, 1);
                    $scope.initialize();
                })
            } else {
                swal("Hủy thao tác!");
            }
        });

    }

    $scope.imageChanged = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Không upload được hình ảnh");
            console.log("Error ", error);
        })
    }

    $scope.imageChangedCategory = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.formCategory.image = resp.data.name;
        }).catch(error => {
            alert("Không upload được hình ảnh");
            console.log("Error ", error);
        })
    }

    $scope.imageChangedFlash = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.formFlash.image = resp.data.name;
        }).catch(error => {
            alert("Không upload được hình ảnh");
            console.log("Error ", error);
        })
    }

    $scope.pager = {
        page: 0, //begin
        size: 10, //pageSize
        get items() {
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.items.length / this.size)
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
    $scope.orderByMe = function(x) {
        $scope.myOrderBy = x;
    }
    $scope.pagers = {
        page: 0, //begin
        size: 5, //pageSize
        get categories() {
            var start = this.page * this.size;
            return $scope.categories.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.categories.length / this.size)
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
    $scope.pagersod = {
        page: 0, //begin
        size: 8, //pageSize
        get orderDetail() {
            var start = this.page * this.size;
            return $scope.orderDetail.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.orderDetail.length / this.size)
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

    // producer
    $scope.pagersprd = {
            page: 0, //begin
            size: 5, //pageSize
            get producers() {
                var start = this.page * this.size;
                return $scope.producers.slice(start, start + this.size);
            },
            get count() {
                return Math.ceil(1.0 * $scope.producers.length / this.size)
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
        // discount
    $scope.pagersdicount = {
        page: 0, //begin
        size: 5, //pageSize
        get percents() {
            var start = this.page * this.size;
            return $scope.percents.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.percents.length / this.size)
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