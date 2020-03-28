package com.bridgelabz.CensusDAO;
import com.bridgelabz.CensusAnalyser.IndianStateCensusData;
import com.bridgelabz.CensusAnalyser.StateCodePOJO;

public class CensusAnalyserDAO {
    public String state;
    public int population;
    public int area;
    public int density;
    public String stateCode;

    public CensusAnalyserDAO(IndianStateCensusData csvStateCensus) {
        this.state = csvStateCensus.getState();
        this.population = csvStateCensus.getPopulation();
        this.area = csvStateCensus.getAreaInSqKm();
        this.density = csvStateCensus.getDensityPerSqKm();
    }

    public CensusAnalyserDAO(StateCodePOJO csvStateCode) {
        this.stateCode = csvStateCode.getStateCode();
    }
}
