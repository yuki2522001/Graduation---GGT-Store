package GGTStore.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "Accounts")
public class Account implements Serializable {
	@Id
	@Column(nullable = false)
	String username;
	@Column(nullable = false)
	String password;
	@Column(columnDefinition = "NVARCHAR(255) NOT NULL")
	String fullname;
	@Column(nullable = false)
	String email;
	@Column(nullable = false)
	String image;
	@Column(length = 6)
	private String phone;
	@Temporal(TemporalType.DATE)
	private Date registerDate;
	@Column()
	private Boolean gender;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	@Column
	String address;
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authorities;
	Boolean isEdit = false;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Comment> comments;
	@OneToMany
	List<FavoriteProduct> favoriteProducts;
}
