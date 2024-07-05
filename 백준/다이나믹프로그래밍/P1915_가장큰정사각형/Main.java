package 백준.다이나믹프로그래밍.P1915_가장큰정사각형;

import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static int[][] board;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n + 1][m + 1];
		dp = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			String str = br.readLine();
			for (int j = 1; j <= m; j++) {
				board[i][j] = str.charAt(j - 1) - '0';
			}
		}

		bw.write("" + solution());
		bw.flush();
		bw.close();
	}

	public static int solution() {
		int max = 0;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (board[i][j] == 1) {
					dp[i][j] = 1 + Math.min(
						dp[i - 1][j - 1],
						Math.min(dp[i - 1][j], dp[i][j - 1])
					);
					max = Math.max(max, dp[i][j] * dp[i][j]);
				}
			}
		}
		return max;
	}
}
