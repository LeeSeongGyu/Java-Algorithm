package BOJ.ExhaustiveSearch.P15686_치킨배달;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m, min;
    static int[][] map;
    static List<int[]> house = new ArrayList<>(), store = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int k = Integer.parseInt(st.nextToken());
                if (k == 1) {
                    house.add(new int[]{i, j});
                } else if (k == 2) {
                    store.add(new int[]{i, j});
                }
            }
        }

        dfs(0, 0, new int[m][2]);
        bw.write("" + min);
        bw.flush();
        bw.close();
    }

    public static void dfs(int depth, int index, int[][] select) {
        if (depth == m) {
            min = Math.min(min, getDistance(select));
            return;
        }

        for (int i = index; i < store.size(); i++) {
            select[depth] = store.get(i);
            dfs(depth + 1, i + 1, select);
        }
    }

    public static int getDistance(int[][] select) {
        int distance = 0;

        for (int[] h : house) {
            int d = Integer.MAX_VALUE;
            for (int[] s : select) {
                d = Math.min(d, Math.abs(h[0] - s[0]) + Math.abs(h[1] - s[1]));
            }
            distance += d;
        }

        return distance;
    }
}

