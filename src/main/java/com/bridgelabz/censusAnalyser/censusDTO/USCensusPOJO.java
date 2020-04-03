package com.bridgelabz.censusAnalyser.censusDTO;

import com.opencsv.bean.CsvBindByName;

public class USCensusPOJO {
    @CsvBindByName(column = "State Id",required = true)
    public String state_Id;
    @CsvBindByName(column = "State",required = true)
    public String state;
    @CsvBindByName(column = "Population",required = true)
    public int population;
    @CsvBindByName(column = "Housing units",required = true)
    public int housing_Units;
    @CsvBindByName(column = "Total area",required = true)
    public float total_Area;
    @CsvBindByName(column = "Water area",required = true)
    public float water_Area;
    @CsvBindByName(column = "Land area",required = true)
    public float land_Area;
    @CsvBindByName(column = "Population Density",required = true)
    public float population_Density;
    @CsvBindByName(column = "Housing Density",required = true)
    public float housing_Density;

    public USCensusPOJO(String state_Id, String state, int population, int area, int density) {
        this.state_Id = state_Id;
        this.state = state;
        this.population = population;
        this.total_Area = area;
        this.population_Density = density;
    }

    public String getState_Id() {
        return state_Id;
    }
    public void setState_Id(String state_Id) {
        this.state_Id = state_Id;
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
    public int getHousing_Units() {
        return housing_Units;
    }
    public void setHousing_Units(int housing_Units) {
        this.housing_Units = housing_Units;
    }
    public float getTotal_Area() {
        return total_Area;
    }
    public void setTotal_Area(float total_Area) {
        this.total_Area = total_Area;
    }
    public float getWater_Area() {
        return water_Area;
    }
    public void setWater_Area(float water_Area) {
        this.water_Area = water_Area;
    }
    public float getLand_Area() {
        return land_Area;
    }
    public void setLand_Area(float land_Area) {
        this.land_Area = land_Area;
    }
    public float getPopulation_Density() {
        return population_Density;
    }
    public void setPopulation_Density(float population_Density) {
        this.population_Density = population_Density;
    }
    public float getHousing_Density() {
        return housing_Density;
    }
    public void setHousing_Density(float housing_Density) {
        this.housing_Density = housing_Density;
    }

    @Override
    public String toString() {
        return "USCensusPOJO{" +
                "state_Id='" + state_Id + '\'' +
                ", state='" + state + '\'' +
                ", population=" + population +
                ", housing_Units=" + housing_Units +
                ", total_Area=" + total_Area +
                ", water_Area=" + water_Area +
                ", land_Area=" + land_Area +
                ", population_Density=" + population_Density +
                ", housing_Density=" + housing_Density +
                '}';
    }
}
