package ec.edu.espe.arquitectura.wsdemomanagement.controller.dto;


import ec.edu.espe.arquitectura.wsdemomanagement.model.Country;
import ec.edu.espe.arquitectura.wsdemomanagement.model.GeoLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeoStructureRS {
    private Integer levelCode;
    private String name;
    private Country country;
    private List<GeoLocation> locations;
}
