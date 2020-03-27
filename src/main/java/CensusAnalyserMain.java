import com.bridgelabz.CensusAnalyser.*;
import com.google.gson.Gson;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

public class CensusAnalyserMain<E> {

    private static String CSV_FILE_PATH;
    private final Class<E> csvClass;
    List csvUserList = null;

    public CensusAnalyserMain(String filePath,Class<E> csvClass) throws Exception {
        this.csvClass = csvClass;
        this.CSV_FILE_PATH = filePath;
        readFile();
    }

    OpenCSV openCSV = new OpenCSV();

    public static void main(String[] args)  {
        System.out.println("******************** Welcome to Census Analyseer ********************");
    }
    // Read CSV file
    public <E> Integer readFile() throws Exception {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));) {
            List<E> listCSVfile = (List<E>) openCSV.geCSVfileList(reader,csvClass);
            this.csvUserList=listCSVfile;
            return listCSVfile.size();
        }catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,"Enter a right file name and type");
        }
        catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,"Check delimetr and header");
        }
    }
    // Count number of records
    public int countNumberOfRecords(){
        return csvUserList.size();
    }
    // Sorted state wise data
    public String getStateWiseData(String filePath) throws Exception {
        if (csvUserList == null || csvUserList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                    DATA_ARE_NOT_FOUND,"Data are not found");
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            Comparator<IndianStateCensusData> comparator = Comparator.comparing(censusAnalyser -> censusAnalyser.state);
            this.sortCSVFile(csvUserList,comparator);
            String sortedStateCSVList = new Gson().toJson(csvUserList);
            return sortedStateCSVList;
        } catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                    FILE_NOT_FOUND,"Enter a right file name and type");
        }
    }
    // Compare stateCode wise data and return json object
    public String getSortStateCodeAlphabetical(String filePath) throws Exception {
        if (csvUserList == null || csvUserList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.
                    DATA_ARE_NOT_FOUND,"Data are not found");
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            Comparator<StateCodePOJO> comparator = Comparator.comparing(census -> census.stateCode);
            this.sortCSVFile(csvUserList,comparator);
            String sortedStateCodeCSVList = new Gson().toJson(csvUserList);
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