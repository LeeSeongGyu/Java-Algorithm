package BOJ.DynamicProgramming.P4883_삼각그래프;

import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int[][] arr, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int k = 1;
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0) {
				break;
			}
			arr = new int[n + 1][3];
			dp = new int[n + 1][3];

			for (int i = 1; i <= n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 3; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bw.write(k + ". " + solution() + "\n");
			k++;
		}

		bw.flush();
		bw.close();
	}

	public static int solution() {
		dp[1][0] = Integer.MAX_VALUE;
		dp[1][1] = arr[1][1];
		dp[1][2] = arr[1][1] + arr[1][2];

		for (int i = 2; i <= n; i++) {
			dp[i][0] = arr[i][0] + Math.min(
				dp[i - 1][0], dp[i - 1][1]
			);
			dp[i][1] = arr[i][1] + Math.min(
				Math.min(dp[i][0], dp[i - 1][0]),
				Math.min(dp[i - 1][1], dp[i - 1][2])
			);
			dp[i][2] = arr[i][2] + Math.min(
				Math.min(dp[i - 1][1], dp[i - 1][2]),
				dp[i][1]
			);
		}

		return dp[n][1];
	}
}
