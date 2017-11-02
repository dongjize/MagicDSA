package algorithm.string;

import org.junit.Assert;
import org.junit.Test;

public class StringMatchingTest {

    @Test
    public void testBruteForceMatching() {
        int result = StringMatching.bruteForcePatternMatching("EXAM", "STRINGSEARCHEXAMPLE");
        Assert.assertEquals(result, 12);
    }

    @Test
    public void testHorspoolMatching() {
        int result = StringMatching.HorspoolMatching("EXAM", "STRINGSEARCHEXAMPLE");
        Assert.assertEquals(result, 12);
    }

    @Test
    public void testKMP() {
        int result = StringMatching.KMP("SEARCHCHINCHIMSTRINGSEARCHCHINCHILLASTRINGSEARCH", "CHINCHILLA");
        System.out.println(result);
        Assert.assertEquals(result, 26);
    }

    @Test
    public void testBM() {
        int result = StringMatching.BM("SEARCHCHINCHIMSTRINGSEARCHCHINCHILLASTRINGSEARCH", "CHINCHILLA");
        System.out.println(result);
        Assert.assertEquals(result, 26);
    }

}