package BOJ.ExhaustiveSearch.P4991_로봇청소기;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m, sx, sy, min;
    static char[][] map;
    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, 1, -1};
    static int[][][][] distance;
    static int[] visited, selected;
    static List<int[]> dirt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            min = Integer.MAX_VALUE;
            map = new char[n][m];
            distance = new int[n][m][n][m];

            if (m == 0 && n == 0) {
                break;
            }

            dirt = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == 'o') {
                        sx = i;
                        sy = j;
                        map[i][j] = '.';
                    } else if (map[i][j] == '*') {
                        dirt.add(new int[]{i, j});
                        map[i][j] = '.';
                    }
                }
            }

            visited = new int[dirt.size()];
            selected = new int[dirt.size()];

            saveDistance();
            DFS(0);

            if (min == Integer.MAX_VALUE) {
                bw.write(-1 + "\n");
            } else {
                bw.write(min + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    public static void DFS(int depth){
        if(depth == dirt.size()){
            int[] p = dirt.get(selected[0]);
            int sum = distance[sx][sy][p[0]][p[1]];
            for(int i = 0; i < dirt.size() - 1; i++){
                int[] s = dirt.get(selected[i]);
                int[] e = dirt.get(selected[i + 1]);
                int d = distance[s[0]][s[1]][e[0]][e[1]];
                if(d == -1){
                    return;
                } else {
                    sum += d;
                }
            }
            min = Math.min(min, sum);
            return;
        }

        for(int i = 0; i < dirt.size(); i++){
            if(visited[i] == 0){
                visited[i] = 1;
                selected[depth] = i;
                DFS(depth + 1);
                visited[i] = 0;
            }
        }
    }

    public static void saveDistance(){
        int[] start = new int[]{sx, sy};
        for(int[] d : dirt){
            distance[sx][sy][d[0]][d[1]] = getDistance(start, d);
        }
        getDistanceBetweenDirt(new int[2], 0, 0);
    }

    public static void getDistanceBetweenDirt(int[] arr, int depth, int index){
        if(depth == 2){
            int[] start = dirt.get(arr[0]);
            int[] end = dirt.get(arr[1]);
            int d = getDistance(start, end);
            distance[end[0]][end[1]][start[0]][start[1]] = d;
            distance[start[0]][start[1]][end[0]][end[1]] = d;
            return;
        }

        for(int i = index; i < dirt.size(); i++){
            arr[depth] = i;
            getDistanceBetweenDirt(arr, depth + 1, i + 1);
        }
    }

    public static int getDistance(int[] start, int[] end) {
        int[][] ch = new int[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1]});
        ch[start[0]][start[1]] = 1;

        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();

                if (poll[0] == end[0] && poll[1] == end[1]) {
                    return time;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = poll[0] + xMoves[j];
                    int ny = poll[1] + yMoves[j];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && ch[nx][ny] == 0 && map[nx][ny] == '.') {
                        queue.offer(new int[]{nx, ny});
                        ch[nx][ny] = 1;
                    }
                }
            }

            time++;
        }

        return -1;
    }
}
