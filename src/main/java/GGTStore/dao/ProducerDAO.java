package GGTStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import GGTStore.entity.Producer;

public interface ProducerDAO extends JpaRepository<Producer, String> {

}
