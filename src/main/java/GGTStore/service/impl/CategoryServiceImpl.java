package GGTStore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GGTStore.dao.CategoryDAO;
import GGTStore.entity.Category;
import GGTStore.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDAO cdao;

	@Override
	public List<Category> findAll() {
		return cdao.findAll();
	}

	@Override
	public <S extends Category> S save(S entity) {
		return cdao.save(entity);
	}

	@Override
	public Category findById(String id) {
		return cdao.findById(id).get();
	}

	@Override
	public Category create(Category category) {
		return cdao.save(category);
	}

	@Override
	public Category update(Category category) {
		return cdao.save(category);
	}

	@Override
	public void delete(String id) {
		cdao.deleteById(id);
	}

	public long count() {
		return cdao.count();
	}
	
	
}
