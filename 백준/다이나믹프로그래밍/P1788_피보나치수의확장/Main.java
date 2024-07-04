package 백준.다이나믹프로그래밍.P1788_피보나치수의확장;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int answer = solution(n);

		if(answer == 0){
			bw.write(0 + "\n");
		} else if(answer > 0){
			bw.write(1 + "\n");
		} else {
			bw.write(-1 + "\n");
		}

		bw.write("" + Math.abs(answer));
		bw.flush();
		bw.close();
	}

	public static int solution(int n){
		if(n == 0){
			return 0;
		}

		int size = Math.abs(n);
		int[] dp = new int[size + 1];
		dp[0] = 0;
		dp[1] = 1;

		for(int i = 2; i <= size; i++){
			dp[i] = (dp[i-1] + dp[i-2]) % 1_000_000_000;
		}

		if(n < 0 && n % 2 == 0){
			dp[size] = -dp[size];
		}

		return dp[size];
	}
}
