package 백준.P1759_암호만들기;

import java.io.*;
import java.util.*;

public class Main {

    static int l, c;
    static char[] arr, password;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        l = Integer.valueOf(st.nextToken());
        c = Integer.valueOf(st.nextToken());
        arr = new char[c];
        password = new char[l];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);

        DFS(0, 0, 0, 0);

        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(int passwordIndex, int index, int vowels, int consonants)
        throws IOException {
        if (passwordIndex == l) {
            if (vowels >= 1 && consonants > 1) {
                bw.write(String.valueOf(password) + "\n");
            }
            return;
        }
        for (int i = index; i < c; i++) {
            password[passwordIndex] = arr[i];
            if (isVowel(arr[i])) {
                DFS(passwordIndex + 1, i + 1, vowels + 1, consonants);
            } else {
                DFS(passwordIndex + 1, i + 1, vowels, consonants + 1);
            }
        }
    }

    public static boolean isVowel(char c){
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;
        else return false;
    }
}
