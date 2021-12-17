package GGTStore.dto;

import java.io.Serializable;
import java.util.Date;

import GGTStore.entity.Account;
import GGTStore.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto implements Serializable {

	Integer id;
	String com;
	Date createDate;
	Account account;
	Product product;
}