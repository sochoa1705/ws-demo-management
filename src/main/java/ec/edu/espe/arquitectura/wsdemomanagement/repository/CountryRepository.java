package ec.edu.espe.arquitectura.wsdemomanagement.repository;

import ec.edu.espe.arquitectura.wsdemomanagement.model.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CountryRepository extends MongoRepository<Country, String>{
    Country findByCode(String code);
    Country findByName(String name);
    @Override
    List<Country> findAll();
}
