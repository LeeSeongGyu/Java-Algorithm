package 백준.완전탐색.P6593_상범빌딩;

import java.io.*;
import java.util.*;

public class Main {

    static int l, r, c;
    static char[][][] map;
    static int[][][] ch;

    static int[] zMoves = {0, 0, 0, 0, 1, -1};
    static int[] xMoves = {-1, 1, 0, 0, 0, 0};
    static int[] yMoves = {0, 0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if (l == 0 && r == 0 && c == 0) {
                break;
            }

            map = new char[l][r][c];
            ch = new int[l][r][c];

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    String str = br.readLine();
                    for (int k = 0; k < c; k++) {
                        map[i][j][k] = str.charAt(k);
                    }
                }
                br.readLine();
            }
            bw.write(solution() + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static String solution() {
        Loop1:
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    if (map[i][j][k] == 'S') {
                        int time = BFS(i, j, k);
                        if (time > 0) {
                            return "Escaped in " + time + " minute(s).";
                        }
                        break Loop1;
                    }
                }
            }
        }
        return "Trapped!";
    }

    public static int BFS(int z, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{z, x, y, 0});
        ch[z][x][y] = 1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            if(map[poll[0]][poll[1]][poll[2]] == 'E'){
                return poll[3];
            }

            for (int i = 0; i < 6; i++) {
                int nz = poll[0] + zMoves[i];
                int nx = poll[1] + xMoves[i];
                int ny = poll[2] + yMoves[i];
                if (nz >= 0 && nx >= 0 && ny >= 0 && nz < l && nx < r && ny < c
                    && ch[nz][nx][ny] == 0 && map[nz][nx][ny] != '#') {
                    ch[nz][nx][ny] = 1;
                    queue.offer(new int[]{nz, nx, ny, poll[3] + 1});
                }
            }
        }

        return 0;
    }
}
