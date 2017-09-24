package algorithm.string;

import org.junit.Test;

public class StringMatchingTest {

    @Test
    public void testHorspoolMatching() {
        int result = StringMatching.HorspoolMatching("EXAM", "STRINGSEARCHEXAMPLE");
        System.out.println(result);
    }

    @Test
    public void testKMP() {
        int result = StringMatching.KMP("SEARCHCHINCHIMSTRINGSEARCHCHINCHILLASTRINGSEARCH", "CHINCHILLA");
        System.out.println(result);
    }

    @Test
    public void testBM() {
        int result = StringMatching.BM("SEARCHCHINCHIMSTRINGSEARCHCHINCHILLASTRINGSEARCH", "CHINCHILLA");
        System.out.println(result);
    }

}