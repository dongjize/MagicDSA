package algorithm.string;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringMatchingTest {

    @Test
    public void testHorspoolMatching() {
        int result = StringMatching.HorspoolMatching("EXAM", "STRINGSEARCHEXAMPLE");
        System.out.println(result);
    }

    @Test
    public void testKMP() {
        int result = StringMatching.KMP("EXAM", "STRINGSEARCHEXAMPLE");
        System.out.println(result);
    }

}