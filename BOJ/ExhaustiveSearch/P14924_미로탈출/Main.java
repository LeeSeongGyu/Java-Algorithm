package BOJ.ExhaustiveSearch.P14924_미로탈출;

import java.util.*;
import java.io.*;

public class Main {

	static int[][] map;
	static int[][][] visited;
	static int n, m, hx, hy, ex, ey;
	static int[] xMoves = {-1, 1, 0, 0};
	static int[] yMoves = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new int[n][m][2];

		st = new StringTokenizer(br.readLine(), " ");
		hx = Integer.parseInt(st.nextToken()) - 1;
		hy = Integer.parseInt(st.nextToken()) - 1;

		st = new StringTokenizer(br.readLine(), " ");
		ex = Integer.parseInt(st.nextToken()) - 1;
		ey = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bw.write("" + BFS());
		bw.flush();
		bw.close();
	}

	public static int BFS() {
		int min = Integer.MAX_VALUE;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{hx, hy, 0, 0});
		visited[hx][hy][0] = 1;

		while (!queue.isEmpty()) {
			int[] poll = queue.poll();

			if (poll[0] == ex && poll[1] == ey) {
				return poll[2];
			}

			for (int j = 0; j < 4; j++) {
				int nx = poll[0] + xMoves[j];
				int ny = poll[1] + yMoves[j];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
					continue;
				}

				// 벽을 부순적이 없고, 다음 칸이 빈 공간인 경우
				if (poll[3] == 0 && map[nx][ny] == 0 && visited[nx][ny][0] == 0){
					visited[nx][ny][0] = 1;
					queue.offer(new int[]{nx, ny, poll[2] + 1, 0});
					continue;
				}

				// 벽을 부순적이 없고, 다음 칸이 벽인 경우
				if(poll[3] == 0 && map[nx][ny] == 1 && visited[nx][ny][1] == 0){
					visited[nx][ny][1] = 1;
					queue.offer(new int[]{nx, ny, poll[2] + 1, 1});
					continue;
				}

				// 벽을 부순 적이 있고, 다음 칸이 빈 공간인 경우
				if(poll[3] == 1 && map[nx][ny] == 0 && visited[nx][ny][1] == 0){
					visited[nx][ny][1] = 1;
					queue.offer(new int[]{nx, ny, poll[2] + 1, 1});
					continue;
				}
			}
		}

		return -1;
	}
}
