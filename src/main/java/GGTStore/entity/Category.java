package GGTStore.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Categories")
public class Category implements Serializable {
	@Id
	String id;
	String name;
	@Column(nullable = false)
	String image;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Product> products;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<FlashSale> flashsale;

}
