package problem.medium;

import java.util.*;

/**
 * 
 */
@SuppressWarnings("Duplicates") public class CutOffTreesForGolfEvent {

    public int cutOffTree(List<List<Integer>> forest) {
        int count = 0;
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                int v = forest.get(i).get(j);
                if (v == 0)
                    continue;
                if (v > 1)
                    q.add(new int[] { i, j, v });
            }
        }
        int[] current = new int[] { 0, 0, forest.get(0).get(0) };
        while (!q.isEmpty()) {
            int[] p = q.remove();
            int cc = find(current, p, forest);
            if (cc == -1)
                return -1;
            current = p;
            count += cc;
        }
        return count;
    }

    static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    int find(int[] current, int[] target, List<List<Integer>> forest) {
        int m = forest.size();
        int n = forest.get(0).size();
        boolean[][] visited = new boolean[m][n];

        Queue<int[]> q = new LinkedList<>();
        q.add(current);
        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] c = q.remove();
                if (c[0] == target[0] && c[1] == target[1])
                    return count;

                for (int[] d : dir) {
                    int f = c[0] + d[0];
                    int s = c[1] + d[1];
                    if (f < 0 || s < 0 || f >= m || s >= n || forest.get(f).get(s) == 0 || visited[f][s])
                        continue;
                    q.add(new int[] { f, s });
                    visited[f][s] = true;
                }
            }
            count++;
        }
        return -1;
    }

}
