package GGTStore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import GGTStore.dao.FavoriteDetailProductDAO;
import GGTStore.dao.FavoriteProductDAO;
import GGTStore.entity.FavoriteDetailProduct;
import GGTStore.entity.FavoriteProduct;
import GGTStore.service.FavoriteProductService;

@Service
public class FavoriteProductServiceImpl implements FavoriteProductService {

	@Autowired
	FavoriteProductDAO dao;
	
	@Autowired
	FavoriteDetailProductDAO ddao;
	
	@Override
	public FavoriteProduct create(JsonNode favoriteData) {
		ObjectMapper mapper = new ObjectMapper();
		
		FavoriteProduct favoriteProduct = mapper.convertValue(favoriteData, FavoriteProduct.class);
		dao.save(favoriteProduct);
		
		TypeReference<List<FavoriteDetailProduct>> type = new TypeReference<List<FavoriteDetailProduct>>() {};
		List<FavoriteDetailProduct> details = mapper.convertValue(favoriteData.get("favoriteDetails"), type)
				.stream().peek(d -> d.setFavoriteProduct(favoriteProduct)).collect(Collectors.toList());
		ddao.saveAll(details);
		
		return favoriteProduct;
	}

	@Override
	public List<FavoriteProduct> findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public List<FavoriteProduct> findAll() {
		return dao.findAll();
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public long count() {
		return dao.count();
	}

}
