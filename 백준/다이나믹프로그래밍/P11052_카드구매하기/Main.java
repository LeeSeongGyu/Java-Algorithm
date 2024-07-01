package 백준.다이나믹프로그래밍.P11052_카드구매하기;

import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        dp = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution(){
        for(int i = 1; i <= n; i++){
            int max = arr[i];
            for(int j = 1; j <= i/2; j++){
                max = Math.max(max, dp[j] + dp[i - j]);
            }
            dp[i] = max;
        }

        return dp[n];
    }
}
