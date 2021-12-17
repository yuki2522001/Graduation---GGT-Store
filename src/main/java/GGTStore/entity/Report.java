package GGTStore.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report implements Serializable {
    @Id
    private Category name;
    private Double doanhthu;
    private long soLuong;
}
