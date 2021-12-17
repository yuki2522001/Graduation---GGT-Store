package GGTStore.service;

import java.util.List;

import GGTStore.entity.Category;

public interface CategoryService {

	List<Category> findAll();
	
	Category findById(String id);
	
	Category create(Category category);
	
	Category update(Category category);
	
	void delete(String id);

	<S extends Category> S save(S entity);

	long count();
}
