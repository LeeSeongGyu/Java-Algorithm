package BOJ.DynamicProgramming.P2748_피보나치수2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int n;
    static long[] dp = new long[91];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        fibo();
        n = Integer.parseInt(br.readLine());
        bw.write("" + dp[n]);
        bw.flush();
        bw.close();
    }

    public static void fibo(){
        dp[1] = 1;
        dp[2] = 1;

        for(int i = 3; i <= 90; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
    }
}
