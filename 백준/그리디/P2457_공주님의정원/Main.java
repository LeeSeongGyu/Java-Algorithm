package 백준.그리디.P2457_공주님의정원;

import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static Flower[] arr;
    static int[] used;
    static int start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new Flower[n];
        used = new int[n];
        start = 301;
        end = 1130;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());
            arr[i] = new Flower(sm * 100 + sd, em * 100 + ed);
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution() {
        int count = 0;
        Arrays.sort(arr, Collections.reverseOrder());

        while(start <= end){
            boolean find = false;

            for(int i = 0; i < n; i++){
                Flower f = arr[i];
                if(f.start <= start && used[i] == 0){
                    start = f.end;
                    used[i] = 1;
                    count++;
                    find = true;
                    break;
                }
            }

            if(!find){
                return 0;
            }
        }

        return count;
    }

    public static class Flower implements Comparable<Flower> {

        int start;
        int end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower f) {
            if (this.end == f.end) {
                return this.start - f.start;
            }
            return this.end - f.end;
        }
    }
}
