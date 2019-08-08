package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int[][] memory;
    public static int[][] S;

    public static void main(String[] args) {
	    // write your code here
        Scanner sc = new Scanner(System.in);
        int line;
        line = sc.nextInt();
        S = new int[line][3];
        for (int i = 0; i < line; i++) {
            S[i][0] = i;
            S[i][1] = sc.nextInt();
            S[i][2] = sc.nextInt();
        }

        memory = new int[line][line];

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < line; j++) {
                memory[i][j] = -1;
            }
        }

        int result = dp(S, line);
        System.out.println(result);
    }

    public static int dpMemory(int start, int end, int limit) {
        if (start > end) {
            return 0;
        }

        if(memory[start][end] == -1) {
            if(start == end) {
                if(S[start][0] + S[start][1] <= limit) {
                    int result = S[start][2];
                    memory[start][end] = result;
                    return S[start][2];
                } else {
                    memory[start][end] = 0;
                    return 0;
                }
            } else {
                int head = dpMemory(start, start, limit) + dpMemory(start + S[start][1], end, limit);
                int tail = dpMemory(start + 1, end, limit);
                int max = Integer.max(head, tail);
                memory[start][end] = max;
                return max;
            }
        }else {
            return memory[start][end];
        }
    }

    public static int dp(int[][] S, int limit) {
        if(S.length == 1) {
            if(S[0][0] + S[0][1] <= limit) return S[0][2];
            else return 0;
        } else if(S.length == 0 ) {
            return 0;
        }
        int[][] head = Arrays.copyOfRange(S, 0, 1);
        int[][] remain;
        if(head[0][0] + head[0][1] <= limit) remain = Arrays.copyOfRange(S, head[0][1], S.length);
        else remain = new int[0][0];
        int[][] tail = Arrays.copyOfRange(S, 1, S.length);
        return Integer.max(dp(head,limit) + dp(remain,limit),dp(tail,limit));
    }

}
