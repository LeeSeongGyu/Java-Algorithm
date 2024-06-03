package 백준.시뮬레이션.P16234_인구이동;

import java.io.*;
import java.util.*;

public class Main {

    static int n, l, p;
    static int[][] board, ch;
    static int count = 0;
    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        ch = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solution();
        bw.write("" + count);
        bw.flush();
        bw.close();
    }

    public static void solution() {
        while(true){
            for (int i = 0; i < n; i++){
                Arrays.fill(ch[i], 0);
            }
            boolean isMoved = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (ch[i][j] == 0) {
                        List<Nation> union = new ArrayList<>();
                        joinUnion(union, i, j);
                        if(union.size() >= 2){
                            move(union);
                            isMoved = true;
                        }
                    }
                }
            }
            if(isMoved){
                count++;
            } else {
                break;
            }
        }
    }

    public static void joinUnion(List<Nation> union, int x, int y) {
        union.add(new Nation(x, y, board[x][y]));
        ch[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + xMoves[i];
            int ny = y + yMoves[i];
            if(inBoard(nx, ny) && ch[nx][ny] == 0 && isOpen(x, y, nx, ny)){
                joinUnion(union, nx, ny);
            }
        }
    }

    public static void move(List<Nation> union){
        int sum = 0;
        for(Nation n : union){
            sum += n.value;
        }
        int div = sum / union.size();
        for(Nation n : union){
            board[n.x][n.y] = div;
        }
    }

    public static boolean isOpen(int first_x, int first_y, int second_x, int second_y) {
        int diff = Math.abs(board[first_x][first_y] - board[second_x][second_y]);
        if (diff >= l && diff <= p) {
            return true;
        }
        return false;
    }

    public static boolean inBoard(int x, int y) {
        if (x >= n || y >= n || x < 0 || y < 0) {
            return false;
        }
        return true;
    }

    public static class Nation{
        int x;
        int y;
        int value;

        public Nation(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value =value;
        }
    }
}
