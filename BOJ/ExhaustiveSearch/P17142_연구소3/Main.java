package BOJ.ExhaustiveSearch.P17142_연구소3;

import java.io.*;
import java.util.*;

public class Main {

    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, 1, -1};
    static int n, m;
    static int min_time = Integer.MAX_VALUE;
    static int[][] map, copy, visited;
    static List<int[]> candidates = new ArrayList<>();
    static int[] select;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        copy = new int[n][n];
        visited = new int[n][n];
        select = new int[m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    candidates.add(new int[]{i, j});
                }
            }
        }

        DFS(0, 0);
        if (min_time == Integer.MAX_VALUE) {
            bw.write("" + -1);
        } else {
            bw.write("" + min_time);
        }
        bw.flush();
        bw.close();
    }

    public static void DFS(int depth, int index) {
        if (depth == m) {
            BFS();
            return;
        }

        for (int i = index; i < candidates.size(); i++) {
            select[depth] = i;
            DFS(depth + 1, i + 1);
        }
    }

    public static void BFS() {
        initCopy();
        initVisited();

        Queue<int[]> queue = new LinkedList<>();

        for (int i : select) {
            int[] point = candidates.get(i);
            copy[point[0]][point[1]] = 0;
            visited[point[0]][point[1]] = 1;
            queue.offer(point);
        }

        int time = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = poll[0] + xMoves[j];
                    int ny = poll[1] + yMoves[j];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && visited[nx][ny] == 0
                        && copy[nx][ny] != -2) {
                        queue.offer(new int[]{nx, ny});
                        copy[nx][ny] = time;
                        visited[nx][ny] = 1;
                    }
                }
            }

            time++;
        }

        if (noBlank()) {
            min_time = Math.min(min_time, getMaxTime());
        }
    }

    public static void initCopy() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    copy[i][j] = -2;
                } else if (map[i][j] == 2) {
                    copy[i][j] = -3;
                } else {
                    copy[i][j] = -1;
                }
            }
        }
    }

    public static void initVisited() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], 0);
        }
    }

    public static boolean noBlank() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int getMaxTime() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 2 && map[i][j] != 1) {
                    max = Math.max(max, copy[i][j]);
                }
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
