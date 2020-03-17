import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserMainTest {

    CensusAnalyserMain censusAnalyser = new CensusAnalyserMain();
    @Test
    public void givenRecordInCSVFile_WhenNumberOfRecordMatch_ThenTrue() throws Exception {
        Integer record=censusAnalyser.readFile("./src/test/resources/StateCensusData.csv");
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
    public void givenCSVFile_WhenIncorrectTypeMatc_ThenTrue() throws Exception {
        try{
            Integer record=censusAnalyser.readFile("./src/test/resources/StateCensusDataEror.csv");
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,e.type);

        }
    }
}
