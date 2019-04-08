package lintong.service.repository;

import lintong.datamodel.clothes.ClothesInformation;
import org.springframework.data.repository.CrudRepository;

public interface ClothesStateRepository extends CrudRepository<ClothesInformation, Integer> {

}
