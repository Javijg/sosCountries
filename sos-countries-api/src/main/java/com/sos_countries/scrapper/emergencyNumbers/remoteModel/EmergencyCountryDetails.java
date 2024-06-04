package com.sos_countries.scrapper.emergencyNumbers.remoteModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyCountryDetails {

    private String country;

    private String svgFlag;

    private List<Integer> policeNumbers;

    private List<Integer> ambulanceNumbers;

    private List<Integer> fireNumbers;


}
