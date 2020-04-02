package com.bridgelabz.censusAnalyser.censusADAPTER;
import com.bridgelabz.censusAnalyser.censusDAO.CensusAnalyserDAO;
import com.bridgelabz.censusAnalyser.censusDTO.USCensusPOJO;
import com.bridgelabz.censusAnalyser.exception.CensusAnalyserException;
import java.util.Map;

public class USCensusAdapter extends CensusAdepter{
    @Override
    public Map<String, CensusAnalyserDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        return super.loadCensusData(USCensusPOJO.class, csvFilePath[0]);
    }
}
