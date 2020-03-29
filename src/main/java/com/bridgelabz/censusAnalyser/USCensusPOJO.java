package com.bridgelabz.censusAnalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusPOJO {
    @CsvBindByName(column = "State Id",required = true)
    public String State_Id;

    @CsvBindByName(column = "State",required = true)
    public String State;

    @CsvBindByName(column = "Population",required = true)
    public int Population;

    @CsvBindByName(column = "Housing units",required = true)
    public int Housing_units;

    @CsvBindByName(column = "Total area",required = true)
    public int Total_area;

    @CsvBindByName(column = "Water area",required = true)
    public int Water_area;

    @CsvBindByName(column = "Land area",required = true)
    public int Land_area;

    @CsvBindByName(column = "Population Density",required = true)
    public int Population_Density;

    @CsvBindByName(column = "Housing Density",required = true)
    public int Housing_Density;
}
