package 백준.다이나믹프로그래밍.P1932_정수삼각형;

import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j <= i; j++){
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bw.write("" + solution());
		bw.flush();
		bw.close();
	}

	public static int solution(){
		for(int i = 1; i < n; i++){
			arr[i][0] += arr[i-1][0];
			for(int j = 1; j <= i; j++){
				arr[i][j] += Math.max(arr[i - 1][j], arr[i - 1][j - 1]);
			}
		}

		return Arrays.stream(arr[n-1]).max().getAsInt();
	}
}
