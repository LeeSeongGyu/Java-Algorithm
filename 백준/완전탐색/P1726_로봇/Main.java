package 백준.완전탐색.P1726_로봇;

import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static int[] start = new int[3], end = new int[3];
	static int[][] map;
	static int[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new int[n][m][4];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 3; i++) {
			start[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 3; i++) {
			end[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		bw.write("" + BFS());
		bw.flush();
		bw.close();
	}

	public static int BFS() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{start[0], start[1], start[2]});
		visited[start[0]][start[1]][start[2]] = 1;

		int time = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for(int i = 0; i < size; i++){
				int[] poll = queue.poll();

				if(Arrays.equals(poll, end)){
					return time;
				}

				for(int j = 0; j < 2; j++){
					int[] next = turn(poll, j);
					if(visited[next[0]][next[1]][next[2]] == 0){
						visited[next[0]][next[1]][next[2]] = 1;
						queue.offer(next);
					}
				}

				for(int k = 1; k < 4; k++){
					int[] next = go(poll, k);
					if(visited[next[0]][next[1]][next[2]] == 0){
						visited[next[0]][next[1]][next[2]] = 1;
						queue.offer(next);
					}
				}
			}

			time++;
		}

		return time;
	}

	public static int[] go(int[] status, int k) {
		int x = status[0];
		int y = status[1];
		int d = status[2];

		for(int i = 0; i < k; i++){
			int nx = x, ny = y;
			if(d == 0){
				ny++;
			} else if (d == 1){
				ny--;
			} else if( d == 2){
				nx++;
			} else {
				nx--;
			}
			if(nx >= n || ny >= m || nx < 0 || ny < 0 || map[nx][ny] == 1){
				break;
			}
			x = nx;
			y = ny;
		}

		return new int[]{x, y, d};
	}

	public static int[] turn(int[] status, int direction) {
		int d = status[2];

		if (direction == 0) { // left
			if (d == 0) d = 3;
			else if (d == 1) d = 2;
			else if (d == 2) d = 0;
			else d = 1;
		}

		if (direction == 1) { //right
			if (d == 0) d = 2;
			else if (d == 1) d = 3;
			else if (d == 2) d = 1;
			else d = 0;
		}

		int[] next = status.clone();
		next[2] = d;
		return next;
	}
}
