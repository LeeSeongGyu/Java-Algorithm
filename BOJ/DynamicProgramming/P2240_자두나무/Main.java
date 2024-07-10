package BOJ.DynamicProgramming.P2240_자두나무;

import java.io.*;
import java.util.*;

public class Main {
    static int t, w;
    static int[] arr;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        arr = new int[t + 1];
        dp = new int[t + 1][w + 1][3];

        for(int i = 1; i <= t; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution(){
        for(int[][] d : dp){
            for(int[] arr : d){
                Arrays.fill(arr, -1);
            }
        }
        dp[0][0][1] = 0;

        for(int i = 1; i <= t; i++){
            for(int j = 0; j <= w; j++){
                for(int k = 1; k <= 2; k++){
                    if(dp[i - 1][j][k] != -1){
                        if(k == arr[i]){
                            // 가만히 있고 먹는다
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k] + 1);
                        } else {
                            // 움직여서 먹는다
                            if(j + 1 <= w){
                                if(k == 1){
                                    dp[i][j + 1][2] = Math.max(dp[i][j + 1][2], dp[i - 1][j][k] + 1);
                                } else {
                                    dp[i][j + 1][1] = Math.max(dp[i][j + 1][1], dp[i - 1][j][k] + 1);
                                }
                            }
                            // 가만히 있고 먹지 않는다
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k]);
                        }
                    }
                }
            }
        }

        int max = 0;
        for(int j = 0; j <= w; j++){
            for(int k = 1; k <= 2; k++){
                max = Math.max(max, dp[t][j][k]);
            }
        }

        return max;
    }
}
