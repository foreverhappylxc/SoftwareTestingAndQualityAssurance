package problem.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Why Did you create this class? what does it do?
 */
public class FindAndReplaceString {

    public String findReplaceString(String s, int[] indexes, String[] sources, String[] targets) {
        if (s == null || s.length() == 0)
            return s;
        StringBuilder sb = new StringBuilder();
        Pair[] p = new Pair[indexes.length];
        for (int i = 0; i < p.length; i++) {
            p[i] = new Pair(i, indexes[i]);
        }
        Arrays.sort(p, Comparator.comparingInt(a -> a.val));

        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            if (i < p.length && p[i].val == j) {
                if (s.substring(p[i].val, p[i].val + sources[p[i].id].length()).equals(sources[p[i].id])) {
                    sb.append(targets[p[i].id]);
                    j += sources[p[i].id].length() - 1;
                } else {
                    sb.append(s.charAt(i));
                }
                i++;
            } else {
                sb.append(s.charAt(j));
            }
        }

        return sb.toString();
    }

    class Pair {
        int id, val;

        Pair(int i, int v) {
            id = i;
            val = v;
        }

    }
}

