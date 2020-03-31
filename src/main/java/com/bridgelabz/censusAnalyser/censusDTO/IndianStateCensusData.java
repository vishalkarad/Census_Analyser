package com.bridgelabz.censusAnalyser.censusDTO;
import com.opencsv.bean.CsvBindByName;

public class IndianStateCensusData {

        @CsvBindByName(column = "State",required = true)
        public String state;

        @CsvBindByName(column = "Population",required = true)
        public int population;

        @CsvBindByName(column = "AreaInSqKm",required = true)
        public int areaInSqKm;

        @CsvBindByName(column = "DensityPerSqKm",required = true)
        public int densityPerSqKm;

        public String getState() {
                return state;
        }

        public int getPopulation() {
                return population;
        }

        public int getAreaInSqKm() {
                return areaInSqKm;
        }

        public int getDensityPerSqKm() {
                return densityPerSqKm;
        }
}
