package 백준.다이나믹프로그래밍.P9465_스티커;

import java.io.*;
import java.util.*;

public class Main {

    static int T, n;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n + 1][2];
            dp = new int[n + 1][2];

            for (int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int k = 1; k <= n; k++) {
                    arr[k][j] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write(solution() + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static int solution() {
        dp[1][0] = arr[1][0];
        dp[1][1] = arr[1][1];

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = arr[i][j] + Math.max(
                    Math.max(dp[i - 2][0], dp[i - 2][1]),
                    j == 0 ? dp[i - 1][1] : dp[i - 1][0]
                );
            }
        }

        return Math.max(dp[n][0], dp[n][1]);
    }
}
