package 백준.다이나믹프로그래밍.P1520_내리막길;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[][] arr, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		dp = new int[n][m];

		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++){
				arr[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		bw.write("" + solution());
		bw.flush();
		bw.close();
	}

	public static int solution(){
		return 0;
	}
}
