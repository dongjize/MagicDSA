package algorithm.string;

import java.util.HashMap;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-20
 * @Time: 20:58
 */
public class StringMatching {

    /**
     * Brute-Force String Matching
     * Time Complexity: O(mn)
     *
     * @param p
     * @param t
     * @return
     */
    public static int bruteForcePatternMatching(String p, String t) {
        int i, j;
        for (i = 0; i < t.length() - p.length(); i++) {
            for (j = 0; j < p.length(); j++) {
                if (t.charAt(i + j) != p.charAt(j)) {
                    break;
                }

            }
            if (j >= p.length()) {
                break;
            }
        }
        return i;

    }


    /**
     * Knuth-Morris-Pratt(KMP) Algorithm
     * <p>
     * //////////////////////////////////////////////////////////////////////////
     * // T: 0 1 . . . i i+1 . . . i+k . . n-1
     * // --------------------|-------------------|------------
     * // P: j j+1 . . . j+k . .
     * // |-------------------|
     * //////////////////////////////////////////////////////////////////////////
     *
     * @return
     */
    public static int KMP(String p, String t) {
        int[] next = buildNextImproved(p);
        int i = 0; // t指针
        int j = 0; // p指针
        while (j < p.length() && i < t.length()) {
            showProgress(t, p, i - j, j);
            showNextTable(next, i - j, p.length());
            System.out.println();
            if (j < 0 || t.charAt(i) == p.charAt(j)) {
                // step to the next pair
                i++;
                j++;
            } else {
                j = next[j]; // right move p
            }
        }
        return i - j;
    }

    /**
     * 建立模式串P的next[]表（改进版本）
     *
     * @param p
     * @return
     */
    private static int[] buildNextImproved(String p) {
        int[] next = new int[p.length()];//next[]表

        int j = 0;//“主”串指针
        int t = next[0] = -1;//“模式”串指针

        while (j < p.length() - 1)
            if (0 > t || p.charAt(j) == p.charAt(t)) {//匹配
                j++;
                t++;
                next[j] = (p.charAt(j) != p.charAt(t)) ? t : next[t];//注意此句与未改进之前的区别
            } else//失配
                t = next[t];
        for (j = 0; j < p.length(); j++) System.out.print("\t" + p.charAt(j));
        System.out.print("\n");
        showNextTable(next, 0, p.length());

        return (next);
    }


    private static void showNextTable(int[] N, int offset, int length) {
        int i;
        for (i = 0; i < offset; i++) {
            System.out.print("\t");
        }
        for (i = 0; i < length; i++) {
            System.out.print("\t" + N[i]);
        }
        System.out.print("\n\n");
    }


    protected static void showProgress(String T, String P, int i, int j) {
        int t;

        System.out.println("-------------------------------------------");
        for (t = 0; t < T.length(); t++) System.out.print("\t" + T.charAt(t));
        System.out.print("\n");

        if (0 <= i + j) {
            for (t = 0; t < i + j; t++) System.out.print("\t");
            System.out.print("\t|");
        }
        System.out.println();

        for (t = 0; t < i; t++) System.out.print("\t");
        for (t = 0; t < P.length(); t++) System.out.print("\t" + P.charAt(t));
        System.out.print("\n");
        System.out.println();
    }


    /**
     * @param p
     * @param t
     * @return
     */
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
