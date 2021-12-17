package GGTStore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import GGTStore.entity.Comment;
import GGTStore.entity.FlashSale;

public interface FlashsaleDAO extends JpaRepository<FlashSale, Integer> {
	
	@Query("SELECT p from FlashSale p where p.category.id=?1")
	List<FlashSale> findByCategoryId(String cid);
	
	List<FlashSale> findByNameContaining(String name);
	
	Optional<FlashSale> findById(Long flashsaleId);
	
	@Query("SELECT p FROM FlashSale p Where p.category.id=?1")
	Page<FlashSale> findByCategory(String cid, Pageable pageable);
	
	@Query("SELECT c FROM Comment c WHERE c.flashsale.id=?1")
	List<Comment> findCommentByFlashsaleId();

}
