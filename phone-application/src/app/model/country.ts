export interface Country {
    readonly name: CountryName;
    cca2: string;
    cca3: string;
    flags: CountryFlag

}

export interface CountryName {
    common: string;
    official: string;
    nativeName: {
        ron: {
            official: string;
            common: string;
        }
    };
}

export interface CountryFlag {
    png: string;
    svg: string;
    alt: string;
}