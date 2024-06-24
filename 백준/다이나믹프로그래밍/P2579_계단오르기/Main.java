package 백준.다이나믹프로그래밍.P2579_계단오르기;

import java.io.*;

public class Main {

    static int n;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        dp = new int[n + 1][2];

        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution(){
        if(n == 1) return arr[1];

        dp[1][1] = arr[1];
        dp[2][0] = arr[2];
        dp[2][1] = arr[1] + arr[2];

        for(int i = 3; i <= n; i++){
            dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]) + arr[i];
            dp[i][1] = dp[i - 1][0] + arr[i];
        }

        return Math.max(dp[n][0], dp[n][1]);
    }
}
