package 백준.다이나믹프로그래밍.P10942_펠린드롬;

import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static int[] arr;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		dp = new int[n + 1][n + 1];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dp();

		m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			bw.write(dp[start][end] + "\n");
		}

		bw.flush();
		bw.close();
	}

	public static void dp() {
		// 길이 1
		for (int i = 1; i <= n; i++) {
			dp[i][i] = 1;
		}

		// 길이 2
		for(int i = 1; i < n; i++){
			if(arr[i] == arr[i + 1]){
				dp[i][i+1] = 1;
			}
		}

		// 길이 3 이상
		for (int i = 3; i <= n; i++) {
			for (int j = 1; j + i - 1 <= n; j++) {
				if (dp[j + 1][j + i - 2] == 1 && arr[j] == arr[j + i - 1]) {
					dp[j][j + i - 1] = 1;
				}
			}
		}
	}
}
