package 백준.완전탐색.P17090_미로탈출하기;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m, count = 0;
    static char[][] map;
    static int[][] visited, ch;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new int[n][m];
        ch = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(ch[i][j] == 0){
                    DFS(i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(ch[i][j] == 1){
                    count++;
                }
            }
        }

        bw.write("" + count);
        bw.flush();
        bw.close();
    }

    public static boolean DFS(int x, int y) {
        if(x >= n || x < 0 || y >= m || y < 0 || ch[x][y] == 1 ){
            return true;
        }
        if(ch[x][y] == -1 || visited[x][y] == 1){
            return false;
        }
        visited[x][y] = 1;

        int[] next = getNext(map[x][y], x, y);
        int nx = next[0];
        int ny = next[1];

        boolean flag = DFS(nx, ny);
        ch[x][y] = flag ? 1 : -1;

        return flag;
    }

    public static int[] getNext(char c, int x, int y) {
        switch (c) {
            case 'U':
                return new int[]{x - 1, y};
            case 'R':
                return new int[]{x, y + 1};
            case 'D':
                return new int[]{x + 1, y};
            case 'L':
                return new int[]{x, y - 1};
            default:
                return null;
        }
    }
}
