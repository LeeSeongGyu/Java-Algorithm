package BOJ.DynamicProgramming.P2294_동전2;

import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int[] dp;
    static Set<Integer> coin = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[k + 1];

        for (int i = 0; i < n; i ++) {
            coin.add(Integer.parseInt(br.readLine()));
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution() {
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int c: coin){
            for(int i = c; i <= k; i++){
                if(dp[i - c] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], dp[i - c] + 1);
                }
            }
        }

        return dp[k] == Integer.MAX_VALUE ? -1 : dp[k];
    }
}

