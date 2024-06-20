package 백준.그리디.P11000_강의실배정;

import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[][] lecture;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		lecture = new int[n][2];

		for(int i = 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			lecture[i][0] = Integer.parseInt(st.nextToken());
			lecture[i][1] = Integer.parseInt(st.nextToken());
		}

		bw.write("" + solution());
		bw.flush();
		bw.close();
	}

	public static int solution(){
		Arrays.sort(lecture, (o1, o2) -> {
			if(o1[0] == o2[0]){
				return o1[1] - o2[1];
			}
			return o1[0] - o2[0];
		});

		PriorityQueue<Integer> queue = new PriorityQueue<>();
		queue.offer(0);

		for(int[] l : lecture){
			if(queue.peek() <= l[0]) {
				queue.poll();
			}
			queue.offer(l[1]);
		}

		return queue.size();
	}
}
