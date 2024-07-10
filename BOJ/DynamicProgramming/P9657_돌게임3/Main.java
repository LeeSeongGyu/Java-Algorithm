package BOJ.DynamicProgramming.P9657_돌게임3;

import java.io.*;

public class Main {

    static int n;
    static boolean[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        dp = new boolean[1001];

        bw.write(solution());
        bw.flush();
        bw.close();
    }

    public static String solution() {
        dp[1] = true;
        dp[2] = false;
        dp[3] = true;
        dp[4] = true;

        for (int i = 5; i <= n; i++) {
            if (!dp[i - 1] || !dp[i - 3] || !dp[i - 4]) {
                dp[i] = true;  // 상근이가 이길 수 있는 경우
            } else {
                dp[i] = false;  // 상근이가 이길 수 없는 경우
            }
        }

        return dp[n] ? "SK" : "CY";
    }
}
