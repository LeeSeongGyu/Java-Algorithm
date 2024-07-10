package BOJ.ExhaustiveSearch.P15683_감시;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m, min;
    static int[][] map;
    static List<int[]> cameras = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        min = Integer.MAX_VALUE;

        int[][] sight = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cameras.add(new int[]{i, j, map[i][j]});
                }
                if(map[i][j] == 6){
                    sight[i][j] = 6;
                }
            }
        }

        dfs(0, sight);

        bw.write("" + min);
        bw.flush();
        bw.close();
    }

    public static void dfs(int index, int[][] sight) {
        if (index == cameras.size()) {
            min = Math.min(min, getBlindSpot(sight));
            return;
        }

        int[] cam = cameras.get(index);
        switch (cam[2]) {
            case 1: {
                for (int i = 0; i < 4; i++) {
                    int[][] ns = clone(sight);
                    if (i == 0) {
                        up(cam, ns);
                    } else if (i == 1) {
                        down(cam, ns);
                    } else if (i == 2) {
                        left(cam, ns);
                    } else if (i == 3) {
                        right(cam, ns);
                    }
                    dfs(index + 1, ns);
                }
                break;
            }
            case 2: {
                for (int i = 0; i < 2; i++) {
                    int[][] ns = clone(sight);
                    if (i == 0) {
                        up(cam, ns);
                        down(cam, ns);
                    } else {
                        left(cam, ns);
                        right(cam, ns);
                    }
                    dfs(index + 1, ns);
                }
                break;
            }
            case 3: {
                for (int i = 0; i < 4; i++) {
                    int[][] ns = clone(sight);
                    if (i == 0) {
                        up(cam, ns);
                        left(cam, ns);
                    } else if (i == 1) {
                        left(cam, ns);
                        down(cam, ns);
                    } else if (i == 2) {
                        down(cam, ns);
                        right(cam, ns);
                    } else if (i == 3) {
                        right(cam, ns);
                        up(cam, ns);
                    }
                    dfs(index + 1, ns);
                }
                break;
            }
            case 4: {
                for (int i = 0; i < 4; i++) {
                    int[][] ns = clone(sight);
                    if (i == 0) {
                        up(cam, ns);
                        right(cam, ns);
                        down(cam, ns);
                    } else if (i == 1) {
                        right(cam, ns);
                        down(cam, ns);
                        left(cam, ns);
                    } else if (i == 2) {
                        down(cam, ns);
                        left(cam, ns);
                        up(cam, ns);
                    } else if (i == 3) {
                        left(cam, ns);
                        up(cam, ns);
                        right(cam, ns);
                    }
                    dfs(index + 1, ns);
                }
                break;
            }
            case 5: {
                int[][] ns = clone(sight);
                up(cam, ns);
                down(cam, ns);
                left(cam, ns);
                right(cam, ns);
                dfs(index + 1, ns);
                break;
            }
        }
    }

    public static int[][] clone(int[][] sight){
        int[][] ns = new int[n][m];
        for(int i = 0; i <n; i++){
            ns[i] = sight[i].clone();
        }
        return ns;
    }

    public static void left(int[] cam, int[][] sight){
        int x = cam[0];
        int y = cam[1];

        for(int j = y; j >= 0; j--){
            if(sight[x][j] == 6){
                break;
            }
            sight[x][j] = 1;
        }
    }

    public static void right(int[] cam, int[][] sight){
        int x = cam[0];
        int y = cam[1];

        for(int j = y; j < m; j++){
            if(sight[x][j] == 6){
                break;
            }
            sight[x][j] = 1;
        }
    }

    public static void up(int[] cam, int[][] sight){
        int x = cam[0];
        int y = cam[1];

        for(int i = x; i >= 0; i--){
            if(sight[i][y] == 6){
                break;
            }
            sight[i][y] = 1;
        }
    }

    public static void down(int[] cam, int[][] sight){
        int x = cam[0];
        int y = cam[1];

        for(int i = x; i < n; i++){
            if(sight[i][y] == 6){
                break;
            }
            sight[i][y] = 1;
        }
    }

    public static int getBlindSpot(int[][] sight){
        int count = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(sight[i][j] == 0){
                    count++;
                }
            }
        }

        return count;
    }
}