import com.bridgelabz.CensusAnalyser.CSVStates;
import com.bridgelabz.CensusAnalyser.CensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserMainTest {

    CensusAnalyserMain censusAnalyser = new CensusAnalyserMain();
    CSVStates csvStates = new CSVStates();
    @Test
    public void givenRecordInCSVFile_WhenNumberOfRecordMatch_ThenTrue() throws Exception {
        Integer record=censusAnalyser.readFile("./src/test/resources/StateCensusDat.csv");
        Assert.assertEquals((Integer) 29,record);
    }
    @Test
    public void givenStateCensusCSVFile_WhenIncorrectFileName_ThenCustomException() throws Exception {
        try {
            censusAnalyser.readFile("./src/test/resources/StateCensus.csv");
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,e.type);
        }
    }
    @Test
    public void givenCSVFile_WhenIncorrectTypeMatch_ThenTrue() throws Exception {
        try{
            Integer record=censusAnalyser.readFile("./src/test/resources/StateCensusData.cs");
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,e.type);
        }
    }
    @Test
    public void givenCSVFile_WhenIncorrectDelimiter_ThenTrue() throws Exception {
        try{
            Integer record=censusAnalyser.readFile("./src/test/resources/StateCensusDataEror.csv");
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,e.type);
        }
    }
    @Test
    public void givenCSVFile_WhenIncorrectHeader_ThenTrue() throws Exception {
        try{
            Integer record=censusAnalyser.readFile("./src/test/resources/StateCensusDataErorType.csv");
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,e.type);
        }
    }

    @Test
    public void givenCheckWithStateCodeCSV_WhenNumberOfRecordMatches_ThenTrue() throws Exception {
        int stateCensusDataRecords = csvStates.loadIndianStateCodeData("./src/test/resources/StateCo.csv");
        Assert.assertEquals(37,stateCensusDataRecords);
    }
    @Test
    public void givenStateCodeCSVFile_WhenIncorrectFileName_ThenCustomException() {
        try {
            csvStates.loadIndianStateCodeData("./src/test/resources/State.csv");
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,e.type);
        }
    }
    @Test
    public void givenStateCodeCSVFile_WhenIncorrectTypeMatch_ThenTrue() throws Exception {
        try{
            csvStates.loadIndianStateCodeData("./src/test/resources/StateCensusData.cs");
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,e.type);
        }
    }
}
