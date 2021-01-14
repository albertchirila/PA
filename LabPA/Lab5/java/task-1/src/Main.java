import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        static int n, k;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                k = sc.nextInt();
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(ArrayList<ArrayList<Integer>> result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result.size());
                for (ArrayList<Integer> arr : result) {
                    for (int i = 0; i < arr.size(); i++) {
                        pw.printf("%d%c", arr.get(i), i + 1 == arr.size() ?
                                '\n' : ' ');
                    }
                }
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private static void bkt(int pos, ArrayList<ArrayList<Integer>> all, ArrayList<Integer> crt_sol, ArrayList<Boolean> visited){
            if (pos == k){
                all.add(crt_sol);
                return;
            }

            for (int i = 1; i <= n; i++){
                if (!visited.get(i)){
                    visited.get(i) = true;
                    crt_sol.add(i);

                    bkt(pos + 1, all, crt_sol, visited);

                    visited[i] = false;
                    crt.sol = ArrayUtils.remove(crt_sol, crt_sol.size() - 1);
                }
            }
        }

        private ArrayList<ArrayList<Integer>> getResult() {

            ArrayList<ArrayList<Integer>> all = new ArrayList<>();
            ArrayList<Integer> crt_sol = new ArrayList<>();
            ArrayList<Boolean> visited = new ArrayList<>(n);
            Arrays.fill(visited, false);

            bkt(0, all, crt_sol, visited);

            return all;
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
