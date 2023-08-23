package ec.edu.espe.arquitectura.wsdemomanagement.controller;
import ec.edu.espe.arquitectura.wsdemomanagement.controller.dto.GeoStructureRQ;
import ec.edu.espe.arquitectura.wsdemomanagement.controller.dto.GeoStructureRS;
import ec.edu.espe.arquitectura.wsdemomanagement.service.GeoStructureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/geo-structure")
public class GeoStrucutreController {
    private final GeoStructureService geoStructureService;

    public GeoStrucutreController(GeoStructureService geoStructureService) {
        this.geoStructureService = geoStructureService;
    }

    @PostMapping
    public ResponseEntity<?> createGeoStructure(@RequestBody GeoStructureRQ geoStructure, @RequestParam String countryCode) {
        this.geoStructureService.createGeoStructure(geoStructure, countryCode);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/locations/{countryCode}")
    public ResponseEntity<GeoStructureRS> obtainProvincesFromCountry(@RequestParam Integer levelCode, @PathVariable String countryCode) {
        GeoStructureRS geoStructureRS = this.geoStructureService.obtainStructureFromCountry(levelCode, countryCode);
        return ResponseEntity.ok().body(geoStructureRS);
    }

}
