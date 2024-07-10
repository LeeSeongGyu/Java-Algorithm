package BOJ.ExhaustiveSearch.P14502_연구소;

import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int[][] map, copy, visited;
	static int[] xMoves = {-1, 1, 0, 0};
	static int[] yMoves = {0, 0, 1, -1};
	static int max = Integer.MIN_VALUE;
	static int[] wall = new int[3];
	static List<int[]> zeros = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new int[n][m];
		copy = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					zeros.add(new int[]{i, j});
				}
			}
		}

		DFS(0, -1);
		bw.write("" + max);
		bw.flush();
		bw.close();
	}

	public static void DFS(int depth, int index) {
		// 벽 3개를 골랐다면: 감염 진행 후, 안전 지역 계산
		if (depth == 3) {
			initCopy();
			for (int i : wall) {
				int[] point = zeros.get(i);
				copy[point[0]][point[1]] = 1;
			}
			getSafeArea();
			return;
		}

		for (int i = index + 1; i < zeros.size(); i++) {
			wall[depth] = i;
			DFS(depth + 1, i);
		}
	}

	public static void getSafeArea() {
		initVisited();
		// 감염 진행
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (copy[i][j] == 2 && visited[i][j] == 0) {
					infect(i, j);
				}
			}
		}
		// 안전 지역 계산
		int safe = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (copy[i][j] == 0) {
					safe++;
				}
			}
		}
		// 최대값 갱신
		max = Math.max(max, safe);
	}

	public static void infect(int x, int y) {
		visited[x][y] = 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + xMoves[i];
			int ny = y + yMoves[i];
			if (nx >= 0 && nx < n && ny >= 0 && ny < m && visited[nx][ny] == 0
				&& copy[nx][ny] == 0) {
				copy[nx][ny] = 2;
				infect(nx, ny);
			}
		}
	}

	public static void initVisited() {
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], 0);
		}
	}

	public static void initCopy() {
		for (int i = 0; i < map.length; i++) {
			copy[i] = map[i].clone();
		}
	}
}
