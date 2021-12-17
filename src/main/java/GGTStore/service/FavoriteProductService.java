package GGTStore.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import GGTStore.entity.FavoriteProduct;

public interface FavoriteProductService {

	FavoriteProduct create(JsonNode favoriteData);
		
	List<FavoriteProduct> findByUsername(String username);
	
	List<FavoriteProduct> findAll();
	
	void delete(Long id);
	
	void deleteById(Long id);
	
	long count();
}
