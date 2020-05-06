package problem.medium;

import java.util.*;

/**
 * 
 */
public class ContainerWithMostWater {
    public int maxArea(int[] a) {
        int max = 0;
        int l = 0;
        int r = a.length - 1;

        while (l <= r) {
            max = Math.max(max, Math.min(a[r], a[l]) * (r - l));
            if (a[r] < a[l]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}
