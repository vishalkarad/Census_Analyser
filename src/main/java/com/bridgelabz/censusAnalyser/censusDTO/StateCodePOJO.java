package com.bridgelabz.censusAnalyser;
import com.opencsv.bean.CsvBindByName;

public class StateCodePOJO {

    @CsvBindByName(column = "SrNo",required = true)
    public String srNo;

    @CsvBindByName(column = "State",required = true)
    public String state;

    @CsvBindByName(column = "Name",required = false)
    public String name;

    @CsvBindByName(column = "TIN",required = true)
    public String tin;

    @CsvBindByName(column = "StateCode",required = true)
    public String stateCode;

    public String getStateCode() {
        return stateCode;
    }
}
