package 백준.완전탐색.P2210_숫자판점프;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[5][5];
    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, 1, -1};
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0; i < 5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 5; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                DFS(0, 100_000, i, j);
            }
        }

        bw.write("" + set.size());
        bw.flush();
        bw.close();
    }

    public static void DFS(int number, int digit, int x, int y){
        number += board[x][y] * digit;

        if(digit == 1){
            set.add(number);
            return;
        }

        for(int i = 0; i < 4; i++){
            int nx = x + xMoves[i];
            int ny = y + yMoves[i];
            if(nx >= 0 && ny >= 0 && nx < 5 && ny < 5){
                DFS(number, digit / 10, nx, ny);
            }
        }
    }
}
