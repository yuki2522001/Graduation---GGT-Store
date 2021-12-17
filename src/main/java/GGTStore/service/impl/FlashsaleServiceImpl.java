package GGTStore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import GGTStore.dao.FlashsaleDAO;
import GGTStore.entity.Comment;
import GGTStore.entity.FlashSale;
import GGTStore.service.FlashsaleService;

@Service
public class FlashsaleServiceImpl implements FlashsaleService {

	@Autowired
	FlashsaleDAO fdao;
	
	@Override
	public List<FlashSale> findAll() {
		return fdao.findAll();
	}

	@Override
	public FlashSale findById(Integer id) {
		return fdao.findById(id).get();
	}

	@Override
	public List<Comment> findCommentByProductId(Integer id) {
		return fdao.findCommentByFlashsaleId();
	}

	@Override
	public List<FlashSale> findByCategoryId(String cid) {
		return fdao.findByCategoryId(cid);
	}

	@Override
	public FlashSale create(FlashSale flashSale) {
		return fdao.save(flashSale);
	}

	@Override
	public FlashSale update(FlashSale flashSale) {
		return fdao.save(flashSale);
	}

	@Override
	public void delete(Integer id) {
		fdao.deleteById(id);
	}

	@Override
	public List<FlashSale> findByNameContaining(String name) {
		return fdao.findByNameContaining(name);
	}

	@Override
	public long count() {
		return fdao.count();
	}

	@Override
	public Optional<FlashSale> findById(Long flashsaleId) {
		return fdao.findById(flashsaleId);
	}

	@Override
	public Page<FlashSale> findByCategoryId(String string, Pageable pageable) {
		return null;
	}

	@Override
	public Page<FlashSale> findByCategory(String string, Pageable pageable) {
		return fdao.findByCategory(string, pageable);
	}

	@Override
	public Page<FlashSale> findAll(Pageable pageable) {
		return fdao.findAll(pageable);
	}

}
