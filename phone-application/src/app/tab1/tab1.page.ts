import { Component } from '@angular/core';
import { CountriesISO } from '../enum/countries.iso.enum';
import { CountriesService } from '../services/countries.service';
import { Country } from '../model/country';
import { Observable } from 'rxjs';
import { EmergencyNumbers } from '../model/EmergencyNumbers';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page {

  public COUNTRIES_ISO = CountriesISO;
  public selectedCountry: Country;
  public countries$: Observable<Country[]>
  public emergencyCountryNumbers$: Observable<EmergencyNumbers>

  constructor(private readonly countriesService: CountriesService){
    this.countries$ = countriesService.getCountries$();
  }

  public onCountrySelection(country: Country): void {
    console.log(country)
    this.selectedCountry = country;
    this.emergencyCountryNumbers$ = this.countriesService.getEmergencyNumbers$(country.cca2);
  }

}
