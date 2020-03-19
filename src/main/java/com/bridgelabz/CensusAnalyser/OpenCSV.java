package com.bridgelabz.CensusAnalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSV implements CSV_Interface {
    public<E> Iterator<E> getCSVfileIterator(Reader reader,Class<E> csvClass) throws CensusAnalyserException {
        return this.getCSVToBeen(reader,csvClass).iterator();
    }
    public <E> CsvToBean<E> getCSVToBeen(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean;
        }catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,"Check delimetr and header");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
