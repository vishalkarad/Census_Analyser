import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserMainTest {

    CensusAnalyserMain censusAnalyser = new CensusAnalyserMain();
    @Test
    public void givenRecordInCSVFile_WhenNumberOfRecordMatch_ThenTrue() throws Exception {
        Integer record=censusAnalyser.readFile("./src/test/resources/StateCensusData.csv");
        Assert.assertEquals((Integer) 29,record);
    }
}
