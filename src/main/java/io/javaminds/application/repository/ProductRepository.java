package io.javaminds.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.javaminds.application.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> findAllByCategory_Id(int id);

}
