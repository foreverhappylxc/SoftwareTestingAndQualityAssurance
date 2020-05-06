package problem.medium;

/**
 * Created by sherxon on 1/11/17.
 */
public class WordSearch2 {
    /**
    * This is cute solution using global variable
    * */
     class Solution {
        private boolean found=false;

        public boolean exist(char[][] a, String word) {
            char[] s=word.toCharArray();

            for(int i=0; i<a.length && !found; i++){
                for(int j=1; j<a[i].length && !found; j++){
                    if(a[i][j]==s[0])
                        go(a, i, j, s, 0);
                }
            }
            return found;
        }
        void go(char[][]a, int i, int j, char[] s, int index){
            if(i<0 || j<0 || i>=a.length || j>=a[i].length || index >= s.length) return;

            if(s[index]!=a[i][j] || found)return;

            if(index==s.length-1){
                found=true;
                return;
            }
            char temp=a[i][j];
            a[i][j]='.';
            go(a, i+1, j, s, index+1);
            go(a, i, j+1, s, index+1);
            go(a, i-1, j, s, index+1);
            go(a, i, j-1, s, index+1);
            a[i][j]=temp;
        }
    }
}
