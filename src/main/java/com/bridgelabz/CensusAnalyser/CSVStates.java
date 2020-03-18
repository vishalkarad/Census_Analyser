package com.bridgelabz.CensusAnalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class CSVStates {
    //METHOD TO LOAD THE CSV FILE AND GET
    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
        int count=0;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            CsvToBean<StateCodePOJO> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(StateCodePOJO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<StateCodePOJO> statesCSVIterator = csvToBean.iterator();
            while (statesCSVIterator.hasNext()) {
                StateCodePOJO censusCSV = statesCSVIterator.next();
                System.out.println("SrNo: "+censusCSV.getSrNo()+", ");
                System.out.println("state: "+censusCSV.getState()+", ");
                System.out.println("Name: "+censusCSV.getName()+", ");
                System.out.println("TIN: "+censusCSV.getTin()+", ");
                System.out.println("StateCode: "+censusCSV.getStateCode()+", ");
                System.out.println("===============================");
                count++;
            }
        } catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,"Enter a right file name and type");
        }catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}

