package GGTStore.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "FlashSales")
public class FlashSale implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column(nullable = false)
	String name;
	@Column(nullable = false)
	String image;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private double price;
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	Date createDate = new Date();
	@Column(columnDefinition = "nvarchar(4000) not null")
	private String desciption;
	Boolean available;
	@Column(name = "Insurance")
	Boolean insurance;
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	@ManyToOne
	@JoinColumn(name = "Discountid")
	Discount discount;
	@ManyToOne
	@JoinColumn(name = "Producerid")
	Producer producer;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Comment> comments;
	@OneToMany
	List<FavoriteProduct> favoriteProducts;
}
