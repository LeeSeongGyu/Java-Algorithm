package 백준.완전탐색.P9019_DSLR;

import java.io.*;
import java.util.*;

public class Main {

    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            bw.write(solution(A, B) + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static String solution(int A, int B) {
        Queue<Digits> queue = new LinkedList<>();
        queue.offer(new Digits("", A));
        int[] visited = new int[10000];

        while (!queue.isEmpty()) {
            Digits poll = queue.poll();

            Digits d = new Digits(poll.operation + "D", D(poll.num));
            if (d.num == B) {
                return d.operation;
            }
            if (visited[d.num] == 0) {
                visited[d.num] = 1;
                queue.offer(d);
            }

            Digits s = new Digits(poll.operation + "S", S(poll.num));
            if (s.num == B) {
                return s.operation;
            }
            if (visited[s.num] == 0) {
                visited[s.num] = 1;
                queue.offer(s);
            }

            Digits l = new Digits(poll.operation + "L", L(poll.num));
            if (l.num == B) {
                return l.operation;
            }
            if (visited[l.num] == 0) {
                visited[l.num] = 1;
                queue.offer(l);
            }

            Digits r = new Digits(poll.operation + "R", R(poll.num));
            if (r.num == B) {
                return r.operation;
            }
            if (visited[r.num] == 0) {
                visited[r.num] = 1;
                queue.offer(r);
            }
        }

        return null;
    }

    public static int D(int num) {
        return num * 2 % 10000;
    }

    public static int S(int num) {
        if (num == 0) {
            return 9999;
        }
        return num - 1;
    }

    public static int L(int num) {
        num = (num * 10) % 10000 + (num / 1000);
        return num;
    }

    public static int R(int num) {
        num = (num / 10) + (num % 10) * 1000;
        return num;
    }

    public static class Digits {

        String operation;
        int num;

        public Digits(String operation, int num) {
            this.operation = operation;
            this.num = num;
        }
    }
}
