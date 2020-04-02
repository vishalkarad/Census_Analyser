package com.bridgelabz.censusAnalyser.censusDAO;
import com.bridgelabz.censusAnalyser.censusDTO.IndianStateCensusData;
import com.bridgelabz.censusAnalyser.censusDTO.StateCodePOJO;
import com.bridgelabz.censusAnalyser.censusDTO.USCensusPOJO;
import com.bridgelabz.censusAnalyser.service.CensusAnalyserMain;

import java.util.Comparator;

public class CensusAnalyserDAO<Static> {
    public String state;
    public int population;
    public int area;
    public int density;
    public String stateCode;
    public String state_Id;
    public int housing_Units;
    public float total_Area;
    public float water_Area;
    public float land_Area;
    public float Population_Density;
    public float housing_Density;

    public CensusAnalyserDAO(IndianStateCensusData csvStateCensus) {
        this.state = csvStateCensus.getState();
        this.population = csvStateCensus.getPopulation();
        this.area = csvStateCensus.getAreaInSqKm();
        this.density = csvStateCensus.getDensityPerSqKm();
    }

    public CensusAnalyserDAO(StateCodePOJO csvStateCode) {
        this.stateCode = csvStateCode.getStateCode();
    }
    public CensusAnalyserDAO(USCensusPOJO usCensusPOJO){
        this.state_Id = usCensusPOJO.state_Id;
        this.state = usCensusPOJO.state;
        this.population = usCensusPOJO.population;
        this.housing_Units = usCensusPOJO.housing_Units;
        this.total_Area = usCensusPOJO.total_Area;
        this.water_Area = usCensusPOJO.water_Area;
        this.land_Area = usCensusPOJO.land_Area;
        this.Population_Density = usCensusPOJO.population_Density;
        this.housing_Density = usCensusPOJO.housing_Density;
    }

    public static Comparator<CensusAnalyserDAO> getSortComparator(CensusAnalyserMain.SORTING_MODE mode) {
        if (mode.equals(CensusAnalyserMain.SORTING_MODE.STATE))
            return Comparator.comparing(census -> census.state);
        if (mode.equals(CensusAnalyserMain.SORTING_MODE.POPULATION))
            return Comparator.comparing(census -> census.population);
        if (mode.equals(CensusAnalyserMain.SORTING_MODE.DENSITY))
            return Comparator.comparing(census -> census.density);
        if (mode.equals(CensusAnalyserMain.SORTING_MODE.AREA))
            return Comparator.comparing(census -> census.area);
        if (mode.equals(CensusAnalyserMain.SORTING_MODE.STATECODE))
            return Comparator.comparing(census -> census.stateCode);
        return null;
    }

    public Object getCensusDTO(CensusAnalyserMain.COUNTRY country) {
        if (country.equals(CensusAnalyserMain.COUNTRY.INDIA))
            return new IndianStateCensusData(state, stateCode, population, area, density);
        if (country.equals(CensusAnalyserMain.COUNTRY.US))
            return new USCensusPOJO(stateCode, state, population, area, population);
        return null;
    }
}
