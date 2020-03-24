package com.bridgelabz.CensusAnalyser;

public class CensusAnalyserException extends Exception {

    public enum MyException_Type{
        FILE_NOT_FOUND,DELIMITER_INCORECT,DATA_ARE_NOT_FOUND;
    }
    public MyException_Type type;
    public CensusAnalyserException(MyException_Type type, String message) {
        super(message);
        this.type=type;
        System.out.println(message);
    }
}
