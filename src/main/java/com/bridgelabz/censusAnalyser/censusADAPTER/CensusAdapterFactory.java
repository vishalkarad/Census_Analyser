package com.bridgelabz.censusAnalyser.censusADAPTER;
import com.bridgelabz.censusAnalyser.service.CensusAnalyserMain;

public class CensusAdapterFactory {
    public static CensusAdepter getCensusData(CensusAnalyserMain.COUNTRY country) {
        if (country.equals(CensusAnalyserMain.COUNTRY.INDIA))
            return new IndiaCensusAdapter();
        if (country.equals(CensusAnalyserMain.COUNTRY.US))
            return new USCensusAdapter();
        return null;
    }
}
