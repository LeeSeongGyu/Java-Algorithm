package 백준.P2493_탑;

import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.valueOf(st.nextToken());
		}

		bw.write(solution());
		bw.flush();
		bw.close();
	}

	public static String solution(){
		int[] answer = new int[n+1];
		Stack<Integer> stack = new Stack<>();
		int index = 0;
		int height = Integer.MAX_VALUE;
		for(int i = 1; i <= n; i++){
			if(stack.isEmpty() || stack.peek() < arr[i]){
				answer[i] = index;
				if(arr[i] > height){
					answer[i] = 0;
					height = arr[i];
					index = i;
				}
			} else {
				answer[i] = i - 1;
				index = i - 1;
				height = arr[i];
				stack.clear();
			}
			stack.push(arr[i]);
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++){
			sb.append(answer[i]).append(" ");
		}
		return sb.toString();
	}
}
