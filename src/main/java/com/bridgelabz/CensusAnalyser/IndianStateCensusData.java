package com.bridgelabz.CensusAnalyser;
import com.opencsv.bean.CsvBindByName;

public class IndianStateCensusData {

        @CsvBindByName(column = "State",required = true)
        public String state;

        @CsvBindByName(column = "Population",required = true)
        public String population;

        @CsvBindByName(column = "AreaInSqKm",required = true)
        public String areaInSqKm;

        @CsvBindByName(column = "DensityPerSqKm",required = true)
        public String densityPerSqKm;
}
