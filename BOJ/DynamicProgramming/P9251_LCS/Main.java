package BOJ.DynamicProgramming.P9251_LCS;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str1 = br.readLine();
		String str2 = br.readLine();

		bw.write("" + solution(str1, str2));
		bw.flush();
		bw.close();
	}

	public static int solution(String str1, String str2) {
		int size1 = str1.length();
		int size2 = str2.length();
		char[] arr1 = new char[size1 + 1];
		char[] arr2 = new char[size2 + 1];

		for (int i = 1; i <= size1; i++) {
			arr1[i] = str1.charAt(i - 1);
		}

		for (int i = 1; i <= size2; i++) {
			arr2[i] = str2.charAt(i - 1);
		}

		int[][] dp = new int[size1 + 1][size2 + 1];

		for (int i = 1; i <= size1; i++) {
			for (int j = 1; j <= size2; j++) {
				if (arr1[i] == arr2[j]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		return dp[size1][size2];
	}
}
