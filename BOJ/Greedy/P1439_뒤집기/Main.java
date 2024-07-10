package BOJ.Greedy.P1439_뒤집기;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();

        bw.write("" + solution(str));
        bw.flush();
        bw.close();
    }

    public static int solution(String str){
        int count = 0;
        char[] s = str.toCharArray();
        char start = s[0];

        for(int i = 1; i < s.length; i++){
            if(s[i] != start && s[i - 1] != s[i]){
                count++;
            }
        }

        return count;
    }
}
