package BOJ.ExhaustiveSearch.P17825_주사위윷놀이;

import java.io.*;
import java.util.*;

public class Main {

	static int[] arr = new int[10];
	static int max = Integer.MIN_VALUE;
	static boolean[] exist = new boolean[33];
	static int[] scores = {
		0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20,
		22, 24, 26, 28, 30, 32, 34, 36, 38, 40,
		13, 16, 19, 22, 24, 28, 27, 26, 25, 30,
		35, 0
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 10; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		bfs(0, 0, new int[]{0, 0, 0, 0});
		bw.write("" + max);
		bw.flush();
		bw.close();
	}

	public static void bfs(int depth, int score, int[] position) {
		if (depth == 10) {
			max = Math.max(max, score);
			return;
		}

		for (int i = 0; i < 4; i++) {
			// 말이 이미 도착지점에 있을 경우
			if (position[i] == 32) {
				bfs(depth + 1, score, position);
			}

			// 이동할 곳에 말이 있는 경우
			int destination = move(position[i], arr[depth]);
			if (destination != 32 && exist[destination]) {
				continue;
			}

			// 이동
			int tmp = position[i];
			exist[position[i]] = false;
			position[i] = destination;
			exist[destination] = true;

			//bfs
			bfs(depth + 1, score + scores[destination], position);

			// 이동 취소
			exist[tmp] = true;
			position[i] = tmp;
			exist[destination] = false;

		}
	}

	public static int move(int start, int m) {
		if (start == 5) {
			start = 21;
			m--;
		} else if (start == 10) {
			start = 24;
			m--;
		} else if (start == 15) {
			start = 26;
			m--;
		}

		for (int i = 0; i < m; i++) {
			if (start == 23 || start == 25) {
				start = 29;
			} else if (start == 31) {
				start = 20;
			} else if (start == 20 || start == 32) {
				start = 32;
			} else {
				start++;
			}
		}

		return start;
	}
}
