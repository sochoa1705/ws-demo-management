package ec.edu.espe.arquitectura.wsdemomanagement.repository;


import ec.edu.espe.arquitectura.wsdemomanagement.model.GeoStructure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GeoStructureRepository extends MongoRepository<GeoStructure, String> {
    @Query(value = "{'locations._id': ?0}", fields = "{'levelCode': 1, 'name': 1,'locations.$': 1}")
    GeoStructure findGeoStructureByLocations(String locationId);
    @Query(value = "{'levelCode': ?0, 'country.code': ?1}", fields = "{'levelCode': 1, 'name': 1, 'country': 1, 'locations': 1}")
    GeoStructure findByLevelCodeAndCountryCode(Integer levelCode,String countryCode);

}
