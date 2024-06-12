package 백준.완전탐색.P13460_구슬탈출2;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static char[][] board;
    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, 1, -1};
    static int[][][][] ch;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        ch = new int[n][m][n][m];

        int[] status = new int[5];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'R') {
                    status[0] = i;
                    status[1] = j;
                }
                if (board[i][j] == 'B') {
                    status[2] = i;
                    status[3] = j;
                }
            }
        }
        status[4] = 0;

        bw.write("" + BFS(status));

        bw.flush();
        bw.close();
    }

    public static int BFS(int[] status) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(status);
        ch[status[0]][status[1]][status[2]][status[3]] = 1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            if (poll[4] == 10) {
                return -1;
            }

            Loop1:
            for (int i = 0; i < 4; i++) {
                int rx = poll[0];
                int ry = poll[1];
                int bx = poll[2];
                int by = poll[3];
                boolean flag = false;
                while (true) {
                    int nrx = rx, nry = ry, nbx = bx, nby = by;
                    if (board[rx + xMoves[i]][ry + yMoves[i]] != '#') { // 빨간 구슬을 벽이 아니라면 기울인 방향으로 이동
                        rx += xMoves[i];
                        ry += yMoves[i];
                    }
                    if (board[bx + xMoves[i]][by + yMoves[i]] != '#') { // 파란 구슬을 벽이 아니라면 기울인 방향으로 이동
                        bx += xMoves[i];
                        by += yMoves[i];
                    }
                    if (flag == false && rx == bx && ry == by) { // 빨간 구슬과 파란 구슬이 같은 위치라면
                        rx = nrx;
                        ry = nry;
                        bx = nbx;
                        by = nby;
                    }
                    if (rx == nrx && ry == nry && bx == nbx && by == nby) { // 두 구슬의 위치가 변하지 않았다면 break;
                        break;
                    }
                    if (board[bx][by] == 'O') { // 파란 구슬이 구멍에 들어간 경우
                        continue Loop1;
                    }
                    if (board[rx][ry] == 'O') { // 빨간 구슬이 구멍에 들어간 경우
                        flag = true;
                    }
                }
                // 빨간 구슬이 구멍에 들어가고, 파란 구슬이 구멍에 들어가지 않았다면
                if (flag) {
                    return poll[4] + 1;
                }
                // 구슬의 위치가 처음 만난 상태라면
                if (ch[rx][ry][bx][by] == 0) {
                    ch[rx][ry][bx][by] = 1;
                    queue.offer(new int[]{rx, ry, bx, by, poll[4] + 1});
                }
            }
        }

        return -1;
    }
}
