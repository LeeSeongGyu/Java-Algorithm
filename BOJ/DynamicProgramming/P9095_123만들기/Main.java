package BOJ.DynamicProgramming.P9095_123만들기;

import java.io.*;

public class Main {

    static int T, n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++){
            n = Integer.parseInt(br.readLine());
            dp = new int[n + 1];
            bw.write(solution() + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static int solution(){
        if(n == 1) return 1;
        if(n == 2) return 2;
        if(n == 3) return 4;

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i = 4; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }
}
