package ec.edu.espe.arquitectura.wsdemomanagement.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryRS {
    private String code;
    private String name;
    private String phoneCode;
}
