package 백준.P10866_덱;

import java.io.*;
import java.util.*;

public class Main {

    static Deque<Integer> deque = new ArrayDeque<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n  = Integer.valueOf(br.readLine());

        for(int i = 0; i < n; i++){
            solution(br.readLine());
        }

        bw.flush();
        bw.close();
    }

    public static void solution(String str) throws IOException {
        if(str.startsWith("push_front")){
            deque.offerFirst(Integer.valueOf(str.substring(11)));
            return;
        }
        if (str.startsWith("push_back")){
            deque.offerLast(Integer.valueOf(str.substring(10)));
            return;
        }

        switch (str){
            case "pop_front":
                if(deque.isEmpty()){
                    bw.write("-1\n");
                } else bw.write("" + deque.pollFirst() + "\n");
                break;
            case "pop_back":
                if(deque.isEmpty()){
                    bw.write("-1\n");
                } else bw.write("" + deque.pollLast() + "\n");
                break;
            case "size":
                bw.write("" + deque.size() + "\n");
                break;
            case "empty":
                if(deque.isEmpty()) bw.write("1\n");
                else bw.write("0\n");
                break;
            case "front":
                if(deque.isEmpty()) bw.write("-1\n");
                else bw.write("" + deque.peekFirst() + "\n");
                break;
            case "back" :
                if(deque.isEmpty()) bw.write("-1\n");
                else bw.write("" + deque.peekLast() + "\n");
                break;
        }
    }
}
