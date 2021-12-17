const app = angular.module("shopping-cart-app", []);

app.controller("shoppping-cart-ctrl", function($scope, $http) {
	/**
    	Quan ly san pham yeu thich
    **/
    
    $scope.cartFav = {
    	itemsFav: [],
    	//them san pham yeu thich
    	addFav(id){
    		var item = this.itemsFav.find(item => item.id == id);
    		if(item){
    			
    			item.qty++;
    			this.saveToLocalStorage();
    		}else{
    			$http.get(`/rest/favorite/${id}`).then(resp => {
    				resp.data.qty = 1;
    				this.itemsFav.push(resp.data);
                    this.saveToLocalStorage();
    			})
    		}
    		swal("Thành công!", "Đã thêm sản phẩm yêu thích thành công !", "success");
    	},
    	
    	//xoa san pham yeu thich
    	removeFav(id) {
            var result = confirm("Bạn có muốn xóa sản phẩm yêu thích này ?");
            if (result) {
                var index = this.itemsFav.findIndex(item => item.id == id);
                this.itemsFav.splice(index, 1);
                this.saveToFavLocalStorage();
            }
        },
        
        //xoa sach san pham yeu thich
        clearFav() {
            this.itemsFav = [];
            this.saveToFavLocalStorage();
        },
        
        //luu gio hang vao local storage
        saveToFavLocalStorage() {
            var json = JSON.stringify(angular.copy(this.itemsFav));
            localStorage.setItem("cartFav", json);
        },
        
        loadFromFavLocalStorage() {
            var json = localStorage.getItem("cartFav");
            this.itemsFav = json ? JSON.parse(json) : [];
        }
    }
    
    $scope.cartFav.loadFromFavLocalStorage();
}