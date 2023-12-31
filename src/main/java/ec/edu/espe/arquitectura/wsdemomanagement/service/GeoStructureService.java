package ec.edu.espe.arquitectura.wsdemomanagement.service;

import ec.edu.espe.arquitectura.wsdemomanagement.repository.CountryRepository;
import ec.edu.espe.arquitectura.wsdemomanagement.repository.GeoLocationRepository;
import ec.edu.espe.arquitectura.wsdemomanagement.repository.GeoStructureRepository;
import ec.edu.espe.arquitectura.wsdemomanagement.model.Country;
import ec.edu.espe.arquitectura.wsdemomanagement.model.GeoLocation;
import ec.edu.espe.arquitectura.wsdemomanagement.model.GeoStructure;
import ec.edu.espe.arquitectura.wsdemomanagement.controller.dto.GeoStructureRQ;
import ec.edu.espe.arquitectura.wsdemomanagement.controller.dto.GeoStructureRS;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Service
public class GeoStructureService {
    private final GeoStructureRepository geoStructureRepository;
    private final GeoLocationRepository geoLocationRepository;
    private final CountryRepository countryRepository;
    private final GeoLocationService geoLocationService;

    public GeoStructureService(GeoStructureRepository geoStructureRepository, GeoLocationRepository geoLocationRepository, CountryRepository countryRepository, GeoLocationService geoLocationService) {
        this.geoStructureRepository = geoStructureRepository;
        this.geoLocationRepository = geoLocationRepository;
        this.countryRepository = countryRepository;
        this.geoLocationService = geoLocationService;
    }

    public void createGeoStructure(GeoStructureRQ geoStructureRQ, String countryCode) {
        try {
            GeoStructure geoStructure = transformGeoStructureRQ(geoStructureRQ);
            Country country = this.countryRepository.findByCode(countryCode);
            switch (geoStructure.getLevelCode()) {
                case 1 -> {
                    List<GeoLocation> provinceList = this.geoLocationRepository.findGeoLocationByZipCodeAndLocationParent("170101", country.getCode());
                    geoStructure.setLocations(provinceList);
                    geoStructure.setState("ACT");
                    geoStructure.setCountry(country);
                    this.geoStructureRepository.save(geoStructure);
                }
                case 2 -> {
                    List<GeoLocation> cantons = this.geoLocationRepository.findGeoLocationByZipCode("200202");
                    List<GeoLocation> provinceList = this.geoLocationRepository.findGeoLocationByZipCodeAndLocationParent("170101", country.getCode());
                    List<GeoLocation> cantonList = new ArrayList<>();
                    for (GeoLocation ctns : cantons) {
                        for (GeoLocation prv : provinceList) {
                            if (ctns.getLocationParent().equals(prv.getId())) {
                                cantonList.add(ctns);
                            }
                        }
                    }
                    geoStructure.setLocations(cantonList);
                    geoStructure.setState("ACT");
                    geoStructure.setCountry(country);
                    this.geoStructureRepository.save(geoStructure);
                }
                case 3 -> {
                    List<GeoLocation> parishes = this.geoLocationRepository.findGeoLocationByZipCode("230303");
                    List<GeoLocation> cantonList = this.geoLocationRepository.findGeoLocationByZipCode("200202");
                    List<GeoLocation> parishList = new ArrayList<>();
                    for (GeoLocation prsh : parishes) {
                        for (GeoLocation ctns : cantonList) {
                            if (prsh.getLocationParent().equals(ctns.getId())) {
                                parishList.add(prsh);
                            }
                        }
                    }
                    geoStructure.setLocations(parishList);
                    geoStructure.setState("ACT");
                    geoStructure.setCountry(country);
                    this.geoStructureRepository.save(geoStructure);
                }
                default -> throw new RuntimeException("Error al crear la estructura geográfica");
            }

        } catch(
                RuntimeException rte)

        {
            throw new RuntimeException("Error al crear la estructura geográfica", rte);
        }

    }

    public GeoStructureRS obtainStructureFromCountry(Integer levelCode, String countryCode) {
        try {
            GeoStructure geoStructure = this.geoStructureRepository.findByLevelCodeAndCountryCode(levelCode, countryCode);
            GeoStructureRS response = responseGeoStructure(geoStructure);
            return response;
        } catch (RuntimeException rte) {
            throw new RuntimeException("Error al obtener las provincias del país", rte);
        }
    }


    public GeoStructure transformGeoStructureRQ(GeoStructureRQ rq) {
        GeoStructure geoStructure = GeoStructure
                .builder()
                .levelCode(rq.getLevelCode())
                .name(rq.getName())
                .build();
        return geoStructure;
    }

    public GeoStructureRS responseGeoStructure(GeoStructure geoStructure) {
        GeoStructureRS geoStructureRS = GeoStructureRS
                .builder()
                .levelCode(geoStructure.getLevelCode())
                .name(geoStructure.getName())
                .country(geoStructure.getCountry())
                .locations(geoStructure.getLocations())
                .build();
        return geoStructureRS;
    }


}
