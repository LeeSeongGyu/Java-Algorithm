package 백준.다이나믹프로그래밍.P1915_가장큰정사각형;

import java.util.*;
import java.io.*;

public class Main {

	static int n, m, max;
	static int[][] board;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		dp = new int[n][m];
		max = 0;

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				board[i][j] = str.charAt(j) - '0';
				if (board[i][j] == 1) {
					dp[i][j] = 1;
					max = 1;
				}
			}
		}

		solution();
		bw.write("" + max);
		bw.flush();
		bw.close();
	}

	public static void solution() {
		for (int size = 2; size <= Math.min(n, m); size++) {
			for (int i = 0; i + size - 1 < n; i++) {
				for (int j = 0; j + size - 1 < m; j++) {
					if (dp[i][j] >= size - 1 && dp[i][j + 1] >= size - 1 && dp[i + 1][j] >= size - 1 && dp[i + 1][j + 1] >= size - 1) {
						dp[i][j] = size;
						max = Math.max(max, size * size);
					}
				}
			}
		}
	}
}
