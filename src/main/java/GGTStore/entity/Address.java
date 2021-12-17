package GGTStore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Address")
public class Address implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 45, nullable = false )
    String first_name;
    @Column(length = 45, nullable = false )
    String last_name;
    @Column(length = 15, nullable = false )
    String phone_number;
    @Column(length =64, nullable = false )
    String address_line1;
    @Column(length =64, nullable = false )
    String address_line2;
    @Column(length =45, nullable = false )
    String city;
    @Column(length =45, nullable = false )
    String state;
    @Column(length =10, nullable = false )
    String postal_code;
    
    @ManyToOne
    @JoinColumn(name = "username")
    Account account;
}
