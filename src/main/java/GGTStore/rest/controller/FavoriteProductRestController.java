package GGTStore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import GGTStore.entity.FavoriteProduct;
import GGTStore.service.FavoriteProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favorites")
public class FavoriteProductRestController {

	@Autowired
	FavoriteProductService favoriteProductService;
	
	@PostMapping
	public FavoriteProduct create(@RequestBody JsonNode favoriteData) {
		return favoriteProductService.create(favoriteData);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		favoriteProductService.delete(id);
	}
}


