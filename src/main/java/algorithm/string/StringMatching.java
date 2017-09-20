package algorithm.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-20
 * @Time: 20:58
 */
public class StringMatching {

    public static int HorspoolMatching(String p, String t) {
        HashMap<Character, Integer> table = shiftTable(p);
        int i = p.length() - 1;
        while (i <= table.size() - 1) {
            int k = 0;

            while (k < p.length() && p.charAt(p.length() - 1 - k) == t.charAt(i - k)) {
                k++;
            }
            if (k == p.length()) {
                return i - p.length() + 1;
            } else {
                i += table.get(t.charAt(i));
            }
        }
        return -1;
    }

    private static HashMap shiftTable(String pattern) {
        HashMap<Character, Integer> shift = new HashMap<>();
        for (char i = 'A'; i <= 'Z'; i++) {
            shift.put(i, pattern.length());
        }
        for (int j = 0; j < pattern.length() - 1; j++) {
            shift.put(pattern.charAt(j), pattern.length() - (j + 1));
        }
        return shift;

    }
}
