package BOJ.Simulation.P14503_로봇청소기;

import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[][] board;
    static int count = 0;
    static int[] xMoves = {-1, 0, 1, 0};
    static int[] yMoves = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(x, y, direction);
        bw.write("" + count);
        bw.flush();
        bw.close();
    }

    public static void solution(int x, int y, int direction) {
        if(board[x][y] == 0){
            board[x][y] = 2;
            count++;
        }

        // 주변 4칸 중 청소되지 않은 빈 칸이 있는지 확인 후, 있으면 전진
        for (int i = 0; i < 4; i++){
            direction = direction - 1 < 0 ? 3 : direction - 1;
            int nx = x + xMoves[direction];
            int ny = y + yMoves[direction];
            if(board[nx][ny] == 0){
                solution(nx, ny, direction);
                return;
            }
        }

        // 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
        int nx = x - xMoves[direction];
        int ny = y - yMoves[direction];
        if(board[nx][ny] == 1){
            return;
        } else{
            solution(nx, ny, direction);
        }
    }
}
