package 백준.P2580_스도쿠;

import java.io.*;
import java.util.*;

public class Main {

    static int[][] board = new int[9][9];
    static List<ZeroPoint> zeros = new ArrayList<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.valueOf(st.nextToken());
                if (board[i][j] == 0) {
                    zeros.add(new ZeroPoint(i, j));
                }
            }
        }

        br.close();
        DFS(0);
    }

    public static void DFS(int index) throws IOException {
        if (index >= zeros.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j]);
                    sb.append(" ");
                }
                sb.append("\n");
            }

            bw.write(sb.toString());
            bw.flush();
            bw.close();

            System.exit(0);
        }
        ZeroPoint zp = zeros.get(index);
        for (int pd : findPossibleDigits(zp.x, zp.y)) {
            board[zp.x][zp.y] = pd;
            DFS(index + 1);
            board[zp.x][zp.y] = 0;
        }

    }

    public static List<Integer> findPossibleDigits(int x, int y) {
        int[] ch = new int[10];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            ch[board[x][i]] = 1;
            ch[board[i][y]] = 1;
        }

        for (int i = x / 3 * 3; i < x / 3 * 3 + 3; i++) {
            for (int j = y / 3 * 3; j < y / 3 * 3 + 3; j++) {
                ch[board[i][j]] = 1;
            }
        }

        for (int i = 1; i < 10; i++) {
            if (ch[i] == 0) {
                list.add(i);
            }
        }

        return list;
    }

    public static class ZeroPoint {

        int x;
        int y;

        public ZeroPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
