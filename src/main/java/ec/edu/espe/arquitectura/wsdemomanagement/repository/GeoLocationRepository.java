package ec.edu.espe.arquitectura.wsdemomanagement.repository;

import ec.edu.espe.arquitectura.wsdemomanagement.model.GeoLocation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GeoLocationRepository extends MongoRepository<GeoLocation,String> {
    List<GeoLocation> findGeoLocationByZipCode(String zipCode);
    List<GeoLocation> findGeoLocationByZipCodeAndLocationParent(String zipCode, String locationParent);
}
