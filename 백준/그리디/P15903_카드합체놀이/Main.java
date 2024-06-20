package 백준.그리디.P15903_카드합체놀이;

import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static PriorityQueue<Long> queue = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++){
			queue.offer(Long.parseLong(st.nextToken()));
		}

		bw.write("" + solution());
		bw.flush();
		bw.close();
	}

	public static long solution(){
		for(int i = 0; i < m; i++){
			Long sum = queue.poll() + queue.poll();
			queue.offer(sum);
			queue.offer(sum);
		}

		long score = 0;
		while(!queue.isEmpty()){
			score += queue.poll();
		}
		return score;
	}
}
