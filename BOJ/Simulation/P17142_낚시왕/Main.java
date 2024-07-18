package BOJ.Simulation.P17142_낚시왕;

import java.io.*;
import java.util.*;

public class Main {

	static int r, c, m, count;
	static int[][][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		count = 0;
		map = new int[r][c][3];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			for (int j = 0; j < 3; j++) {
				map[x][y][j] = Integer.parseInt(st.nextToken());
			}
		}

		solution();
		bw.write("" + count);
		bw.flush();
		bw.close();
	}

	public static void solution() {
		for (int j = 0; j < c; j++) {
			for (int i = 0; i < r; i++) {
				if (map[i][j][2] != 0) {
					count += map[i][j][2];
					for(int k =0; k < 3; k++){
						map[i][j][k] = 0;
					}
					break;
				}
			}
			moveSharks();
		}
	}

	public static void moveSharks() {
		int[][][] newMap = new int[r][c][3];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j][2] != 0) {
					moveShark(i, j, map[i][j], newMap);
				}
			}
		}

		map = newMap;
	}

	public static void moveShark(int x, int y, int[] info, int[][][] newMap) {
		int s = info[0];
		int d = info[1];
		int z = info[2];

		for (int i = 0; i < s; i++) {
			if (d == 1) {
				if (x - 1 < 0) {
					x++;
					d = 2;
				} else {
					x--;
				}
			} else if (d == 2) {
				if (x + 1 >= r) {
					x--;
					d = 1;
				} else {
					x++;
				}
			} else if (d == 3) {
				if (y + 1 >= c) {
					y--;
					d = 4;
				} else {
					y++;
				}
			} else if (d == 4) {
				if (y - 1 < 0) {
					y++;
					d = 3;
				} else {
					y--;
				}
			}
		}

		if (newMap[x][y][2] < z) {
			newMap[x][y] = new int[]{s, d, z};
		}
	}
}
