package 백준.완전탐색.P5014_스타트링크;

import java.io.*;
import java.util.*;

public class Main {

    static int f, s, g, u, d;
    static int[] ch;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        ch = new int[f + 1];

        int answer = BFS();

        if (answer == -1) {
            bw.write("use the stairs");
        } else {
            bw.write("" + answer);
        }
        bw.flush();
        bw.close();
    }

    public static int BFS() {
        Queue<Floor> queue = new LinkedList<>();
        queue.offer(new Floor(s, 0));
        ch[s] = 1;

        while (!queue.isEmpty()) {
            Floor poll = queue.poll();

            if(poll.floor == g){
                return poll.count;
            }

            Floor up = new Floor(poll.floor + u, poll.count + 1);
            Floor down = new Floor(poll.floor - d, poll.count + 1);

            if (up.floor <= f && ch[up.floor] == 0) {
                ch[up.floor] = 1;
                queue.offer(up);
            }

            if (down.floor > 0 && ch[down.floor] == 0) {
                ch[down.floor] = 1;
                queue.offer(down);
            }
        }

        return -1;
    }

    public static class Floor {

        int floor;
        int count;

        public Floor(int floor, int count) {
            this.floor = floor;
            this.count = count;
        }
    }
}
