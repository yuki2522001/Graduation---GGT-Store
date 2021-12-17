package GGTStore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import GGTStore.entity.Comment;
import GGTStore.entity.FlashSale;
import GGTStore.entity.Product;

public interface FlashsaleService {

	List<FlashSale> findAll();
	
	FlashSale findById(Integer id);
	
	List<Comment> findCommentByProductId(Integer id);
	
	List<FlashSale> findByCategoryId(String cid);
	
	FlashSale create(FlashSale flashSale);
	
	FlashSale update(FlashSale flashSale);
	
	void delete(Integer id);
	
	Page<FlashSale> findAll(Pageable pageable);
	
	List<FlashSale> findByNameContaining(String name);
	
	long count();
	
	Optional<FlashSale> findById(Long flashsaleId);

	Page<FlashSale> findByCategoryId(String string, Pageable pageable);

	Page<FlashSale> findByCategory(String string, Pageable pageable);
	
}
