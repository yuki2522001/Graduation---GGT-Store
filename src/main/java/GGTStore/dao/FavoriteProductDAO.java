package GGTStore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import GGTStore.entity.FavoriteProduct;

public interface FavoriteProductDAO extends JpaRepository<FavoriteProduct, Long> {

	@Query("SELECT o FROM FavoriteProduct o WHERE o.account.username=?1")
	List<FavoriteProduct> findByUsername(String username);
}
