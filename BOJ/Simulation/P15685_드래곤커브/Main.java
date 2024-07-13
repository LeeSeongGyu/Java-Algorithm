package BOJ.Simulation.P15685_드래곤커브;

import java.io.*;
import java.util.*;

public class Main {

    static int n, x, y, d, g;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        visited = new int[101][101];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            List<Integer> list = new ArrayList<>();
            list.add(d);
            solution(x, y, list, 0);
        }

        bw.write("" + count());
        bw.flush();
        bw.close();
    }

    public static int count() {
        int count = 0;

        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                if (visited[i][j] == 1 && visited[i + 1][j] == 1 && visited[i][j + 1] == 1 && visited[i + 1][j + 1] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void solution(int x, int y, List<Integer> moves, int gen) {
        if(gen == 0){
            visited[x][y] = 1;
            if (moves.get(0) == 0) {
                visited[++x][y] = 1;
            } else if (moves.get(0) == 1) {
                visited[x][--y] = 1;
            } else if (moves.get(0) == 2) {
                visited[--x][y] = 1;
            } else {
                visited[x][++y] = 1;
            }
            solution(x, y, moves, gen + 1);
            return;
        }

        if(gen > g){
            return;
        }

        List<Integer> reverse_moves = new ArrayList<>(moves);
        Collections.reverse(reverse_moves);
        List<Integer> new_moves = new ArrayList<>();
        for (int i : reverse_moves) {
            new_moves.add(nextDirection(i));
        }
        moves.addAll(new_moves);

        for (int m : new_moves) {
            if (m == 0) {
                visited[++x][y] = 1;
            } else if (m == 1) {
                visited[x][--y] = 1;
            } else if (m == 2) {
                visited[--x][y] = 1;
            } else {
                visited[x][++y] = 1;
            }
        }

        solution(x, y, moves, gen + 1);
    }

    public static int nextDirection(int d) {
        if (d == 3) {
            return 0;
        }
        return d + 1;
    }
}