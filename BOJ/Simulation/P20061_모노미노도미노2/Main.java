package BOJ.Simulation.P20061_모노미노도미노2;

import java.io.*;
import java.util.*;

public class Main {

    static int n, score;
    static int[][] blue, green;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        score = 0;
        blue = new int[4][6];
        green = new int[6][4];


        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            play(t, x, y);
        }

        bw.write(score + "\n" + count());
        bw.flush();
        bw.close();
    }

    public static void play(int t, int x, int y){
        switch(t){
            case 1:
                putOneBlock(x, y);
                break;
            case 2:
                putSecondBlock(x, y);
                break;
            case 3:
                putThirdBlock(x, y);
                break;
            default:
                return;
        }

        check();
    }

    public static void putOneBlock(int x, int y){
        blue[x][getFurthestYOnBlue(x)] = 1;
        green[getFurthestXOnGreen(y)][y] = 1;
    }

    public static void putSecondBlock(int x, int y){
        int fy = getFurthestYOnBlue(x);
        blue[x][fy] = 1;
        blue[x][fy - 1] = 1;

        int fx = Math.min(getFurthestXOnGreen(y), getFurthestXOnGreen(y + 1));
        green[fx][y] = 1;
        green[fx][y + 1] = 1;
    }

    public static void putThirdBlock(int x, int y){
        int fx = getFurthestXOnGreen(y);
        green[fx][y] = 1;
        green[fx - 1][y] = 1;

        int fy = Math.min(getFurthestYOnBlue(x), getFurthestYOnBlue(x + 1));
        blue[x][fy] = 1;
        blue[x + 1][fy] = 1;
    }

    public static int getFurthestYOnBlue(int x){
        int y_pos = 0;

        while(true){
            if(y_pos == 5 || blue[x][y_pos + 1] == 1){
                break;
            }
            y_pos++;
        }

        return y_pos;
    }

    public static int getFurthestXOnGreen(int y){
        int x_pos = 0;

        while(true){
            if(x_pos == 5 || green[x_pos + 1][y] == 1){
                break;
            }
            x_pos++;
        }

        return x_pos;
    }


    public static void check(){
        // 점수 정산
        scoreBlue();
        scoreGreen();

        // 연한 칸
        for(int i = 0; i < 2; i++){
            lightBlue();
            lightGreen();
        }
    }

    public static void scoreBlue(){
        Loop1:
        for(int j = 0; j < 6; j++){
            for(int i = 0; i < 4; i++){
                if(blue[i][j] == 0){
                    continue Loop1;
                }
            }
            slideBlue(j);
            score++;
        }
    }

    public static void scoreGreen(){
        Loop1:
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 4; j++){
                if(green[i][j] == 0){
                    continue Loop1;
                }
            }
            slideGreen(i);
            score++;
        }
    }

    public static void slideBlue(int y){
        for(int i = 0; i < 4; i++){
            blue[i][y] = 0;
        }

        for(int k = y; k > 0; k--){
            for(int i = 0; i < 4; i++){
                blue[i][k] = blue[i][k - 1];
            }
        }

        for(int i = 0; i < 4; i++){
            blue[i][0] = 0;
        }
    }

    public static void slideGreen(int x){
        for(int j = 0; j < 4; j++){
            green[x][j] = 0;
        }

        for(int k = x; k > 0; k--){
            for(int j = 0; j < 4; j++){
                green[k][j] = green[k-1][j];
            }
        }

        for(int j = 0; j < 4; j++){
            green[0][j] = 0;
        }
    }

    public static void lightBlue(){
        for(int i = 0; i < 4; i++){
            if(blue[i][1] == 1){
                slideBlue(5);
                return;
            }
        }
    }

    public static void lightGreen(){
        for(int j = 0; j < 4; j++){
            if(green[1][j] == 1){
                slideGreen(5);
                return;
            }
        }
    }

    public static int count(){
        int count = 0;

        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 6; j++){
                count += blue[i][j];
                count += green[j][i];
            }
        }

        return count;
    }
}

