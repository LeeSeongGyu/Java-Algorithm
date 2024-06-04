package 백준.시뮬레이션.P16918_봄버맨;

import java.io.*;
import java.util.*;

public class Main {

    static int r, c, n;
    static char[][] board;
    static int[][] bombTime;
    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        bombTime = new int[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'O') {
                    bombTime[i][j] += 3;
                }
            }
        }

        solution();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }

        bw.write("" + sb);
        bw.flush();
        bw.close();
    }

    public static void solution() {
        int time = 1;
        while (++time <= n) {
            if(time % 2 == 0){ // 짝수일 경우
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (board[i][j] == '.') {
                            board[i][j] = 'O';
                            bombTime[i][j] = time + 3;
                        }
                    }
                }
            } else { // 홀수일 경우
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if(bombTime[i][j] == time){
                            explode(i, j, time);
                        }
                    }
                }
            }
        }
    }

    public static void explode(int x, int y, int time){
        board[x][y] = '.';
        bombTime[x][y] = 0;
        for (int k = 0; k < 4; k++) {
            int nx = x + xMoves[k];
            int ny = y + yMoves[k];
            if (nx >= 0 && ny >= 0 && nx < r && ny < c && bombTime[nx][ny] != time) {
                if(board[nx][ny] == 'O'){
                    bombTime[nx][ny] = 0;
                    board[nx][ny] = '.';
                }
            }
        }
    }
}
