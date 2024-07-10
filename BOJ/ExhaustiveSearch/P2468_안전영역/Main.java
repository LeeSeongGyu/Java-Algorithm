package BOJ.ExhaustiveSearch.P2468_안전영역;

import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] map, ch;
    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, 1, -1};
    static int max_h = Integer.MIN_VALUE;
    static int min_h = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        ch = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max_h = Math.max(max_h, map[i][j]);
                min_h = Math.min(min_h, map[i][j]);
            }
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution() {
        int max = Integer.MIN_VALUE;

        for (int h = 0; h < max_h; h++) {
            int count = 0;
            initCh();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] > h && ch[i][j] == 0) {
                        DFS(h, i, j);
                        count++;
                    }
                }
            }
            max = Math.max(max, count);
        }

        return max;
    }

    public static void initCh() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(ch[i], 0);
        }
    }

    public static void DFS(int h, int x, int y) {
        ch[x][y] = 1;
        for (int k = 0; k < 4; k++) {
            int nx = x + xMoves[k];
            int ny = y + yMoves[k];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && ch[nx][ny] == 0 && map[nx][ny] > h) {
                DFS(h, nx, ny);
            }
        }
    }
}
