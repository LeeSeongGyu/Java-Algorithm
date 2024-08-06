package BOJ.Simulation.P23288_주사위굴리기2;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k, d, x, y;
    static int[][] map, scores, visited;
    static int[] dice = {1, 2, 3, 4, 5, 6};
    static final int EAST = 1, WEST = 2, NORTH = 3, SOUTH = 4;
    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = EAST;
        x = 1;
        y = 1;

        map = new int[n + 1][m + 1];
        visited = new int[n + 1][m + 1];
        scores = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution() {
        int score = 0;

        // 모든 위치 점수 계산 및 저장
        getScores();

        // k번 반복
        for (int i = 0; i < k; i++) {
            // 주사위 굴리기
            rollDice();

            // 점수 획득
            score += scores[x][y];

            // 이동 방향 결정
            if (dice[5] > map[x][y]) {
                d = clockwise(d);
            } else if (dice[5] < map[x][y]) {
                d = counterClockwise(d);
            }
        }

        return score;
    }

    public static void getScores() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                List<int[]> list = new ArrayList<>();
                if (visited[i][j] == 0) {
                    bfs(list, i, j);
                }
                int score = list.size() * map[i][j];
                for (int[] point : list) {
                    scores[point[0]][point[1]] = score;
                }
            }
        }
    }

    public static void bfs(List<int[]> list, int x, int y) {
        visited[x][y] = 1;
        list.add(new int[]{x, y});
        for (int i = 0; i < 4; i++) {
            int nx = x + xMoves[i];
            int ny = y + yMoves[i];
            if (nx > 0 && nx <= n && ny > 0 && ny <= m && visited[nx][ny] == 0
                && map[nx][ny] == map[x][y]) {
                bfs(list, nx, ny);
            }
        }
    }

    public static void rollDice() {
        switch (d) {
            case EAST:
                if (y + 1 <= m) {
                    roll(0, 3, 5, 2);
                    y++;
                } else {
                    d = WEST;
                    rollDice();
                }
                break;
            case SOUTH:
                if (x + 1 <= n) {
                    roll(0, 1, 5, 4);
                    x++;
                } else {
                    d = NORTH;
                    rollDice();
                }
                break;
            case WEST:
                if (y - 1 > 0) {
                    roll(0, 2, 5, 3);
                    y--;
                } else {
                    d = EAST;
                    rollDice();
                }
                break;
            case NORTH:
                if (x - 1 > 0) {
                    roll(0, 4, 5, 1);
                    x--;
                } else {
                    d = SOUTH;
                    rollDice();
                }
                break;
            default:
                break;
        }
    }

    public static void roll(int a, int b, int c, int d) {
        int tmp = dice[a];
        dice[a] = dice[b];
        dice[b] = dice[c];
        dice[c] = dice[d];
        dice[d] = tmp;
    }

    public static int clockwise(int d) {
        switch (d) {
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            case NORTH:
                return EAST;
            default:
                return d;
        }
    }

    public static int counterClockwise(int d) {
        switch (d) {
            case EAST:
                return NORTH;
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
            case NORTH:
                return WEST;
            default:
                return d;
        }
    }
}
