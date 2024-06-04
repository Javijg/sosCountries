
export interface EmergencyNumbers {
    data: {
        country: {
            name: string;
            ISOCode: string;
            ISONumeric: string;
        };
        ambulance: EmergencyPhoneNumber;
        fire: EmergencyPhoneNumber;
        police: EmergencyPhoneNumber;
        dispatch: EmergencyPhoneNumber;
        member_112: boolean;
        localOnly: boolean;
        nodata: boolean;
    }
}

export interface EmergencyPhoneNumber {
    all: string[];
    gsm: any; //?
    fixed: any //?
}