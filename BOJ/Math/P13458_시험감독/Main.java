package BOJ.Math.P13458_시험감독;

import java.io.*;
import java.util.*;

public class Main {

    static int n, b, c;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static long solution(){
        long count = n;

        for(int i = 0; i < n; i++){
            int p = arr[i];
            if(p - b > 0){
                count += Math.ceil((double)(p - b) / (double)c);
            }
        }

        return count;
    }
}
