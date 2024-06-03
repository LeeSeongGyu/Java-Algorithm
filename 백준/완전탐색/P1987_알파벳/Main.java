package 백준.완전탐색.P1987_알파벳;

import java.io.*;
import java.util.*;

public class Main {

    static int r, c, max;
    static char[][] board, ch;
    static Set<Character> set = new HashSet<>();
    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.valueOf(st.nextToken());
        c = Integer.valueOf(st.nextToken());
        board = new char[r][c];
        ch = new char[r][c];
        max = Integer.MIN_VALUE;

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        DFS(0, 0, 0);
        bw.write("" + max);

        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(int x, int y, int count) {
        if (x < 0 || x >= r || y < 0 || y >= c || ch[x][y] == 1 || set.contains(board[x][y])) {
            max = Math.max(max, count);
            return;
        }

        ch[x][y] = 1;
        set.add(board[x][y]);

        for (int i = 0; i < 4; i++) {
            DFS(x + xMoves[i], y + yMoves[i], count + 1);
        }

        ch[x][y] = 0;
        set.remove(board[x][y]);
    }
}
