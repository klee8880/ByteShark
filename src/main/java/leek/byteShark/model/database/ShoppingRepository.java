package leek.byteShark.model.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ShoppingRepository extends CrudRepository <Shopping, Integer>{

	Shopping findById(int id);
	
	List<Shopping> findByCarNumber(int CarNumber);
	
	List<Shopping> findByCarInitial(String carInitial);
	
	List<Shopping> findByCarInitialAndCarNumber(String carInitial, int carNumber);

	List<Shopping> findAll();
} 
