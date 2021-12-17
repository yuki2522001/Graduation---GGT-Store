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
@Table(name = "Orders")
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(nullable = false, length = 100)
	String address;
	@Column(name = "Ward", nullable = false, length = 100)
	String ward;
	@Column(name = "District", nullable = false, length = 100)
	String district;
	@Column(name = "City", nullable = false, length = 100)
	String city;
	@Column(name = "Zipcode", length = 6)
	String zipcode;
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	Date createDate = new Date();
	@Column(name = "Status", nullable = false)
	Boolean status;
	@Column(name = "Note", nullable = true, length = 500)
	String note;
	@ManyToOne
	@JoinColumn(name = "Username")
	Account account;
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderDetails;
}