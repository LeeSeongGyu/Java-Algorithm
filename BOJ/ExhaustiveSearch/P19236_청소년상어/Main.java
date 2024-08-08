package BOJ.ExhaustiveSearch.P19236_청소년상어;

import java.io.*;
import java.util.*;

public class Main {

	static int max_score = Integer.MIN_VALUE;
	static int[][] map;
	static Fish[] fishes;
	static int[] x_directions = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] y_directions = {0, 0, -1, -1, -1, 0, 1, 1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new int[4][4];
		fishes = new Fish[17];

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				Fish fish = new Fish(i, j, num, d, true);
				map[i][j] = num;
				fishes[num] = fish;
			}
		}

		solution();

		bw.write("" + max_score);
		bw.flush();
		bw.close();
	}

	public static void solution() {
		// (0, 0) 물고기 먹기
		Fish fish = fishes[map[0][0]];
		int sx = 0, sy = 0, sd = fish.d, sum = fish.num;
		fish.isAlive = false;

		// 탐색 시작
		bfs(sx, sy, sd, sum, map, fishes);
	}

	public static void bfs(int sx, int sy, int sd, int sum, int[][] map, Fish[] fishes) {
		max_score = Math.max(max_score, sum);

		// 물고기 이동
		moveFishes(sx, sy, map, fishes);

		// 상어 이동
		int nx = sx;
		int ny = sy;
		for (int i = 1; i <= 3; i++) {
			nx += x_directions[sd];
			ny += y_directions[sd];
			// 이동할 수 없다면
			if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || !fishes[map[nx][ny]].isAlive) {
				continue;
			}
			// copy
			int[][] copyMap = copyMap(map);
			Fish[] copyFishes = copyFishes(fishes);

			//이동
			Fish eat = copyFishes[copyMap[nx][ny]];
			eat.isAlive = false;
			bfs(nx, ny, eat.d, sum + eat.num, copyMap, copyFishes);
		}
	}

	public static void moveFishes(int sx, int sy, int[][] map, Fish[] fishes) {
		for (int i = 1; i < 17; i++) {
			Fish fish = fishes[i];
			if (fish.isAlive) {
				for (int j = 0; j < 8; j++) {
					int nx = fish.x + x_directions[fish.d];
					int ny = fish.y + y_directions[fish.d];
					// 이동 가능할 때
					if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && !(nx == sx && ny == sy)) {
						Fish target = fishes[map[nx][ny]];

						target.x = fish.x;
						target.y = fish.y;
						map[target.x][target.y] = target.num;

						fish.x = nx;
						fish.y = ny;
						map[nx][ny] = fish.num;

						break;
					}
					// 방향 45도 회전
					fish.d = getNextDirection(fish.d);
				}

			}
		}
	}

	public static int[][] copyMap(int[][] map){
		int[][] copy = new int[4][4];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				copy[i][j] = map[i][j];
			}
		}

		return copy;
	}

	public static Fish[] copyFishes(Fish[] Fishes){
		Fish[] copy = new Fish[17];

		for(int i = 1; i < 17; i++){
			Fish f = Fishes[i];
			Fish c = new Fish(f.x, f.y, f.num, f.d, f.isAlive);
			copy[i] = c;
		}

		return copy;
	}
	public static int getNextDirection(int d) {
		return d % 8 + 1;
	}

	public static class Fish {
		int x, y, num, d;
		boolean isAlive;

		public Fish(int x, int y, int num, int d, boolean isAlive) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.d = d;
			this.isAlive = isAlive;
		}
	}
}
