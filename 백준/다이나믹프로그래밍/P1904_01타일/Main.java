package 백준.다이나믹프로그래밍.P1904_01타일;

import java.util.*;
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
		int[] dp = new int[1_000_001];
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
		}

		return dp[n];
	}
}
