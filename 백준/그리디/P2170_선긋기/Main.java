package 백준.그리디.P2170_선긋기;

import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static Line[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		arr = new Line[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			arr[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		bw.write("" + solution());
		bw.flush();
		bw.close();
	}

	public static int solution() {
		Arrays.sort(arr);
		PriorityQueue<Line> queue = new PriorityQueue<>(Collections.reverseOrder());

		for (Line l : arr) {
			if (queue.isEmpty()) {
				queue.offer(l);
				continue;
			}

			Line peek = queue.peek();
			if (queue.peek().end < l.start) {
				queue.offer(l);
			} else {
				peek.end = Math.max(peek.end, l.end);
			}
		}

		int length = 0;
		for (Line l : queue) {
			length += l.end - l.start;
		}

		return length;
	}

	public static class Line implements Comparable<Line> {

		int start, end;

		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Line l) {
			if (this.start == l.start) {
				return this.end - this.start;
			}
			return this.start - l.start;
		}
	}
}
