package 백준.다이나믹프로그래밍.P2011_암호코드;

import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write("" + solution(br.readLine()));
        bw.flush();
        bw.close();
    }

    public static int solution(String str) {
        int[] arr = new int[str.length() + 1];
        for (int i = 1; i <= str.length(); i++) {
            arr[i] = str.charAt(i - 1) - '0';
        }

        if(arr[1] == 0){
            return 0;
        }

        int[] dp = new int[str.length() + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= str.length(); i++) {
            if (arr[i] == 0) {
                if (arr[i - 1] == 0 || arr[i - 1] >= 3) {
                    return 0;
                }
                dp[i] = dp[i - 2];
                continue;
            }

            if (arr[i - 1] == 0 || arr[i - 1] * 10 + arr[i] > 26) {
                dp[i] = dp[i - 1];
                continue;
            }

            dp[i] = (dp[i - 1] + dp[i- 2]) % MOD;
        }

        return dp[str.length()];
    }
}
