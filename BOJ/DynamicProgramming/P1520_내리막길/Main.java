package BOJ.DynamicProgramming.P1520_내리막길;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] dp, arr;
    static int[] xMoves = {1, -1, 0, 0};
    static int[] yMoves = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new int[n][m];
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int[] d : dp){
            Arrays.fill(d, -1);
        }

        bw.write("" + dfs(0, 0));
        bw.flush();
        bw.close();
    }

    public static int dfs(int x, int y) {
        if(x == n-1 && y == m-1){
            return 1;
        }

        if(dp[x][y] != -1){
            return dp[x][y];
        }

        int sum = 0;
        for(int i = 0; i < 4; i++){
            int nx = x + xMoves[i];
            int ny = y + yMoves[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m || arr[nx][ny] >= arr[x][y]){
                continue;
            }
            sum += dfs(nx, ny);
        }
        dp[x][y] = sum;

        return sum;
    }
}
