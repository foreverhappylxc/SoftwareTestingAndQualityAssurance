package problem.medium;

import java.util.*;

/**
 * Created by sherxon on 2/13/17.
 */

public class JumpGame {

    static boolean canJump(int[] a) {
        if (a.length == 0) return true;
        if (a.length == 1) return true;
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (max < i) return false;
            if (max >= a.length - 1) return true;
            max = Math.max(i + a[i], max);
        }
        return max >= a.length - 1;
    }

    public boolean canJump2(int[] a) {
        if (a.length == 0) return true;
        if (a.length == 1) return true;
        Map<Integer, Set<Integer>> map = new HashMap<>(a.length);
        for (int i = 0; i < a.length; i++) {
            if (!map.containsKey(i)) {
                map.put(i, new HashSet<>());
            }
            for (int j = i + 1; j < a[i] + i; j++) {
                map.get(i).add(j);
            }
        }


        boolean[] visited = new boolean[a.length];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            Integer x = q.remove();
            for (Integer integer : map.get(x)) {
                if (integer == a.length - 1) return true;
                if (!visited[integer]) {
                    visited[integer] = true;
                    q.add(integer);
                }

            }
        }
        return false;
    }
}
