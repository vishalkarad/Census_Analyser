import com.bridgelabz.CensusAnalyser.*;
import com.google.gson.Gson;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class CensusAnalyserMain {

    OpenCSV openCSV = new OpenCSV();

    public static void main(String[] args)  {
        System.out.println("******************** Welcome to Census Analyseer ********************");
    }
    // Read CSV file
    public Integer readFile(String filePath) throws Exception {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
        ) {
            List<IndianStateCensusData> listCSVfile = openCSV.geCSVfileList(reader,IndianStateCensusData.class);
            return listCSVfile.size();

        }catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,"Enter a right file name and type");
        }
        catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,"Check delimetr and header");
        }
    }
    // Read state code csv file
    public Integer loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            List<StateCodePOJO> listCSVfile = openCSV.geCSVfileList(reader,StateCodePOJO.class);
            return listCSVfile.size();
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
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
        ) {
            List<IndianStateCensusData> listCSVfile = openCSV.geCSVfileList(reader,IndianStateCensusData.class);
            Comparator<IndianStateCensusData> comparator = Comparator.comparing(censusAnalyserDAO -> censusAnalyserDAO.state);
            this.sortCSVFile(listCSVfile,comparator);
            String sortedStateCSVList = new Gson().toJson(listCSVfile);
            return sortedStateCSVList;

        }catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,"Enter a right file name and type");
        }
        catch (CensusAnalyserException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.DATA_ARE_NOT_FOUND,"Check delimetr and header");
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