import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserMainTest {

    CensusAnalyserMain censusAnalyser = new CensusAnalyserMain();
    @Test
    public void givenRecordInCSVFile_WhenRecordMatch_ThenTrue() throws Exception {
        Integer record=censusAnalyser.readFile();
        Assert.assertEquals((Integer) 30,record);
    }
}
