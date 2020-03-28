import com.bridgelabz.censusAnalyser.CensusAnalyserException;
import com.bridgelabz.censusAnalyser.IndianStateCensusData;
import com.bridgelabz.censusAnalyser.StateCodePOJO;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserMainTest {

    private static final String STATE_CENSUS_DATA_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
    private static final String STATE_CENSUS_DATA_CSV_FILE_INCORRECT_PATH = "./src/test/resources/StateCensus.csv";
    private static final String STATE_CENSUS_DATA_CSV_INCORRECT_FILE_TYPE = "./src/test/resources/StateCensusData.cs";
    private static final String STATE_CENSUS_DATA_CSV_INCORRECT_DELIMITER_FILE_TYPE =
                                                                       "./src/test/resources/StateCensusDataEror.csv";
    private static final String STATE_CENSUS_DATA_CSV_INCORRECT_HEADER_FILE =
                                                                   "./src/test/resources/StateCensusDataErorType.csv";
    private static final String STATE_CODE_DATA_CSV_FILE_PATH = "./src/test/resources/StateCo.csv";
    private static final String STATE_CODE_DATA_CSV_FILE_INCORRECT_PATH = "./src/test/resources/State.csv";
    private static final String STATE_CODE_DATA_CSV_FILE_INCORRECT_TYPE = "./src/test/resources/StateCo.cs";
    private static final String STATE_CODE_DATA_CSV_FILE_INCORRECT_DELIMITER =
                                                              "./src/test/resources/StateCodeIncorectDelimiter.csv";
    private static final String STATE_CODE_DATA_CSV_FILE_INCORRECT_HEADER =
                                                                "./src/test/resources/StateCensusDataErorType.csv";

    CensusAnalyserMain censusAnalyser = new CensusAnalyserMain();
    @Test
    public void givenRecordInCSVFile_WhenNumberOfRecordMatch_ThenTrue() throws Exception {
        Integer record=censusAnalyser.readFile(STATE_CENSUS_DATA_CSV_FILE_PATH);
        Assert.assertEquals((Integer) 29,record);
    }
    @Test
    public void givenStateCensusCSVFile_WhenIncorrectFileName_ThenCustomException() throws Exception {
        try {
            censusAnalyser.readFile(STATE_CENSUS_DATA_CSV_FILE_INCORRECT_PATH);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,e.type);
        }
    }
    @Test
    public void givenCSVFile_WhenIncorrectTypeMatch_ThenTrue() throws Exception {
        try{
            censusAnalyser.readFile(STATE_CENSUS_DATA_CSV_INCORRECT_FILE_TYPE);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,e.type);
        }
    }
    @Test
    public void givenCSVFile_WhenIncorrectDelimiter_ThenTrue() throws Exception {
        try{
            censusAnalyser.readFile(STATE_CENSUS_DATA_CSV_INCORRECT_DELIMITER_FILE_TYPE);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,e.type);
        }
    }
    @Test
    public void givenCSVFile_WhenIncorrectHeader_ThenTrue() throws Exception {
        try{
            censusAnalyser.readFile(STATE_CENSUS_DATA_CSV_INCORRECT_HEADER_FILE);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,e.type);
        }
    }
    @Test
    public void givenCheckWithStateCodeCSV_WhenNumberOfRecordMatches_ThenTrue() throws Exception {
        int stateCensusDataRecords = censusAnalyser.loadIndianStateCodeData(STATE_CODE_DATA_CSV_FILE_PATH);
        Assert.assertEquals(37,stateCensusDataRecords);
    }
    @Test
    public void givenStateCodeCSVFile_WhenIncorrectFileName_ThenCustomException() {
        try {
            censusAnalyser.loadIndianStateCodeData(STATE_CODE_DATA_CSV_FILE_INCORRECT_PATH);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,e.type);
        }
    }
    @Test
    public void givenStateCodeCSVFile_WhenIncorrectTypeMatch_ThenTrue() throws Exception {
        try{
            censusAnalyser.loadIndianStateCodeData(STATE_CODE_DATA_CSV_FILE_INCORRECT_TYPE);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,e.type);
        }
    }
    @Test
    public void givenStateCodeeCSVFile_WhenIncorrectDelimiter_ThenTrue() throws Exception {
        try{
            censusAnalyser.loadIndianStateCodeData(STATE_CODE_DATA_CSV_FILE_INCORRECT_DELIMITER);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,e.type);
        }
    }
    @Test
    public void givenStateCodeCSVFile_WhenIncorrectHeader_ThenTrue() throws Exception {
        try{
            censusAnalyser.loadIndianStateCodeData(STATE_CODE_DATA_CSV_FILE_INCORRECT_HEADER);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,e.type);
        }
    }
    @Test
    public void givenStateCensusData_WhenStateWiseSort_ThenReturn() {
        try{
            censusAnalyser.readFile(STATE_CENSUS_DATA_CSV_FILE_PATH);
            String sortedStateList = censusAnalyser.getStateWiseData(STATE_CENSUS_DATA_CSV_FILE_PATH);
            IndianStateCensusData sortedStateArray[] = new Gson().fromJson(sortedStateList,IndianStateCensusData[].class);
            Assert.assertEquals("Andhra Pradesh",sortedStateArray[0].state);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
   @Test
    public void givenStateCodeData_WhenStateCodeWiseSort_ThenReturn() {
        try{
            censusAnalyser.loadIndianStateCodeData (STATE_CODE_DATA_CSV_FILE_PATH);
            String sortedStateCodeData = censusAnalyser.getSortStateCodeAlphabetical (STATE_CODE_DATA_CSV_FILE_PATH);
            StateCodePOJO sortedStateCodeArray[] = new Gson().fromJson(sortedStateCodeData,StateCodePOJO[].class);
            Assert.assertEquals("AD",sortedStateCodeArray[0].stateCode);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}