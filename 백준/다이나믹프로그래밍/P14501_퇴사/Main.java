package 백준.다이나믹프로그래밍.P14501_퇴사;

import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] t, p, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        t = new int[n];
        p = new int[n];
        dp = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution() {
        for (int i = 0; i < n; i++) {
            int end = i + t[i] - 1;
            if (end >= n) {
                continue;
            }

            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                max = Math.max(max, dp[j]);
            }

            dp[end] = Math.max(dp[end], max + p[i]);
        }

        return Arrays.stream(dp).max().getAsInt();
    }
}
