package GGTStore.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Discounts")
public class Discount implements Serializable {
	@Id
	String id;
	String nameDiscount;
	Double percents;
	@Temporal(TemporalType.DATE)
	Date createDate = new Date();
	@JsonIgnore
	@OneToMany(mappedBy = "discount")
	List<Product> products;
	@JsonIgnore
	@OneToMany(mappedBy = "discount")
	List<FlashSale> flashsale;
}
