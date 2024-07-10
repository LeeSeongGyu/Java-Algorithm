package BOJ.Greedy.P2847_게임을만든동준이;

import java.io.*;

public class Main {

    static int n;
    static Integer[] scores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        scores = new Integer[n];

        for(int i = 0; i < n; i++){
            scores[i] = Integer.parseInt(br.readLine());
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution(){
        int count = 0;

        for(int i = scores.length - 2; i >= 0; i--){
            if(scores[i + 1] <= scores[i]){
                int newScore = scores[i + 1] - 1;
                count += scores[i] - newScore;
                scores[i] = newScore;
            }
        }

        return count;
    }
}
