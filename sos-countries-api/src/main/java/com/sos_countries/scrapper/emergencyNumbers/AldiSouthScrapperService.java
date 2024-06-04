package com.sos_countries.scrapper.emergencyNumbers;

import com.sos_countries.scrapper.emergencyNumbers.remoteModel.EmergencyCountryDetails;
import com.sos_countries.scrapper.emergencyNumbers.remoteModel.MainNumbersEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AldiSouthScrapperService {

    private Log log = LogFactory.getLog(getClass());

    public List<EmergencyCountryDetails> retrieveOffers() {
        String url = "https://en.wikipedia.org/wiki/List_of_emergency_telephone_numbers";
        Document document;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("Could not connect to web page " + url + ", " + e);
            return null;
        }

        Elements elements = document.select("tr");
        if (elements == null || elements.isEmpty()){
            log.error("Could not find elements for " + url);
            return null;
        }

        List<EmergencyCountryDetails> result = new ArrayList<>();
        elements.forEach(element -> {

            Elements rawCountries = element.select("td");
            result.add(extractCountry(rawCountries));

        });

        if(result.isEmpty()) {
            log.error("Could not get items for url " + url);
        }
        return result;
    }

    private EmergencyCountryDetails extractCountry(Elements rawCountryData){
        if(rawCountryData == null || rawCountryData.isEmpty()) {
            log.error("No raw country data was provided");
            return null;
        }

        Iterator iter = rawCountryData.iterator();
        Element countryElement = (Element) iter.next();

        Elements countryHref = countryElement.select("a");
        if(countryHref == null || countryHref.isEmpty()) {
            log.error("Could not identify country from element " + countryHref);
            return null;
        }

        EmergencyCountryDetails result = new EmergencyCountryDetails();
        result.setCountry(countryHref.text());

        Elements flagElement = countryElement.select("img");
        if(flagElement != null && !flagElement.isEmpty()) {
            result.setSvgFlag(flagElement.attr("src"));
        }

        MainNumbersEnum mainNumberIndex = MainNumbersEnum.values()[0];
        while (iter.hasNext()) {
            Element element = (Element) iter.next();

            Attribute style = element.attribute("style");
            Attribute align = element.attribute("align");
            boolean isCentralNumber = (style != null && style.getValue().contains("text-align: center") ||
                    (align != null && align.getValue().contains("center")));

            if(isCentralNumber) {
                Elements numberElement = element.select("b");

                int columnsCount = 1;
                Attribute countAttribute = element.attribute("colspan");
                if(countAttribute != null) {
                    columnsCount = Integer.parseInt(countAttribute.getValue());
                }

                if(numberElement == null || numberElement.isEmpty()) {
                    if(MainNumbersEnum.values().length > (mainNumberIndex.getNumberOrder()+1)){
                        mainNumberIndex = MainNumbersEnum.values()[mainNumberIndex.getNumberOrder()+1];
                    }
                    continue;
                }

                String phoneNumberString = numberElement.get(0).ownText();
                phoneNumberString = phoneNumberString.replaceAll("\\(.*?\\)", "")
                        .replaceAll("-", "");
                String[] phoneNumberStringArray = phoneNumberString.split("/");

                for(String item: phoneNumberStringArray) {
                    int phoneNumber = -1;
                    try {
                        phoneNumber = Integer.parseInt(item);
                    } catch (Exception e) {
                        log.error("Could not parse from item " + item + ", " + e );
                    }

                    for(int i=0; i<columnsCount; i++) {
                        if(phoneNumber != -1) {
                            switch (mainNumberIndex) {
                                case POLICE:
                                    List<Integer> policeNumbers = result.getPoliceNumbers() == null ? new ArrayList<>() : result.getPoliceNumbers();
                                    policeNumbers.add(phoneNumber);
                                    result.setPoliceNumbers(policeNumbers);
                                    break;
                                case AMBULANCE:
                                        List<Integer> ambulanceNumbers = result.getAmbulanceNumbers() == null ? new ArrayList<>() : result.getAmbulanceNumbers();
                                        ambulanceNumbers.add(phoneNumber);
                                        result.setAmbulanceNumbers(ambulanceNumbers);
                                        break;
                                case FIRE:
                                    List<Integer> fireNumbers = result.getFireNumbers() == null ? new ArrayList<>() : result.getFireNumbers();
                                    fireNumbers.add(phoneNumber);
                                    result.setFireNumbers(fireNumbers);
                                    break;
                            }
                        }
                        if(MainNumbersEnum.values().length > (mainNumberIndex.getNumberOrder()+1)){
                            mainNumberIndex = MainNumbersEnum.values()[mainNumberIndex.getNumberOrder()+1];
                        }
                    }
                }

            }
        }
        return result;
    }
}
