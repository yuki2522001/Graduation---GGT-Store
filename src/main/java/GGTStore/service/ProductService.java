package GGTStore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import GGTStore.entity.Comment;
import GGTStore.entity.Product;

public interface ProductService {

	Product findtByProductquantitysold(Optional<String> id);

	List<Product> findAll();

	Product findById(Integer id);

	List<Comment> findCommentByProductId(Integer id);

	List<Product> findByCategoryId(String cid);
	
	List<Product> findTop8Product();

	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);

	List<Product> findByNameContaining(String name);

	long count();

	Page<Product> findAllPro(Pageable pageable);

	Page<Product> findAll(Pageable pageable);

	Page<Product> findByNameContaining(String name, Pageable pageable);

	Page<Product> findByCategory(String cid, Pageable pageable);

	Page<Product> findByProducer(String pid, Pageable pageable);

	Optional<Product> findById(Long productId);
}
