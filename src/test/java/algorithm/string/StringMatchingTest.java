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

}