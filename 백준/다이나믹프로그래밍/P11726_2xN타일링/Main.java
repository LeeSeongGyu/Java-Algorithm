package 백준.다이나믹프로그래밍.P11726_2xN타일링;

import java.io.*;

public class Main {

    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        dp = new int[n];

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution(){
        if(n == 1){
            return 1;
        }

        dp[0] = 1;
        dp[1] = 2;

        for(int i = 2; i < n; i++){
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10_007;
        }

        return dp[n-1];
    }
}
