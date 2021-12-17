package GGTStore.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto implements Serializable {
	String username;
	String password;
	String fullname;
	String email;
	String image;
	String address;
	Boolean gender;
	Date registerDate;
	String phone;
}
