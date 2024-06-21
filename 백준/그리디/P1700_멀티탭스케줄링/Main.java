package 백준.그리디.P1700_멀티탭스케줄링;

import java.util.*;
import java.io.*;

public class Main {

    static int n, k;
    static int[] arr;
    static List<Queue<Integer>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[k];
        list = new ArrayList<>();
        for(int i = 0; i <= k; i++){
            list.add(new LinkedList<>());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            int item = Integer.parseInt(st.nextToken());
            arr[i] = item;
            list.get(item).offer(i);
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution(){
        List<Integer> using = new ArrayList<>();
        int count = 0;

        for(int i = 0; i < k; i++){
            int item = arr[i];
            if(!using.contains(item)){
                if(using.size() == n){
                    using.remove(Integer.valueOf(getLatest(using)));
                    count++;
                }
                using.add(item);
            }
            list.get(item).poll();
        }

        return count;
    }

    public static int getLatest(List<Integer> using){
        int answer = 0;
        int max = Integer.MIN_VALUE;

        for(int item : using){
            if(list.get(item).isEmpty()){
                return item;
            }

            if(max < list.get(item).peek()){
                answer = item;
                max = list.get(item).peek();
            }
        }

        return answer;
    }
}
