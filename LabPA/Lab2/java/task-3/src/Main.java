import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.*;

public class Main {
    static class Homework implements Comparable{
        public int deadline;
        public int score;

        public Homework() {
            deadline = 0;
            score = 0;
        }

        public int compareTo (Object o){
            return ((Homework)o).score - this.score;
        }
    }

    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n;
        Homework[] hws;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                hws = new Homework[n];
                for (int i = 0; i < n; i++) {
                    hws[i] = new Homework();
                    hws[i].deadline = sc.nextInt();
                    hws[i].score = sc.nextInt();
                }
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
            // TODO: Aflati punctajul maxim pe care il puteti obtine
            // planificand optim temele.
            Arrays.sort(hws, 0, n);
            int[] v = new int[10];

            for(int i = 0; i < n; i++){
                if (v[hws[i].deadline - 1] == 0)
                    v[hws[i].deadline -1] = hws[i].score;
                else {
                    for (int j = 0; j < hws[i].deadline - 1; j++){
                        if (v[j] == 0){
                            v[j] = hws[i].score;
                            break;
                        }
                    }
                }
            }

            int sum = 0;
            for (int k = 0; k < v.length; k++){
                sum += v[k];
            }

            return sum;
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
