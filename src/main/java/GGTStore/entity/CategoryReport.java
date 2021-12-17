package GGTStore.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CategoryReport")
public class CategoryReport implements Serializable {

	@Id
	Category category;
	Long quantity;
	Double grossRevenue;
}
