import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        private final static int MOD = 1000000007;

        int n;
        char[] expr;
        int[][] m = new int[1000][1000];
        int[][] f = new int[1000][1000];
        String bool, op;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                String s = sc.next().trim();
                s = " " + s;
                expr = s.toCharArray();
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(int result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private int getResult() {
            // TODO: Calculati numarul de moduri in care se pot aseza
            // parantezele astfel incat rezultatul expresiei sa fie TRUE.
            // Numarul de moduri se va calcula modulo MOD (1000000007).


            int j;
            
            for (char c : expr) {
                if (c == 'T' || c == 'F') {
                    bool += c;
                }
                else if (c != ' ') {
                    op += c;
                }
            }
            
            bool = bool.replace("null", "");
            op = op.replace("null", "");
            int size = bool.length();
            
            for (int i = 0; i < size; i++) {
                if (bool.charAt(i) == 'T') {
                    m[i][i] = 1;
                    f[i][i] = 0;
                }
                else {
                    m[i][i] = 0;
                    f[i][i] = 1;
                }
            }
            
            
            for (int p = 1; p < size; p++) {
                for (int i = 0; i < size; ++i) {

                    j = i + p;

                    if (j < size) {

                        for (int k = i; k < j; ++k) {

                            switch (op.charAt(k)) {

                                case '&':

                                    m[i][j] = (int) ((m[i][j] + 1L * m[i][k] * m[k + 1][j]) % MOD);



                                    f[i][j] = (int) ((f[i][j] + 1L * m[i][k] * f[k + 1][j]) % MOD);

                                    f[i][j] = (int) ((f[i][j] + 1L * f[i][k] * m[k + 1][j]) % MOD);

                                    f[i][j] = (int) ((f[i][j] + 1L * f[i][k] * f[k + 1][j]) % MOD);

                                    break;



                                case '|':

                                    m[i][j] = (int) ((m[i][j] + 1L * f[i][k] * m[k + 1][j]) % MOD);

                                    m[i][j] = (int) ((m[i][j] + 1L * m[i][k] * m[k + 1][j]) % MOD);

                                    m[i][j] = (int) ((m[i][j] + 1L * m[i][k] * f[k + 1][j]) % MOD);



                                    f[i][j] = (int) ((f[i][j] + 1L * f[i][k] * f[k + 1][j]) % MOD);

                                    break;



                                case '^':

                                    m[i][j] = (int) ((m[i][j] + 1L * m[i][k] * f[k + 1][j]) % MOD);

                                    m[i][j] = (int) ((m[i][j] + 1L * f[i][k] * m[k + 1][j]) % MOD);



                                    f[i][j] = (int) ((f[i][j] + 1L * f[i][k] * f[k + 1][j]) % MOD);

                                    f[i][j] = (int) ((f[i][j] + 1L * m[i][k] * m[k + 1][j]) % MOD);

                                    break;

                            }
                        }
                    }
                }
            }
            return m[0][size - 1];
        }

        public void solve() {
            readInput();
            writeOutput(getResult());
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
