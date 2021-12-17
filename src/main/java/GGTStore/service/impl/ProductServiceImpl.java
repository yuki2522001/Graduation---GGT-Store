package GGTStore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import GGTStore.dao.ProductDAO;
import GGTStore.entity.Comment;
import GGTStore.entity.Product;
import GGTStore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO pdao;


	@Override
	public List<Product> findAll() {
		return pdao.findAll();
	}

	@Override
	public List<Comment> findCommentByProductId(Integer id) {
		return pdao.findCommentByProductId();
	}

	@Override
	public Product findById(Integer id) {
		return pdao.findById(id).get();
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		return pdao.findByCategoryId(cid);
	}

	@Override
	public Product create(Product product) {
		return pdao.save(product);
	}

	@Override
	public Product update(Product product) {
		return pdao.save(product);
	}

	@Override
	public void delete(Integer id) {
		pdao.deleteById(id);
	}

	@Override
	public List<Product> findByNameContaining(String name) {
		return pdao.findByNameContaining(name);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return pdao.count();
	}

	@Override
	public Page<Product> findAllPro(Pageable pageable) {
		return pdao.findAll(pageable);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return pdao.findAll(pageable);
	}

	@Override
	public Page<Product> findByNameContaining(String name, Pageable pageable) {
		return pdao.findByNameContaining(name, pageable);
	}

	@Override
	public Page<Product> findByCategory(String cid, Pageable pageable) {
		return pdao.findByCategory(cid, pageable);
	}

	@Override
	public Page<Product> findByProducer(String pid, Pageable pageable) {
		return pdao.findByProducer(pid, pageable);
	}

	@Override
	public Optional<Product> findById(Long productId) {
		return pdao.findById(productId);
	}

	@Override
	public List<Product> findTop8Product() {
		return pdao.findTop8Product();
	}

	@Override
	public Product findtByProductquantitysold(Optional<String> id) {
		return pdao.findtByProductquantitysold(id);
	}

}
