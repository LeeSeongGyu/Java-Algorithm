package BOJ.DynamicProgramming.P2133_타일채우기;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());

		bw.write("" + solution(n));
		bw.flush();
		bw.close();
	}

	public static int solution(int n) {
		int[] dp = new int[31];
		dp[0] = 1;

		for (int i = 2; i <= n; i+=2) {
			dp[i] = dp[i - 2] * 3;
			for(int j = i - 4; j >= 0; j -= 2){
				dp[i] += dp[j] * 2;
			}
		}

		return dp[n];
	}
}
