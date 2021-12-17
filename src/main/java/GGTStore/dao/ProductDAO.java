package GGTStore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import GGTStore.entity.Comment;
import GGTStore.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {

	@Query("SELECT p from Product p where p.category.id=?1")
	List<Product> findByCategoryId(String cid);

	@Query("SELECT p from Product p where p.producer.id=?1")
	List<Product> findByProducerId(String pid);

	List<Product> findByNameContaining(String name);

	// tim kiem va phan trang
	Page<Product> findByNameContaining(String name, Pageable pageable);

	@Query("SELECT o FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
	Page<Product> findByPrice(double minPrice, double maxPrice, Pageable pageable);

	@Query("SELECT p FROM Product p Where p.category.id=?1")
	Page<Product> findByCategory(String cid, Pageable pageable);

	@Query("SELECT p FROM Product p Where p.producer.id=?1")
	Page<Product> findByProducer(String pid, Pageable pageable);

	@Query(value = "select * from Product where categoryid = ?", nativeQuery = true)
	Page<Product> findAllProductByCategoryId(Long id, Pageable pageable);

	Optional<Product> findById(Long productId);

	@Query("SELECT c FROM Comment c WHERE c.product.id=?1")
	List<Comment> findCommentByProductId();
	
	@Query(nativeQuery = true, value = "select top (8) id,c.[Số lần được mua], available, createdate, desciption, image, name, price, quantity, categoryid, insurance, producerid, commentid, discountid  from Products INNER JOIN (SELECT top 8 orderdetails.productid, count(orderdetails.productid) as \"Số lần được mua\"   FROM orderdetails  GROUP BY orderdetails.productid ORDER BY count(orderdetails.productid) DESC) C ON C.productid =products.id\r\n"
			+ "")
	List<Product> findTop8Product();

	@Query("select o.product.id, count(*) from OrderDetail o inner join Product p on o.product.id= p.id where productid=?1 GROUP BY productid")
	Product findtByProductquantitysold(Optional<String> id);

}
