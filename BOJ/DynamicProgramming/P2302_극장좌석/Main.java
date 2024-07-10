package BOJ.DynamicProgramming.P2302_극장좌석;

import java.io.*;

public class Main {

    static int n, m;
    static int[] vip;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        vip = new int[m + 1];
        dp = new int[41];

        for (int i = 1; i <= m; i++) {
            vip[i] = Integer.parseInt(br.readLine());
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution(){
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int count = 1;
        for(int i = 1; i <= m; i++){
            count *= dp[vip[i] - vip[i - 1] - 1];
        }
        count *= dp[n - vip[m]];

        return count;
    }
}
