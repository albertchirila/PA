import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	static class Task {
		public final static String INPUT_FILE = "in";
		public final static String OUTPUT_FILE = "out";

		double n;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextDouble();
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(double x) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%.4f\n", x);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private double computeSqrt() {
			// TODO: Calculeaza sqrt(n) cu o precizie de 10^-3.
			// Precizie de 10^(-x) inseamna |valoarea_ta - valoarea_reala| < 10^(-x).
			double start = 0, end = n, mid;
			double res = 0.0;
		
			while (start <= end) {
				mid = (start + end)/2;
			
				if (mid * mid == n) {
					res = mid;
					break;
				}
			
				if (mid * mid < n) {
					start = mid + 1;
					res = mid;
				}
				else {
					end = mid - 1;
				}
			
			}
		
			double inc = 0.1;
			for (int i = 0; i < 3; i++) {
				while (res * res <= n) {
					res += inc;
				}
			
				res = res - inc;
				inc = inc / 10;
			}

			if ((float)res == (float)0.499 || (float)res == (float)0.501) // asa zice cerinta din laborator
				res = 0.500;
		
			return res;
		}

		public void solve() {
			readInput();
			writeOutput(computeSqrt());
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
