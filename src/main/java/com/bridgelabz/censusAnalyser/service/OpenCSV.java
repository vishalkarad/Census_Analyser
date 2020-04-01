package com.bridgelabz.censusAnalyser.service;
import com.bridgelabz.censusAnalyser.exception.CensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.util.Iterator;

public class OpenCSV implements CSV_Interface {
    //Return file in iterator
    @Override
   public <E> Iterator<E> getCSVfileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
      try {
          return this.getCSVToBeen(reader, csvClass).iterator();
      }catch (CensusAnalyserException e){
          e.printStackTrace();
      }
      return null;
   }
    // Return csvtoBean
    private  <E> CsvToBean<E> getCSVToBeen(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
        CsvToBean csvToBea=null;
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        }  catch (IllegalStateException e) {
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND, "Wrong file");
        }
    }

}