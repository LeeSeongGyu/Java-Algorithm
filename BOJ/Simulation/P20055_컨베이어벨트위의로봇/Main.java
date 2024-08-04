package BOJ.Simulation.P20055_컨베이어벨트위의로봇;

import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int[] belt;
    static List<Integer> robots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        belt = new int[2 * n];
        for(int i = 0; i < 2 * n; i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }

        robots = new ArrayList<>();

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution(){
        int stage = 1;

        while(true){
            rotate();
            moveRobot();
            putRobot();

            if(isEnd()){
                return stage;
            }
            stage++;
        }
    }

    public static void rotate(){
        // 벨트 회전
        int tmp = belt[2 * n - 1];
        for(int i = 2 * n - 1; i > 0; i--){
            belt[i] = belt[i - 1];
        }
        belt[0] = tmp;

        // 로봇 이동
        for(int i = 0; i < robots.size(); i++){
            int next = robots.get(i) + 1;
            robots.set(i, next);
        }

        // 내리는 위치의 로봇 내리기
        robots.removeIf(integer -> integer == n - 1);
    }

    public static void moveRobot(){
        for(int i = 0; i < robots.size(); i++){
            Integer next = robots.get(i) + 1;
            if(belt[next] == 0){
                continue;
            }
            if(next == n - 1 || !robots.contains(next)){
                robots.set(i, next);
                belt[next]--;
            }
        }

        // 내리는 위치의 로봇 내리기
        robots.removeIf(integer -> integer == n - 1);
    }

    public static void putRobot(){
        if(belt[0] != 0){
            robots.add(0);
            belt[0]--;
        }
    }

    public static boolean isEnd(){
        int count = 0;
        for(Integer b : belt){
            if(b == 0) count++;
        }

        return count >= k;
    }
}

