import { Component, Input } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { CountriesISO } from 'src/app/enum/countries.iso.enum';
import { EmergencyNumbers } from 'src/app/model/EmergencyNumbers';
import { Country } from 'src/app/model/country';

@Component({
  selector: 'app-country-details',
  templateUrl: 'country-details.component.html',
  styleUrls: ['country-details.component.scss'],

})
export class CountryDetailsComponent {
  @Input() public country: Country;
  @Input() public emergencyNumbers: EmergencyNumbers;

  public COUNTRIES_ISO = CountriesISO

  constructor(private modalController: ModalController) {

  }

  async dismissModal() {
    const modal = await this.modalController.getTop();
    if (modal) {
      modal.dismiss();
    }
  }

  public onWillDismiss(event: any): void {
    console.log("On will dimiss")
    console.log(event)
  }
}
