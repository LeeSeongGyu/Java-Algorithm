package 백준.다이나믹프로그래밍.P15486_퇴사2;

import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] t, p, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        t = new int[n + 1];
        p = new int[n + 1];
        dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution() {
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);

            int end = i + t[i] - 1;
            if (end > n) {
                continue;
            }

            dp[end] = Math.max(dp[end], dp[i - 1] + p[i]);
        }

        return dp[n];
    }
}
