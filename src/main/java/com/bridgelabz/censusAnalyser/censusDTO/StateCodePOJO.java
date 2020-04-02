package com.bridgelabz.censusAnalyser.censusDTO;
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

    public String getSrNo() {
        return srNo;
    }
    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTin() {
        return tin;
    }
    public void setTin(String tin) {
        this.tin = tin;
    }
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @Override
    public String toString() {
        return "StateCodePOJO{" +
                "srNo='" + srNo + '\'' +
                ", state='" + state + '\'' +
                ", name='" + name + '\'' +
                ", tin='" + tin + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}