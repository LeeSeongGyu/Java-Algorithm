package BOJ.DynamicProgramming.P1699_제곱수의합;

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
		int root = (int) Math.sqrt(n);
		List<Integer> square = new ArrayList<>();

		for (int i = 1; i <= root; i++) {
			square.add((int) Math.pow(i, 2));
		}

		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 0; i <= n; i++) {
			for (int j : square) {
				if (i + j <= n) {
					dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
				}
			}
		}

		return dp[n];
	}
}
