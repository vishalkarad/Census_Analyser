package com.bridgelabz.CensusAnalyser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSV implements CSV_Interface {
    //Return file in iterator
    public<E> Iterator<E> getCSVfileIterator(Reader reader,Class<E> csvClass) throws CensusAnalyserException {
        return this.getCSVToBeen(reader,csvClass).iterator();
    }
    // Return file in list
    @Override
    public<E> List<E> geCSVfileList(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
        return this.getCSVToBeen(reader,csvClass).parse();
    }
    // Return csvtoBean
    private  <E> CsvToBean<E> getCSVToBeen(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            CsvToBean<E> csvToBean =csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
            return csvToBean;
        }catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,"Check delimetr and header");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}