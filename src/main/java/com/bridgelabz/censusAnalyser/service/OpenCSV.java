package com.bridgelabz.censusAnalyser.service;
import com.bridgelabz.censusAnalyser.exception.CensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.util.Iterator;

public class OpenCSV implements CSV_Interface {
    //Return file in iterator
    @Override
    public<E> Iterator<E> getCSVfileIterator(Reader reader,Class<E> csvClass) throws CensusAnalyserException {
        return this.getCSVToBeen(reader,csvClass).iterator();
    }

    // Return csvtoBean
    private  <E> CsvToBean<E> getCSVToBeen(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            CsvToBean<E> csvToBean =csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
            return csvToBean;
        }catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                                                                      DELIMITER_INCORECT,"Check delimetr and header");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}