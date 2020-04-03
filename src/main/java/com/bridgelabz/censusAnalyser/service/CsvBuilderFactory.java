package com.bridgelabz.censusAnalyser.service;

public class CsvBuilderFactory {
    public static CSV_Interface createCsvInterface() {
        return new com.bridgelabz.censusAnalyser.service.OpenCSV();
    }
}
