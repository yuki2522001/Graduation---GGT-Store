package GGTStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import GGTStore.entity.Discount;

public interface DiscountDAO extends JpaRepository<Discount, String> {

}
