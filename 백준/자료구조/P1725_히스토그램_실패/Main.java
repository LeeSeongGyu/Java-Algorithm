package 백준.자료구조.P1725_히스토그램_실패;

import java.io.*;

public class Main {

	static int n;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		arr = new int[n+2];

		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		bw.write("" + solution());
		bw.flush();
		bw.close();
	}

	public static int solution() {
		int max = Integer.MIN_VALUE;
		return max;
	}
}
