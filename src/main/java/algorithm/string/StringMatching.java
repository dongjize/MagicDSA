package algorithm.string;

import java.util.HashMap;

/*
 * Description: 字符串匹配
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
            j = 0;
            while (j < p.length() && t.charAt(i + j) == p.charAt(j)) {
                j++;
            }
            if (j >= p.length()) {
                return i;
            }
        }
        return -1;

    }


    //--------------------------------------- KMP ------------------------------------------


    /**
     * Knuth-Morris-Pratt(KMP) Algorithm
     * Time Complexity: O(n+m)
     * <p>
     * 不断将模式串与文本串比较，一旦局部失配，则利用此前比较所
     * 给出的信息，尽可能长距离地移动模式串
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
    public static int KMP(String t, String p) {
        int[] next = buildNext(p);
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
     * 建立模式串p的next[]表
     *
     * @param p
     * @return
     */
    protected static int[] buildNext(String p) {
        int[] next = new int[p.length()];//next[]表
        int j = 0;//“主”串指针
        int k = next[0] = -1;//“模式”串指针

        while (j < p.length() - 1)
            if (k < 0 || p.charAt(j) == p.charAt(k)) {//匹配
                j++;
                k++;
                next[j] = k;//此句可以改进...
            } else {//失配
                k = next[k];
            }
        for (j = 0; j < p.length(); j++) {
            System.out.print("\t" + p.charAt(j));
        }
        System.out.print("\n");
        showNextTable(next, 0, p.length());

        return next;
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
            } else { //失配
                t = next[t];
            }
        for (j = 0; j < p.length(); j++) {
            System.out.print("\t" + p.charAt(j));
        }
        System.out.print("\n");
        showNextTable(next, 0, p.length());

        return next;
    }


    protected static void showProgress(String T, String P, int i, int j) {
        int t;

        System.out.println("-------------------------------------------");
        for (t = 0; t < T.length(); t++) {
            System.out.print("\t" + T.charAt(t));
        }
        System.out.print("\n");

        if (0 <= i + j) {
            for (t = 0; t < i + j; t++) {
                System.out.print("\t");
            }
            System.out.print("\t|");
        }
        System.out.println();

        for (t = 0; t < i; t++) {
            System.out.print("\t");
        }
        for (t = 0; t < P.length(); t++) {
            System.out.print("\t" + P.charAt(t));
        }
        System.out.print("\n");
        System.out.println();
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


    // ---------------------------------------- BM ------------------------------------------

    /**
     * 字符集规模
     */
    private final static int CARD_CHAR_SET = 256;

    /**
     * Boyer-Boore Algorithm
     * Time Complexity: O(m+n)
     *
     * @param text
     * @param pattern
     * @return
     */
    public static int BM(String text, String pattern) {
        int[] badCharacters = buildBadCharacterTable(pattern);
        int[] goodSuffixes = buildGoodSuffixTable(pattern);

        int i = 0;
        while (text.length() - pattern.length() >= i) {
            int j = pattern.length() - 1;
            // 从模式串最末尾的字符开始，自右向左比较
            while (pattern.charAt(j) == text.charAt(i + j)) {
                if (--j < 0) {
                    break;
                }
                showProgress(text, pattern, i, j);
                System.out.print("\n");
            }
            if (j < 0) { //若极大匹配后缀 == 整个模式串（说明已经完全匹配）
                break; //返回匹配位置
            } else {
                // 否则，在位移量bad character和good suffix之间选择大者，相应地移动模式串
                i += Math.max(goodSuffixes[j], j - badCharacters[text.charAt(i + j)]);
            }
        }

        return i;
    }

    private static int[] buildBadCharacterTable(String p) {
        int[] bc = new int[CARD_CHAR_SET];
        int j;
        for (j = 0; j < CARD_CHAR_SET; j++) {
            bc[j] = -1; //首先假设该字符没有在P中出现
        }
        for (j = 0; j < p.length(); j++) {
            // p[j]曾出现在位置j——鉴于这里的扫描次序是从左到右（即下标递增），
            // 故只要某个字符ch在P中出现过，bc[ch]就会记录下其中的最靠右的出现位置
            bc[p.charAt(j)] = j;
        }
        System.out.println("-- BC[] Table ---------------");
        for (j = 0; j < CARD_CHAR_SET; j++) {
            if (bc[j] >= 0) {
                System.out.print("\t" + (char) j);
            }
        }
        System.out.println();
        for (j = 0; j < CARD_CHAR_SET; j++) {
            if (bc[j] >= 0) {
                System.out.print("\t" + bc[j]);
            }
        }
        System.out.println("\n");
        return bc;
    }


    private static int[] buildGoodSuffixTable(String p) {
        int m = p.length();
        int[] suffixSize = computeSuffixSize(p);
        int[] goodSuffix = new int[m];
        int j;
        for (j = 0; j < m; j++) {
            goodSuffix[j] = m;
        }
        int i = 0;
        for (j = m - 1; j >= -1; j--) {
            if (j == -1 || j + 1 == suffixSize[j]) {
                for (; i < m - j - 1; i++) {
                    if (goodSuffix[i] == m) {
                        goodSuffix[i] = m - j - 1;
                    }
                }
            }
        }
        for (j = 0; j < m - 1; j++) {
            goodSuffix[m - suffixSize[j] - 1] = m - j - 1;
        }
        System.out.println("-- GS[] Table ---------------");
        for (j = 0; j < m; j++) {
            System.out.print("\t" + p.charAt(j));
        }
        System.out.println();
        for (j = 0; j < m; j++) {
            System.out.print("\t" + goodSuffix[j]);
        }
        System.out.println("\n");
        return (goodSuffix);
    }


    protected static int[] computeSuffixSize(String p) {
        int m = p.length();
        int[] ss = new int[m];//Suffix Size Table
        int s, t;//子串P[s+1, ..., t]与后缀P[m+s-t, ..., m-1]匹配
        int j;//当前字符的位置

        // 对最后一个字符而言，与之匹配的最长后缀就是整个P串，故...
        ss[m - 1] = m;

        // 从倒数第二个字符起，自右向左扫描P，依次计算出SS[]其余各项
        s = m - 1;
        t = m - 2;
        for (j = m - 2; j >= 0; j--) {
            if ((j > s) && (j - s > ss[(m - 1 - t) + j])) {
                ss[j] = ss[(m - 1 - t) + j];
            } else {
                t = j; //与后缀匹配之子串的终点，就是当前字符
                s = Math.min(s, j); //与后缀匹配之子串的起点
                while ((0 <= s) && (p.charAt(s) == p.charAt((m - 1 - t) + s))) {
                    s--; //似乎是二重循环，难道复杂度是平方量级？
                }
                ss[j] = t - s;//与后缀匹配之最长子串的长度
            }
        }
        System.out.println("-- SS[] Table -------");
        for (j = 0; j < m; j++) System.out.print("\t" + p.charAt(j));
        System.out.println();
        for (j = 0; j < m; j++) System.out.print("\t" + ss[j]);
        System.out.println("\n");
        return (ss);
    }


    // ------------------------------------ Horspool's --------------------------------------

    /**
     * Horspool's string matching
     *
     * @param p
     * @param t
     * @return
     */
    public static int HorspoolMatching(String p, String t) {
        HashMap<Character, Integer> table = shiftTable(p);
        int i = p.length() - 1;
        while (i < table.size()) {
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

    /**
     * Build the shift table of the alphabet
     *
     * @param pattern
     * @return
     */
    private static HashMap<Character, Integer> shiftTable(String pattern) {
        HashMap<Character, Integer> shift = new HashMap<>();
        for (char i = 'A'; i <= 'Z'; i++) {
            shift.put(i, pattern.length());
        }
        // Scan the pattern to update shift table.
        for (int j = 0; j < pattern.length() - 1; j++) {
            // The value is the distance from the last existed position to the tail.
            shift.put(pattern.charAt(j), pattern.length() - (j + 1));
        }
        return shift;

    }
}
