import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Country } from "../model/country";
import { Observable } from "rxjs";
import { EmergencyNumbers } from "../model/EmergencyNumbers";

@Injectable({
    providedIn: 'root'
})
export class CountriesService {
    constructor(private readonly http: HttpClient) { }

    public getCountries$(): Observable<Country[]> {
        const url = "https://restcountries.com/v3.1/all?fields=name,flags,cca2,cca3"
        return this.http.get<Country[]>(url)
    }

    public getEmergencyNumbers$(countyCode: string) : Observable<EmergencyNumbers> {
        const url = `https://emergencynumberapi.com/api/country/${countyCode}`
        return this.http.get<EmergencyNumbers>(url)
    }

}