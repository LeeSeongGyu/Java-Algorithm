package BOJ.DynamicProgramming.P1463_1로만들기;

import java.io.*;
import java.util.Arrays;

public class Main {

    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];

        solution();

        bw.write("" + dp[n]);
        bw.flush();
        bw.close();
    }

    public static void solution() {
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        for (int i = 1; i < n; i++) {
            dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
            if (i * 2 <= n) {
                dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
            }
            if (i * 3 <= n) {
                dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
            }
        }
    }
}
