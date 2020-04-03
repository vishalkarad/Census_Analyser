package com.bridgelabz.censusAnalyser.censusDTO;
import com.opencsv.bean.CsvBindByName;

public class IndianStateCensusData {

        private final String stateCode;
        @CsvBindByName(column = "State",required = true)
        public String state;

        @CsvBindByName(column = "Population",required = true)
        public int population;

        @CsvBindByName(column = "AreaInSqKm",required = true)
        public int areaInSqKm;

        @CsvBindByName(column = "DensityPerSqKm",required = true)
        public int densityPerSqKm;

       // private String stateCode = new IndianStateCode().getStateCode();

        public IndianStateCensusData(String stateCode, String state, int population, int areaInSqKm, int densityPerSqKm) {
                this.stateCode = stateCode;
                this.state = state;
                this.population = population;
                this.areaInSqKm = areaInSqKm;
                this.densityPerSqKm = densityPerSqKm;
        }

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
