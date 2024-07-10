package BOJ.ExhaustiveSearch.P3055_탈출;

import java.io.*;
import java.util.*;

public class Main {

    static int r, c;
    static char[][] map;
    static int[][] visited;
    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new int[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        bw.write(BFS());
        bw.flush();
        bw.close();
    }

    public static String BFS() {
        Queue<int[]> water_queue = new LinkedList<>();
        Queue<int[]> squirrel_queue = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == '*') {
                    water_queue.offer(new int[]{i, j});
                }
                if(map[i][j] == 'S'){
                    squirrel_queue.offer(new int[]{i, j});
                    visited[i][j] = 1;
                    map[i][j] = '.';
                }
            }
        }

        int time = 1;

        while (!squirrel_queue.isEmpty()) {
            // 물 이동
            int size = water_queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = water_queue.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = poll[0] + xMoves[j];
                    int ny = poll[1] + yMoves[j];
                    if (nx >= 0 && nx < r && ny >= 0 && ny < c && map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        water_queue.offer(new int[]{nx, ny});
                    }
                }
            }

            // 다람쥐 이동
            size = squirrel_queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = squirrel_queue.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = poll[0] + xMoves[j];
                    int ny = poll[1] + yMoves[j];
                    if(nx < 0 || nx >= r || ny < 0 || ny >= c || visited[nx][ny] == 1){
                        continue;
                    }
                    if(map[nx][ny] == 'D'){
                        return String.valueOf(time);
                    }
                    if (map[nx][ny] == '.') {
                        squirrel_queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = 1;
                    }
                }
            }

            time++;
        }

        return "KAKTUS";
    }
}
