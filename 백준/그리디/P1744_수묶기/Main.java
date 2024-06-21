package 백준.그리디.P1744_수묶기;

import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static List<Integer> positive, negative;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        positive = new ArrayList<>();
        negative = new ArrayList<>();

        for(int i = 0; i < n; i++){
            int next = Integer.parseInt(br.readLine());
            if(next > 0){
                positive.add(next);
            } else {
                negative.add(next);
            }
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution(){
        int sum = 0;

        Collections.sort(positive, Collections.reverseOrder());

        for(int i = 0; i < positive.size();){
            if(i == positive.size() - 1 || positive.get(i + 1) == 1){
                sum += positive.get(i);
                i++;
            } else {
                sum += positive.get(i) * positive.get(i + 1);
                i += 2;
            }
        }

        Collections.sort(negative);
        for(int i = 0; i < negative.size();){
            if(i == negative.size() - 1){
                sum += negative.get(i);
                break;
            } else {
                sum += negative.get(i) * negative.get(i + 1);
                i += 2;
            }
        }

        return sum;
    }
}
