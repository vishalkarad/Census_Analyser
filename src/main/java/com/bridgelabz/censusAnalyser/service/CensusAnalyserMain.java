package com.bridgelabz.censusAnalyser.service;

import com.bridgelabz.censusAnalyser.censusDTO.IndianStateCensusData;
import com.bridgelabz.censusAnalyser.censusDTO.StateCodePOJO;
import com.bridgelabz.censusAnalyser.censusDAO.CensusAnalyserDAO;
import com.bridgelabz.censusAnalyser.censusDTO.USCensusPOJO;
import com.bridgelabz.censusAnalyser.exception.CensusAnalyserException;
import com.google.gson.Gson;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyserMain {

    List<CensusAnalyserDAO> csvCensusList = null;
    Map<String, CensusAnalyserDAO> csvCensusMap = null;
       public CensusAnalyserMain() {
        this.csvCensusMap = new HashMap<>();
       // this.csvStateCodeMap = new HashMap<>();
    }

    OpenCSV openCSV = new OpenCSV();
    public static void main(String[] args)  {
        System.out.println("******************** Welcome to Census Analyseer ********************");
    }
    // Read CSV file
    public Integer readFile(String filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            CSV_Interface csv_interface = CsvBuilderFactory.createCsvInterface();
            Iterator<IndianStateCensusData> csvStateCensusIterator = openCSV.
                                                                getCSVfileIterator(reader,IndianStateCensusData.class);
            Iterable<IndianStateCensusData> stateCensuses = () -> csvStateCensusIterator;
            StreamSupport.stream(stateCensuses.spliterator(), false)
                    .forEach(csvStateCensus -> csvCensusMap.put(csvStateCensus.getState(),
                                                                               new CensusAnalyserDAO(csvStateCensus)));
            csvCensusList = csvCensusMap.values().stream().collect(Collectors.toList());
            return csvCensusMap.size();
        }catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                                                                 FILE_NOT_FOUND,"Enter a right file name and type");
        }
        catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                                                                    DELIMITER_INCORECT,"Check delimetr and header");
        }
    }
    // Read state code csv file
    public Integer loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));)
        {
            CSV_Interface csv_interface = CsvBuilderFactory.createCsvInterface();
            Iterator<StateCodePOJO> csvStateCodeIterator = openCSV.
                    getCSVfileIterator(reader,StateCodePOJO.class);
            Iterable<StateCodePOJO> stateCodeCensuses = () -> csvStateCodeIterator;
            StreamSupport.stream(stateCodeCensuses.spliterator(), false)
                    .forEach(csvStateCensus -> csvCensusMap.put(csvStateCensus.getStateCode(),
                            new CensusAnalyserDAO(csvStateCensus)));
            csvCensusList = csvCensusMap.values().stream().collect(Collectors.toList());
            return csvCensusMap.size();
        } catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                                                                   FILE_NOT_FOUND,"Enter a right file name and type");
        } catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                                                                      DELIMITER_INCORECT,"Check delimetr and header");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (null);
    }
    // Function to lod US census data
    public int lodUSCensusData(String filePath) throws CensusAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(filePath));)
        {
            Iterator<USCensusPOJO> usCensusPOJOIterator = openCSV.getCSVfileIterator(reader,USCensusPOJO.class);
            Iterable<USCensusPOJO> usCensusPOJOIterable = ()-> usCensusPOJOIterator;
            StreamSupport.stream(usCensusPOJOIterable.spliterator(), false)
                    .forEach(csvStateCensus -> csvCensusMap.put(csvStateCensus.state_Id,
                            new CensusAnalyserDAO(csvStateCensus)));
            csvCensusList = csvCensusMap.values().stream().collect(Collectors.toList());
            return csvCensusMap.size();
        } catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                    FILE_NOT_FOUND,"Enter a right file name and type");
        } catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                    DELIMITER_INCORECT,"Check delimetr and header");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (0);
    }
    // Sorted state wise data
    public String getStateWiseData(String filePath) throws Exception {
        if (csvCensusList == null || csvCensusList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                    DATA_ARE_NOT_FOUND,"Data are not found");
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            Comparator<CensusAnalyserDAO> comparator = Comparator.comparing(censusAnalyser -> censusAnalyser.state);
            this.sortCSVFile(csvCensusList,comparator);
            String sortedStateCSVList = new Gson().toJson(csvCensusList);
            return sortedStateCSVList;
        } catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                    FILE_NOT_FOUND,"Enter a right file name and type");
        }
    }
    // Compare stateCode wise data and return json object
    public String getSortStateCodeAlphabetical(String filePath) throws Exception {
        if (csvCensusList == null || csvCensusList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                    DATA_ARE_NOT_FOUND,"Data are not found");
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            Comparator<CensusAnalyserDAO> comparator = Comparator.comparing(census -> census.stateCode);
            this.sortCSVFile(csvCensusList,comparator);
            String sortedStateCodeCSVList = new Gson().toJson(csvCensusList);
            return sortedStateCodeCSVList;
        }catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                                                                 FILE_NOT_FOUND,"Enter a right file name and type");
        }
    }
    // Method To Sort State Census Data By Population
    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (csvCensusList == null || csvCensusList.size() == 0) {
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                    DATA_ARE_NOT_FOUND,"Data are not found");
        }
        Comparator<CensusAnalyserDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.population);
        this.sortCSVFile(csvCensusList,censusComparator);
        Collections.reverse(csvCensusList);
        String sortedStateCensusJson = new Gson().toJson(csvCensusList);
        return sortedStateCensusJson;
    }
    // Method To Sort State Census Data By Population Density
    public String getPopulationDensityWiseSortedData() throws CensusAnalyserException {
        if (csvCensusList == null || csvCensusList.size() == 0) {
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                                                                             DATA_ARE_NOT_FOUND,"Data are not found");
        }
        Comparator<CensusAnalyserDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.density);
        this.sortCSVFile(csvCensusList,censusComparator);
        Collections.reverse(csvCensusList);
        String sortedStateCensusJson = new Gson().toJson(csvCensusList);
        return sortedStateCensusJson;
    }
    // Sort State By Area
    public String getAreaWiseSortedState() throws CensusAnalyserException {
        if (csvCensusList == null || csvCensusList.size() == 0) {
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                                                                            DATA_ARE_NOT_FOUND,"Data are not found");
        }
        Comparator<CensusAnalyserDAO> censusComparator = Comparator.comparing(census -> census.area);
        this.sortCSVFile(csvCensusList,censusComparator);
        Collections.reverse(csvCensusList);
        String sortedStateCensusJson = new Gson().toJson(csvCensusList);
        return sortedStateCensusJson;
    }
    // Sort data bubble sort wise
    private <E> void sortCSVFile(List<E>listCSVfile, Comparator<E> comparator) {
        for (int itrat=0; itrat<listCSVfile.size(); itrat++){
            for (int itrat1=0; itrat1 < listCSVfile.size() -itrat -1; itrat1++ ){
                E state1 = listCSVfile.get(itrat1);
                E state2 = listCSVfile.get(itrat1+1);
                if (comparator.compare(state1,state2)>0){
                    listCSVfile.set(itrat1,state2);
                    listCSVfile.set(itrat1+1,state1);
                }
            }
        }
    }
}