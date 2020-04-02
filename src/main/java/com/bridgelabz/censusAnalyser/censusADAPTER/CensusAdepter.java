package com.bridgelabz.censusAnalyser.censusADAPTER;
import com.bridgelabz.censusAnalyser.censusDAO.CensusAnalyserDAO;
import com.bridgelabz.censusAnalyser.censusDTO.IndianStateCensusData;
import com.bridgelabz.censusAnalyser.censusDTO.USCensusPOJO;
import com.bridgelabz.censusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusAnalyser.service.CSV_Interface;
import com.bridgelabz.censusAnalyser.service.CsvBuilderFactory;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CensusAdepter {

    Map<String, CensusAnalyserDAO> csvCensusMap = new HashMap<>();

    public abstract Map<String, CensusAnalyserDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException;

    public <E> Map<String, CensusAnalyserDAO> loadCensusData(Class<E> censusCsvClass, String... csvFilePath)
            throws CensusAnalyserException {
        Map<String, CensusAnalyserDAO> csvCensusMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))) {
            CSV_Interface csvBuilder = CsvBuilderFactory.createCsvInterface();
            Iterator<E> stateCensusIterator = csvBuilder.getCSVfileIterator(reader, censusCsvClass);
            Iterable<E> csvIterable = () -> stateCensusIterator;
            if (censusCsvClass.getName().contains("IndianStateCensusData")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IndianStateCensusData.class::cast)
                        .forEach(censusCSV -> csvCensusMap.put(censusCSV.state, new CensusAnalyserDAO(censusCSV)));
                return csvCensusMap;
            } else if (censusCsvClass.getName().contains("USCensusPOJO")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(USCensusPOJO.class::cast)
                        .forEach(censusCSV -> csvCensusMap.put(censusCSV.getState(), new CensusAnalyserDAO(censusCSV)));
                return csvCensusMap;
            } else {
                throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                                                                                NO_SUCH_COUNTRY, "Wrong country name");
            }
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                    FILE_NOT_FOUND, "Enter a right file name and type");
        } catch (RuntimeException e) {
             throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                                                                   DELIMITER_INCORECT,"Check delimetr and header");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvCensusMap;
    }
}
