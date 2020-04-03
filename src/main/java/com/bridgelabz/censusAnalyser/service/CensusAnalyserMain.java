package com.bridgelabz.censusAnalyser.service;

import com.bridgelabz.censusAnalyser.censusADAPTER.CensusAdapterFactory;
import com.bridgelabz.censusAnalyser.censusADAPTER.CensusAdepter;
import com.bridgelabz.censusAnalyser.censusDAO.CensusAnalyserDAO;
import com.bridgelabz.censusAnalyser.censusDTO.IndianStateCensusData;
import com.bridgelabz.censusAnalyser.censusDTO.StateCodePOJO;
import com.bridgelabz.censusAnalyser.censusDTO.USCensusPOJO;
import com.bridgelabz.censusAnalyser.exception.CensusAnalyserException;
import com.google.gson.Gson;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyserMain {

    List<CensusAnalyserDAO> csvCensusList = null;
    Map<String, CensusAnalyserDAO> csvCensusMap = null;

    public enum COUNTRY {INDIA, US}
    COUNTRY country;
    public enum SORTING_MODE {STATE, POPULATION, DENSITY, AREA, STATECODE}

    public CensusAnalyserMain() {
        this.csvCensusMap = new HashMap<>();
    }
    // GENERIC METHOD LOADING EVERY FILE DATA
    public  int loadCensusData(COUNTRY country, String... filePath) throws CensusAnalyserException {
        CensusAdepter censusLoader = CensusAdapterFactory.getCensusData(country);
        csvCensusMap = censusLoader.loadCensusData(filePath);
        csvCensusList = csvCensusMap.values().stream().collect(Collectors.toList());
        return csvCensusMap.size();
    }
    //METHOD TO SORT STATE CENSUS DATA
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