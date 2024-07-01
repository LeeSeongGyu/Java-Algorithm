package 백준.다이나믹프로그래밍.P1912_연속합;

import java.util.*;
import java.io.*;

public class Main {

    static int n, max;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        max = Integer.MIN_VALUE;
        arr = new int[n];
        dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution();
        bw.write("" + max);
        bw.flush();
        bw.close();
    }

    public static void solution(){
        dp[0] = arr[0];

        for(int i = 1; i < n; i++){
            if(arr[i] < 0){
                max = Math.max(max, dp[i - 1]);
            }
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
        }

        max = Math.max(max, dp[n - 1]);
    }
}
