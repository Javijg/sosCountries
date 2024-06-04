import { Component, Input } from '@angular/core';
import { IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/angular/standalone';
import { CountriesISO } from 'src/app/enum/countries.iso.enum';

@Component({
  selector: 'app-flag-svg-icon',
  templateUrl: 'flag-svg-icon.component.html',
  styleUrls: ['flag-svg-icon.component.scss'],

})
export class FlagSvgIconComponent {
  @Input() public country: CountriesISO;

  public COUNTRIES_ISO = CountriesISO

}
