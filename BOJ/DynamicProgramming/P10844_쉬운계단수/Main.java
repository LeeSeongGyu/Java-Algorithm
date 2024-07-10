package BOJ.DynamicProgramming.P10844_쉬운계단수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    static int n, DIV = 1_000_000_000;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][10];

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution() {
        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;

        for(int i = 2; i <= n; i++){
            for(int j = 0; j < 10; j++){
                if(j != 0){
                    dp[i][j - 1] = (dp[i][j - 1] + dp[i - 1][j]) % DIV;
                }
                if(j != 9){
                    dp[i][j + 1] = (dp[i][j + 1] + dp[i - 1][j]) % DIV;
                }
            }

        }

        int sum = 0;
        for(int i : dp[n]){
            sum = (sum + i) % DIV;
        }
        return sum;
    }
}
