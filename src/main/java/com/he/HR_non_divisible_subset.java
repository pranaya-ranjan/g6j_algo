package com.he;

import java.io.*;

public class HR_non_divisible_subset {

    public static void solve(Input in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] cnt = new int[k];
        for (int i = 0; i < n; ++i) {
            cnt[in.nextInt() % k]++;
        }
        int ans = Math.min(1, cnt[0]) + (k % 2 == 0 ? Math.min(1, cnt[k / 2]) : 0);
        for (int i = 1; 2 * i < k; ++i) {
            ans += Math.max(cnt[i], cnt[k - i]);
        }
        out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        solve(new Input(new BufferedReader(new InputStreamReader(System.in))), out);
        out.close();
    }

    static class Input {
        BufferedReader in;
        StringBuilder sb = new StringBuilder();

        public Input(BufferedReader in) {
            this.in = in;
        }

        public Input(String s) {
            this.in = new BufferedReader(new StringReader(s));
        }

        public String next() throws IOException {
            sb.setLength(0);
            while (true) {
                int c = in.read();
                if (c == -1) {
                    return null;
                }
                if (" \n\r\t".indexOf(c) == -1) {
                    sb.append((char)c);
                    break;
                }
            }
            while (true) {
                int c = in.read();
                if (c == -1 || " \n\r\t".indexOf(c) != -1) {
                    break;
                }
                sb.append((char)c);
            }
            return sb.toString();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}