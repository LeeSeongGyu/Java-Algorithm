package BOJ.ExhaustiveSearch.P16956_늑대와양;

import java.io.*;
import java.util.*;

public class Main {

    static int r, c;
    static char[][] map, ch;
    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        ch = new char[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int answer = moveWolves();

        StringBuilder sb = new StringBuilder();
        sb.append(answer).append("\n");

        if (answer == 1) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void DFS(int x, int y) {
        for (int k = 0; k < 4; k++) {
            int nx = x + xMoves[k];
            int ny = y + yMoves[k];
            if (nx >= 0 && nx < r && ny >= 0 && ny < c && map[nx][ny] == 'S' && map[x][y] != 'W') {
                map[x][y] = 'D';
                return;
            }
        }

        for (int k = 0; k < 4; k++) {
            int nx = x + xMoves[k];
            int ny = y + yMoves[k];
            if (nx >= 0 && nx < r && ny >= 0 && ny < c && ch[nx][ny] == 0 && map[nx][ny] == '.') {
                ch[x][y] = 1;
                DFS(nx, ny);
            }
        }
    }

    public static int moveWolves() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'W') {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + xMoves[k];
                        int ny = j + yMoves[k];
                        if (nx >= 0 && nx < r && ny >= 0 && ny < c && map[nx][ny] == 'S') {
                            return 0;
                        }
                    }
                    ch[i][j] = 1;
                    DFS(i, j);
                }
            }
        }
        return 1;
    }
}
