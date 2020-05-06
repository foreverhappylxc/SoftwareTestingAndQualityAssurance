package problem.medium;

import java.util.Arrays;
import java.util.Stack;

public class AsteroidCollision {

    static public int[] asteroidCollision(int[] a) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < a.length; i++) {
            st.push(a[a.length - 1 - i]);
        }
        Stack<Integer> st2 = new Stack<>();
        while (!st.isEmpty()) {
            int pop = st.pop();
            if (st2.isEmpty()) {
                st2.add(pop);
                continue;
            }
            if ((pop < 0 && st2.peek() > 0)) {
                int pop2 = st2.pop();
                if (Math.abs(pop2) > Math.abs(pop)) {
                    st2.add(pop2);
                } else if (Math.abs(pop2) <= Math.abs(pop)) {
                    st.add(pop);
                }
            } else {
                st2.add(pop);
            }
        }
        int[] res = new int[st2.size()];
        int k = 0;
        while (!st2.isEmpty()) {
            res[res.length - 1 - k] = st2.pop();
            k++;
        }
        return res;
    }
}
