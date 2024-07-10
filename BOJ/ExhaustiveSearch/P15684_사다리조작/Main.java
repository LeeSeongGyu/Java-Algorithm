package BOJ.ExhaustiveSearch.P15684_사다리조작;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m, h;
    static int[][] map;
    static List<int[]> candidates = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][n - 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a - 1][b - 1] = 1;
        }

        bw.write("" + BFS());
        bw.flush();
        bw.close();
    }

    // 추가할 수 있는 모든 가로선 찾기
    public static void findAllCandidates() {
        for (int a = 0; a < h; a++) {
            for (int b = 0; b < n - 1; b++) {
                if (canBeCandidate(a, b)) {
                    candidates.add(new int[]{a, b});
                }
            }
        }
    }

    public static int BFS() {
        findAllCandidates();

        if (checkAllColumn()) {
            return 0;
        }

        Queue<int[]> queue = new ArrayDeque<>();

        int index = 0;
        for (int[] arr : candidates) {
            queue.offer(new int[]{index++});
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (checkBoard(poll)) {
                return poll.length;
            }

            if (poll.length == 3) {
                continue;
            }

            for (int i = poll[poll.length - 1] + 1; i < candidates.size(); i++) {
                int[] offer = new int[poll.length + 1];
                for (int j = 0; j < offer.length - 1; j++) {
                    offer[j] = poll[j];
                }
                offer[offer.length - 1] = i;
                queue.offer(offer);
            }
        }

        return -1;
    }

    public static boolean canBeCandidate(int a, int b) {
        if (map[a][b] == 1) {
            return false;
        }
        if (b - 1 >= 0 && map[a][b - 1] == 1) {
            return false;
        }
        if (b + 1 < n - 1 && map[a][b + 1] == 1) {
            return false;
        }
        return true;
    }

    public static boolean checkBoard(int[] list) {
        for (int index : list) {
            int a = candidates.get(index)[0];
            int b = candidates.get(index)[1];
            if (canBeCandidate(a, b)) {
                map[a][b] = 1;
            }
        }

        boolean flag = checkAllColumn();

        for (int index : list) {
            int a = candidates.get(index)[0];
            int b = candidates.get(index)[1];
            map[a][b] = 0;
        }

        return flag;
    }

    public static boolean checkAllColumn() {
        for (int i = 0; i < n; i++) {
            if (!checkColumn(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkColumn(int start) {
        int current = start;
        for (int i = 0; i < h; i++) {
            if (current < n - 1 && map[i][current] == 1) {
                current++;
                continue;
            }
            if (current - 1 >= 0 && map[i][current - 1] == 1) {
                current--;
            }
        }

        if (current == start) {
            return true;
        } else {
            return false;
        }
    }
}
