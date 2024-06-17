package 백준.그리디.P1026_보물;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static Integer[] A, B;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        A = new Integer[n];
        B = new Integer[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution(){
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B, Collections.reverseOrder());

        for(int i = 0; i < n; i++){
            answer += A[i] * B[i];
        }

        return answer;
    }
}
