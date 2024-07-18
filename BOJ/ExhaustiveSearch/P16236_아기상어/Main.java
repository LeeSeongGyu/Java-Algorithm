package BOJ.ExhaustiveSearch.P16236_아기상어;

import java.io.*;
import java.util.*;

public class Main {

	static int n, cx, cy, cs, eat;
	static int[][] map;
	static int[] xMoves = {-1, 0, 1, 0};
	static int[] yMoves = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9){
					cx = i;
					cy = j;
					cs = 2;
					eat = 0;
					map[i][j] = 0;
				}
			}
		}

		bw.write("" + solution());
		bw.flush();
		bw.close();
	}

	public static int solution(){
		int time = 0;

		while (true){
			// 먹을 수 있는 물고기 중 가장 가까운 곳 찾기
			int min_distance = Integer.MAX_VALUE;
			int[] min_point = new int[2];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(map[i][j] != 0 && map[i][j] < cs){
						int d = getDistance(i, j);
						if(d != -1 && d < min_distance){
							min_point = new int[]{i, j};
							min_distance = d;
						}
					}
				}
			}

			// 없으면 종료
			if(min_distance == Integer.MAX_VALUE){
				return time;
			}

			// 이동
			cx = min_point[0];
			cy = min_point[1];
			time += min_distance;

			// 먹기
			eat++;
			map[cx][cy] = 0;
			if(eat == cs){
				eat = 0;
				cs++;
			}
		}
	}

	public static int getDistance(int x, int y){
		int[][] visited = new int[n][n];
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{cx, cy});

		int distance = 0;
		while(!queue.isEmpty()){
			int size = queue.size();

			for(int i = 0; i < size; i++){
				int[] poll = queue.poll();

				if(poll[0] == x && poll[1] == y){
					return distance;
				}

				for(int m = 0; m < 4; m++){
					int nx = poll[0] + xMoves[m];
					int ny = poll[1] + yMoves[m];
					if(nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] > cs || visited[nx][ny] == 1){
						continue;
					}
					visited[nx][ny] = 1;
					queue.offer(new int[]{nx, ny});
				}
			}

			distance++;
		}

		return -1;
	}
}
