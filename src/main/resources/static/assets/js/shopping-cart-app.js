const app = angular.module("shopping-cart-app", []);

app.controller("shoppping-cart-ctrl", function($scope, $http) {
    /**
    	Quan ly gio hàng
    **/
    $scope.cart = {
        items: [],
        //them san pham vao gio hang
        add(id) {
            //alert(id)
            var item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
            swal("Thành công!", "Đã thêm vào giỏ hàng!", "success");
        },
        addflash(id) {
            //alert(id)
            var item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/flashsales/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
            swal("Thành công!", "Đã thêm vào giỏ hàng!", "success");
        },

        // xoa san pham khoi gio hang
        remove(id) {
            var result = confirm("Bạn có muốn xóa sản phẩm khỏi giỏ hàng ?");
            if (result) {
                var index = this.items.findIndex(item => item.id == id);
                this.items.splice(index, 1);
                this.saveToLocalStorage();
            }
        },

        //xoa sach mat hang trong gio
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },

        //tinh thanh tien 1 san pham
        amt_of(item) {},

        //tinh tong so luong cac mat hang trong gio
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },

        //tong thanh tien cac mat hang trong gio
        get amount() {
            return this.items
                .map(item => item.qty * (item.price - (item.price * item.discount.percents / 100)))
                .reduce((total, qty) => total += qty, 0)
        },

        //luu gio hang vao local storage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },

        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    }

    $scope.cart.loadFromLocalStorage();

    $scope.reset = function() {
        $scope.items = -1;
    }

    $scope.order = {
        createDate: new Date(),
        address: "",
        ward: "",
        district: "",
        city: "",
        zipcode: "",
        note: "",
        account: { username: $("#username").text() },
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    price: item.price - (item.price * item.discount.percents / 100),
                    // item.setPrice(p.get().getUnitPrice() - p.get().getUnitPrice() * p.get().getDiscount.percents() / 100);
                    quantity: item.qty
                }
            });
        },
        purchase() {
            var order = angular.copy(this);
            // thuc hien dat hang
            swal({
                title: "Bạn có muốn đặt đơn hàng này không?",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            }).then((purchase) => {
                if (purchase) {
                    swal("Thành công! Đơn hàng của bạn đã được đặt", {
                        icon: "success",
                    });
                    $http.post("/rest/orders", order).then(resp => {
                        $scope.cart.clear();
                        location.href = "/order/checkout/success";
                    })
                } else {
                    swal("Hủy đơn!");
                }
            });
        }
    }

    /**
    	Quan ly san pham yeu thich
    **/

    $scope.cartFav = {
        itemsFav: [],
        //them san pham yeu thich
        addFav(id) {
            var item = this.itemsFav.find(item => item.id == id);
            if (item) {
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.itemsFav.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
            swal("Thành công!", "Đã thêm sản phẩm yêu thích !", "success");
        },

        //xoa san pham yeu thich
        removeFav(id) {
            var result = confirm("Bạn có muốn xóa sản phẩm yêu thích này ?");
            if (result) {
                var index = this.itemsFav.findIndex(item => item.id == id);
                this.itemsFav.splice(index, 1);
                this.saveToLocalStorage();
            }
        },

        //xoa sach san pham yeu thich
        clearFav() {
            this.itemsFav = [];
            this.saveToLocalStorage();
        },

        //tinh tong so luong cac mat hang yeu thich dang co
        get countFav() {
            return this.itemsFav
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },

        //luu gio hang vao local storage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.itemsFav));
            localStorage.setItem("cartFav", json);
        },

        loadFromLocalStorage() {
            var json = localStorage.getItem("cartFav");
            this.itemsFav = json ? JSON.parse(json) : [];
        }
    }

    $scope.cartFav.loadFromLocalStorage();








})