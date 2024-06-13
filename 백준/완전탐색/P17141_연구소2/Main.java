package 백준.완전탐색.P17141_연구소2;

import java.io.*;
import java.util.*;

public class Main {

	static int[] xMoves = {-1, 1, 0, 0};
	static int[] yMoves = {0, 0, 1, -1};
	static int n, m;
	static int min_time = Integer.MAX_VALUE;
	static int[][] map, copy;
	static List<int[]> candidates = new ArrayList<>();
	static int[] select;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		copy = new int[n][n];
		select = new int[m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					candidates.add(new int[]{i, j});
				}
			}
		}

		DFS(0, 0);
		if (min_time == Integer.MAX_VALUE) {
			bw.write("" + -1);
		} else {
			bw.write("" + min_time);
		}
		bw.flush();
		bw.close();
	}

	public static void DFS(int depth, int index) {
		if (depth == m) {
			initCopy();
			for (int i : select) {
				int[] point = candidates.get(i);
				copy[point[0]][point[1]] = 1;
			}
			getInfectionTime();
			return;
		}

		for (int i = index; i < candidates.size(); i++) {
			select[depth] = i;
			DFS(depth + 1, i + 1);
		}
	}

	public static void getInfectionTime() {
		int time = 1;

		while (true) {
			boolean change = false;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (copy[i][j] == time && infect(time, i, j)) {
						change = true;
					}
				}
			}
			if (!change) {
				break;
			}
			time++;
		}

		if (noBlank()) {
			min_time = Math.min(min_time, time - 1);
		}
	}

	public static boolean infect(int time, int x, int y) {
		boolean change = false;

		for (int i = 0; i < 4; i++) {
			int nx = x + xMoves[i];
			int ny = y + yMoves[i];
			if (nx >= 0 && nx < n && ny >= 0 && ny < n && copy[nx][ny] == 0) {
				copy[nx][ny] = time + 1;
				change = true;
			}
		}

		return change;
	}

	public static void initCopy() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1) {
					copy[i][j] = -1;
				} else {
					copy[i][j] = 0;
				}
			}
		}
	}

	public static boolean noBlank() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (copy[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}
