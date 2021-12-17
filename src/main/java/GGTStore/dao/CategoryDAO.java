package GGTStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import GGTStore.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String>{

}
