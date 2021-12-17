package GGTStore.dao;

import java.util.Date;
// import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import GGTStore.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {

	@Query(nativeQuery = true, value = "select count(status) from OrderDetails where status='4'")
	long orderDetailSuccess();

	@Query(nativeQuery = true, value = "select count(status) from OrderDetails where status='3'")
	long orderDetailFail();

	@Query(nativeQuery = true, value = "select count(status) from OrderDetails where status='2'")
	long orderDetailCancel();

	@Query(nativeQuery = true, value = "select count(status) from OrderDetails where status='1'")
	long orderDetailOnAir();

	@Query(nativeQuery = true, value = "select SUM(price * quantity) from OrderDetails where status='4'")
	long totalRevenue();

	@Query(nativeQuery = true, value = "select count(quantity) from orderDetails")
	long totalQuantity();

	@Query(value = "select c.name, count(p) from Product p inner join Category c on p.category.id = c.id group by c.name")
	List<Object[]> getloaihang();

	@Query(value = "SELECT new Report(d.product.category, sum(d.price * d.quantity), sum(d.quantity)) FROM OrderDetail d GROUP BY d.product.category")
	List<Object[]> getTotalReport();

	@Query("select MONTH(o.createDate), sum(d.price * d.quantity) from Order o inner join  OrderDetail d on o.id = d.order.id"
			+ " GROUP BY MONTH(o.createDate)")
	List<Object[]> getAmount();

	@Query("SELECT o FROM OrderDetail o  WHERE o.order.id=?1")
	List<OrderDetail> getAmount(Long orderId);

	@Query(value="select MONTH(o.createDate), sum(d.price * d.quantity) from Order o inner join OrderDetail d on o.id = d.order.id"
	+ " where o.createDate between ?1 and ?2 GROUP BY MONTH(o.createDate)")
	List<Object[]> getDate(Date startDate, Date endDate);

}
