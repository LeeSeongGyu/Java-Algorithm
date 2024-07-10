package BOJ.DynamicProgramming.P2193_이친수;

import java.io.*;

public class Main {

	static int n;
	static long[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		dp = new long[91];

		bw.write("" + solution());
		bw.flush();
		bw.close();
	}

	public static long solution(){
		dp[0] = 0;
		dp[1] = 1;

		for(int i = 2; i <= n; i++){
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		return dp[n];
	}
}
