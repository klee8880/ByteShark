package leek.byteShark.model.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RepairLineRepository extends CrudRepository <RepairLine, Integer>{

	List<RepairLine> findAll();
	
}
