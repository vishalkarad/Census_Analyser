package com.bridgelabz.censusAnalyser.service;
import com.bridgelabz.censusAnalyser.censusADAPTER.CensusAdapterFactory;
import com.bridgelabz.censusAnalyser.censusADAPTER.CensusAdepter;
import com.bridgelabz.censusAnalyser.censusDAO.CensusAnalyserDAO;
import com.bridgelabz.censusAnalyser.exception.CensusAnalyserException;
import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyserMain {

    List<CensusAnalyserDAO> csvCensusList = null;
    Map<String, CensusAnalyserDAO> csvCensusMap = null;

    // Enum for choice country
    public enum COUNTRY {INDIA, US}
    COUNTRY country;

    // Constructor to assign country
    public CensusAnalyserMain(COUNTRY country){
        this.country = country;
    }
    // Enum for sorting mode
    public enum SORTING_MODE {STATE, POPULATION, DENSITY, AREA, STATECODE}

   /* public CensusAnalyserMain() {
        this.csvCensusMap = new HashMap<>();
    }*/
    // Genric method to lod every file data
    public  int loadCensusData(String... filePath) throws CensusAnalyserException {
        CensusAdepter censusLoader = CensusAdapterFactory.getCensusData(country);
        csvCensusMap = censusLoader.loadCensusData(filePath);
        csvCensusList = csvCensusMap.values().stream().collect(Collectors.toList());
        return csvCensusMap.size();
    }
    // Method to sort  census data
    public String getSortCensusData(SORTING_MODE mode) throws CensusAnalyserException {
        if (csvCensusMap == null || csvCensusMap.size() == 0) {
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                                                                             DATA_ARE_NOT_FOUND,"Data are not found");
        }
        ArrayList censusList = csvCensusMap.values().stream()
                .sorted(CensusAnalyserDAO.getSortComparator(mode))
                .map(CensusAnalyserDAO -> CensusAnalyserDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusList);
    }
}