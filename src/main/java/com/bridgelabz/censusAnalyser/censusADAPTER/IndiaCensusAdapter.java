package com.bridgelabz.censusAnalyser.censusADAPTER;

import com.bridgelabz.censusAnalyser.censusDAO.CensusAnalyserDAO;
import com.bridgelabz.censusAnalyser.censusDTO.IndianStateCensusData;
import com.bridgelabz.censusAnalyser.censusDTO.StateCodePOJO;
import com.bridgelabz.censusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusAnalyser.service.CSV_Interface;
import com.bridgelabz.censusAnalyser.service.CsvBuilderFactory;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdepter {

    @Override
    public Map<String, CensusAnalyserDAO> loadCensusData(String... filePath) throws CensusAnalyserException {
        Map<String, CensusAnalyserDAO> csvCensusMap = super.loadCensusData(IndianStateCensusData.class, filePath[0]);
        if (filePath.length == 1)
            return csvCensusMap;
        return this.loadStateCodeCensusData(csvCensusMap, filePath[1]);
    }

    //FUNCTION TO LOAD US CENSUS DATA
    private <E> Map<String, CensusAnalyserDAO> loadStateCodeCensusData(Map<String, CensusAnalyserDAO> csvCensusMap,
                                                                  String filePath) throws CensusAnalyserException {

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CSV_Interface csvBuilder = CsvBuilderFactory.createCsvInterface();
            Iterator<IndianStateCensusData> stateCensusIterator = csvBuilder.getCSVfileIterator(reader, IndianStateCensusData.class);
            Iterable<IndianStateCensusData> csvIterable = () -> stateCensusIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> csvCensusMap.get(csvState.state) != null)
                    .forEach(csvState -> csvCensusMap.get(csvState.state).state = csvState.state);
            return csvCensusMap;
        } catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                                                               FILE_NOT_FOUND,"Enter a right file name and type");
        } catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                                                              DELIMITER_INCORECT,"Check delimetr and header");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
