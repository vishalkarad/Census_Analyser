import com.bridgelabz.CensusAnalyser.*;
import com.google.gson.Gson;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyserMain {

    List<IndianStateCensusData> csvStateCensusList = null;
    List<StateCodePOJO> csvStateCodeList = null;
    Map<String, IndianStateCensusData> csvStateCensusMap = null;
    Map<String, StateCodePOJO> csvStateCodeMap = null;

    public CensusAnalyserMain() {
        this.csvStateCensusMap = new HashMap<>();
        this.csvStateCodeMap = new HashMap<>();
    }

    OpenCSV openCSV = new OpenCSV();

    public static void main(String[] args)  {
        System.out.println("******************** Welcome to Census Analyseer ********************");
    }
    // Read CSV file
    public Integer readFile(String filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            Iterator<IndianStateCensusData> csvStateCensusIterator = openCSV.getCSVfileIterator(reader,IndianStateCensusData.class);
            while (csvStateCensusIterator.hasNext()) {
                IndianStateCensusData csvStateCensus = csvStateCensusIterator.next();
                this.csvStateCensusMap.put(csvStateCensus.state, csvStateCensus);
                csvStateCensusList = csvStateCensusMap.values().stream().collect(Collectors.toList());
            }
            return csvStateCensusMap.size();
        }catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,"Enter a right file name and type");
        }
        catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,"Check delimetr and header");
        }
    }
    // Read state code csv file
    public Integer loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));)
        {
            Iterator<StateCodePOJO> csvStateCodeIterator = openCSV.getCSVfileIterator(reader,StateCodePOJO.class);
            while (csvStateCodeIterator.hasNext()) {
                StateCodePOJO csvStateCode = csvStateCodeIterator.next();
                this.csvStateCodeMap.put(csvStateCode.state, csvStateCode);
                csvStateCodeList = csvStateCodeMap.values().stream().collect(Collectors.toList());
            }
            return csvStateCodeMap.size();
        } catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,"Enter a right file name and type");
        } catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,"Check delimetr and header");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (null);
    }
    // Sorted state wise data
    public String getStateWiseData(String filePath) throws Exception {
        if (csvStateCensusList == null || csvStateCensusList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                    DATA_ARE_NOT_FOUND,"Data are not found");
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            Comparator<IndianStateCensusData> comparator = Comparator.comparing(censusAnalyser -> censusAnalyser.state);
            this.sortCSVFile(csvStateCensusList,comparator);
            String sortedStateCSVList = new Gson().toJson(csvStateCensusList);
            return sortedStateCSVList;
        } catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                    FILE_NOT_FOUND,"Enter a right file name and type");
        }
    }
    // Compare stateCode wise data and return json object
    public String getSortStateCodeAlphabetical(String filePath) throws Exception {
        if (csvStateCodeList == null || csvStateCodeList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                    DATA_ARE_NOT_FOUND,"Data are not found");
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            Comparator<StateCodePOJO> comparator = Comparator.comparing(census -> census.stateCode);
            this.sortCSVFile(csvStateCodeList,comparator);
            String sortedStateCodeCSVList = new Gson().toJson(csvStateCodeList);
            return sortedStateCodeCSVList;
        }catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,"Enter a right file name and type");
        }
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