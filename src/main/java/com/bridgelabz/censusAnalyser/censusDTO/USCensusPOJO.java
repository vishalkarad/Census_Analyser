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
}
