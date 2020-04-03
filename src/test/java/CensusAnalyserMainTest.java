import com.bridgelabz.censusAnalyser.censusDAO.CensusAnalyserDAO;
import com.bridgelabz.censusAnalyser.censusDTO.IndianStateCensusData;
import com.bridgelabz.censusAnalyser.censusDTO.StateCodePOJO;
import com.bridgelabz.censusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusAnalyser.service.CensusAnalyserMain;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserMainTest {
    //Constant
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
    private static final String US_CODE_DATA_CSV_FILE = "./src/test/resources/USCensusData.csv";
    // Create main method Class object
    CensusAnalyserMain censusAnalyserIndia = new CensusAnalyserMain(CensusAnalyserMain.COUNTRY.INDIA);
    CensusAnalyserMain censusAnalyserUS = new CensusAnalyserMain(CensusAnalyserMain.COUNTRY.US);
    @Test
    public void givenRecordInCSVFile_WhenNumberOfRecordMatch_ThenTrue() throws Exception {
        CensusAnalyserMain censusAnalyserMain = new CensusAnalyserMain(CensusAnalyserMain.COUNTRY.INDIA);
        int record=censusAnalyserMain.loadCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH);
        Assert.assertEquals( 29,record);
    }

    @Test
    public void givenStateCensusCSVFile_WhenIncorrectFileName_ThenCustomException() throws Exception {
        try {
            censusAnalyserIndia.loadCensusData(STATE_CENSUS_DATA_CSV_FILE_INCORRECT_PATH);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,e.type);
        }
    }

    @Test
    public void givenCSVFile_WhenIncorrectTypeMatch_ThenTrue() throws Exception {
        try{
            censusAnalyserIndia.loadCensusData(STATE_CENSUS_DATA_CSV_INCORRECT_FILE_TYPE);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,e.type);
        }
    }
    @Test
    public void givenCSVFile_WhenIncorrectDelimiter_ThenTrue() throws Exception {
        try{
            censusAnalyserIndia.loadCensusData(STATE_CENSUS_DATA_CSV_INCORRECT_DELIMITER_FILE_TYPE);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,e.type);
        }
    }
    @Test
    public void givenCSVFile_WhenIncorrectHeader_ThenTrue() throws Exception {
        try{
            censusAnalyserIndia.loadCensusData(STATE_CENSUS_DATA_CSV_INCORRECT_HEADER_FILE);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,e.type);
        }
    }
    @Test
    public void givenCheckWithStateCodeCSV_WhenNumberOfRecordMatches_ThenTrue() throws Exception {
        int stateCensusDataRecords = censusAnalyserIndia.loadCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH,STATE_CODE_DATA_CSV_FILE_PATH);
        Assert.assertEquals(29,stateCensusDataRecords);
    }
    @Test
    public void givenStateCodeCSVFile_WhenIncorrectFileName_ThenCustomException() {
        try {
            censusAnalyserIndia.loadCensusData(STATE_CODE_DATA_CSV_FILE_INCORRECT_PATH);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,e.type);
        }
    }
    @Test
    public void givenStateCodeCSVFile_WhenIncorrectTypeMatch_ThenTrue() throws Exception {
        try{
            censusAnalyserIndia.loadCensusData(STATE_CODE_DATA_CSV_FILE_INCORRECT_TYPE);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,e.type);
        }
    }
    @Test
    public void givenStateCodeeCSVFile_WhenIncorrectDelimiter_ThenTrue() throws Exception {
        try{
            censusAnalyserIndia.loadCensusData(STATE_CODE_DATA_CSV_FILE_INCORRECT_DELIMITER);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,e.type);
        }
    }
    @Test
    public void givenStateCodeCSVFile_WhenIncorrectHeader_ThenTrue() throws Exception {
        try{
            censusAnalyserIndia.loadCensusData(STATE_CODE_DATA_CSV_FILE_INCORRECT_HEADER);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,e.type);
        }
    }
    @Test
    public void givenStateCensusData_WhenStateWiseSort_ThenReturn() {
        try{
            censusAnalyserIndia.loadCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH);
            String sortedStateList = censusAnalyserIndia.getSortCensusData(CensusAnalyserMain.SORTING_MODE.STATE);
            IndianStateCensusData sortedStateArray[] = new Gson().fromJson(sortedStateList,IndianStateCensusData[].class);
            Assert.assertEquals("Andhra Pradesh",sortedStateArray[0].state);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void givenStateCodeData_WhenStateCodeWiseSort_ThenReturn() {
        try{
            censusAnalyserIndia.loadCensusData(STATE_CODE_DATA_CSV_FILE_PATH);
            String sortedStateCodeData = censusAnalyserIndia.getSortCensusData (CensusAnalyserMain.SORTING_MODE.STATECODE);
            StateCodePOJO sortedStateCodeArray[] = new Gson().fromJson(sortedStateCodeData,StateCodePOJO[].class);
            Assert.assertEquals("AD",sortedStateCodeArray[0].stateCode);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void givenStateCensusData_WhenPopulationWiseSort_ThenReturn() {
        try{
            censusAnalyserIndia.loadCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH);
            String sortedStateList = censusAnalyserIndia.getSortCensusData(CensusAnalyserMain.SORTING_MODE.POPULATION);
            CensusAnalyserDAO sortedPopulationArray[] = new Gson().fromJson(sortedStateList,CensusAnalyserDAO[].class);
            Assert.assertEquals(199812341,sortedPopulationArray[0].population);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void givenTheStateCensusData_WhenSortedOnDensityPerSqKm_ShouldReturnSortedResult() {
        try {
            censusAnalyserIndia.loadCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyserIndia.getSortCensusData(CensusAnalyserMain.SORTING_MODE.DENSITY);
            CensusAnalyserDAO sortedDensityPerSqKmArray[] = new Gson().
                    fromJson(sortedCensusData,CensusAnalyserDAO[].class);
            Assert.assertEquals(1102,sortedDensityPerSqKmArray[0].density);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenStateCensusData_WhenSortedOnAreaInPerSqKm_ThenReturnSortedData() {
        try {
            censusAnalyserIndia.loadCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyserIndia.getSortCensusData(CensusAnalyserMain.SORTING_MODE.AREA);
            CensusAnalyserDAO csvStateAreaCensuses[] = new Gson().fromJson(sortedCensusData, CensusAnalyserDAO[].class);
            Assert.assertEquals(342239, csvStateAreaCensuses[0].area);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenTheUSStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            censusAnalyserUS.loadCensusData(US_CODE_DATA_CSV_FILE);
            String sortedStateList = censusAnalyserUS.getSortCensusData(CensusAnalyserMain.SORTING_MODE.POPULATION);
            CensusAnalyserDAO csvStateAreaCensuses[] = new Gson().fromJson(sortedStateList, CensusAnalyserDAO[].class);
            Assert.assertEquals("California", csvStateAreaCensuses[0].state);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}