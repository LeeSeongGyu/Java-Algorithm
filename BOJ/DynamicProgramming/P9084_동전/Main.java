package BOJ.DynamicProgramming.P9084_동전;

import java.io.*;
import java.util.*;

public class Main {

    static int T, n, m;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            m = Integer.parseInt(br.readLine());
            dp = new int[m + 1];

            bw.write(solution() + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static int solution() {
        dp[0] = 1;

        for (int coin : arr) {
            for(int i = 0; i <= m; i++){
                if(i + coin <= m){
                    dp[i + coin] += dp[i];
                }
            }
        }

        return dp[m];
    }
}
