package GGTStore.service.impl;

import java.util.Date;
// import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import GGTStore.dao.OrderDetailDAO;
import GGTStore.entity.OrderDetail;
import GGTStore.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	OrderDetailDAO dao;

	@Override
	public List<Object[]> getDate(Date startDate, Date endDate) {
		return dao.getDate(startDate, endDate);
	}
	@Override
	public List<Object[]> getloaihang() {
		return dao.getloaihang();
	}

	@Override
	public List<OrderDetail> getAmount(Long orderId) {
		return dao.getAmount(orderId);
	}

	@Override
	public List<Object[]> getTotalReport() {
		return dao.getTotalReport();
	}

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return dao.save(entity);
	}

	@Override
	public <S extends OrderDetail> Optional<S> findOne(Example<S> example) {
		return dao.findOne(example);
	}

	@Override
	public Page<OrderDetail> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public List<OrderDetail> findAll() {
		return dao.findAll();
	}

	@Override
	public List<OrderDetail> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public List<OrderDetail> findAllById(Iterable<Long> ids) {
		return dao.findAllById(ids);
	}

	@Override
	public Optional<OrderDetail> findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
		return dao.saveAll(entities);
	}

	@Override
	public void flush() {
		dao.flush();
	}

	@Override
	public <S extends OrderDetail> S saveAndFlush(S entity) {
		return dao.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return dao.saveAllAndFlush(entities);
	}

	@Override
	public <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return dao.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<OrderDetail> entities) {
		dao.deleteInBatch(entities);
	}

	@Override
	public <S extends OrderDetail> long count(Example<S> example) {
		return dao.count(example);
	}

	@Override
	public <S extends OrderDetail> boolean exists(Example<S> example) {
		return dao.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<OrderDetail> entities) {
		dao.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		dao.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(OrderDetail entity) {
		dao.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		dao.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		dao.deleteAllInBatch();
	}

	@Override
	public OrderDetail getOne(Long id) {
		return dao.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends OrderDetail> entities) {
		dao.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public OrderDetail getById(Long id) {
		return dao.getById(id);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example) {
		return dao.findAll(example);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
		return dao.findAll(example, sort);
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Override
	public OrderDetail update(OrderDetail orderDetail) {
		return dao.save(orderDetail);
	}

	@Override
	public long countOrderDetailSuccess() {
		// TODO Auto-generated method stub
		return dao.orderDetailSuccess();
	}

	@Override
	public long countOrderDetailFail() {
		// TODO Auto-generated method stub
		return dao.orderDetailFail();
	}

	@Override
	public long TotalRevenue() {
		// TODO Auto-generated method stub
		return dao.totalRevenue();
	}

	@Override
	public long TotalQuantity() {
		// TODO Auto-generated method stub
		return dao.totalQuantity();
	}

	@Override
	public long countOrderDetailOnAir() {
		// TODO Auto-generated method stub
		return dao.orderDetailOnAir();
	}

	@Override
	public long countOrderDetailCancel() {
		// TODO Auto-generated method stub
		return dao.orderDetailCancel();
	}

	

}
