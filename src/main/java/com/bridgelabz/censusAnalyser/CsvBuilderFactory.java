package com.bridgelabz.censusAnalyser;

public class CsvBuilderFactory {
    public static CSV_Interface createCsvInterface() {
        return new OpenCSV();
    }
}
