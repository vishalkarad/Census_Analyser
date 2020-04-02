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

        public IndianStateCensusData(String state, int population, int areaInSqKm, int densityPerSqKm) {
                this.state = state;
                this.population = population;
                this.areaInSqKm = areaInSqKm;
                this.densityPerSqKm = densityPerSqKm;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state;
        }

        public int getPopulation() {
                return population;
        }

        public void setPopulation(int population) {
                this.population = population;
        }

        public int getAreaInSqKm() {
                return areaInSqKm;
        }

        public void setAreaInSqKm(int areaInSqKm) {
                this.areaInSqKm = areaInSqKm;
        }

        public int getDensityPerSqKm() {
                return densityPerSqKm;
        }

        public void setDensityPerSqKm(int densityPerSqKm) {
                this.densityPerSqKm = densityPerSqKm;
        }

        @Override
        public String toString() {
                return "IndianStateCensusData{" +
                        "state='" + state + '\'' +
                        ", population=" + population +
                        ", areaInSqKm=" + areaInSqKm +
                        ", densityPerSqKm=" + densityPerSqKm +
                        '}';
        }
}
