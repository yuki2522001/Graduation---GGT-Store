package GGTStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import GGTStore.entity.FavoriteDetailProduct;

public interface FavoriteDetailProductDAO extends JpaRepository<FavoriteDetailProduct, Long> {

}
