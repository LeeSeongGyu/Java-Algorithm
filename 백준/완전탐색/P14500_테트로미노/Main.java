package 백준.완전탐색.P14500_테트로미노;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m, max;
    static int[][] board, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new int[n][m];
        max = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                check(i, j);
                dfs(i, j, 0, board[i][j]);
            }
        }

        bw.write("" + max);
        bw.flush();
        bw.close();
    }

    public static void check(int x, int y) {
        // ㅗ
        if (y - 1 >= 0 && y + 1 < m && x - 1 >= 0) {
            max = Math.max(max, board[x][y - 1] + board[x][y + 1] + board[x - 1][y] + board[x][y]);
        }
        // ㅜ
        if (y - 1 >= 0 && y + 1 < m && x + 1 < n) {
            max = Math.max(max, board[x][y - 1] + board[x][y + 1] + board[x + 1][y] + board[x][y]);
        }
        // ㅓ
        if (x - 1 >= 0 && x + 1 < n && y - 1 >= 0) {
            max = Math.max(max, board[x - 1][y] + board[x + 1][y] + board[x][y - 1] + board[x][y]);
        }
        // ㅏ
        if (x - 1 >= 0 && x + 1 < n && y + 1 < m) {
            max = Math.max(max, board[x - 1][y] + board[x + 1][y] + board[x][y + 1] + board[x][y]);
        }
    }

    public static void dfs(int x, int y, int move, int sum) {
        if (move == 3) {
            max = Math.max(max, sum);
            return;
        }

        visited[x][y] = 1;

        if (x + 1 < n && visited[x+1][y] == 0) {
            dfs(x + 1, y, move + 1, sum + board[x + 1][y]);
        }

        if (x - 1 >= 0 && visited[x-1][y] == 0) {
            dfs(x - 1, y, move + 1, sum + board[x - 1][y]);
        }

        if (y + 1 < m && visited[x][y + 1] == 0) {
            dfs(x, y + 1, move + 1, sum + board[x][y + 1]);
        }

        visited[x][y] = 0;
    }
}
