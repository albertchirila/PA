import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	static class Task {
		public final static String INPUT_FILE = "in";
		public final static String OUTPUT_FILE = "out";

		int n, x, y;


		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt();
				x = sc.nextInt();
				y = sc.nextInt();
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int pow(int n) {
			int lg = 1;
			for (int i = 1; i <= n; i++)
				lg *= 2;
			return lg;
		}

		private void writeOutput(int answer) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d\n", answer);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int getAnswer(int n, int x, int y) {
			// TODO: Calculati valoarea de pe pozitia (x, y) din matricea de dimensiune
			// 2^N * 2^N.
			
			int  half = (n-1) * 2;
			int val = 1;
			int lin = 1, col =1;
			int maxl = 2 * half, maxc = 2 * half;
			
			if (n == 1) {
				if (x == 1 && y == 1)
					return 1;
				else if (x==1 && y == 2)
					return 2;
				else if (x==2 && y==1)
					return 3;
				return 4;
			}

			while (half > 0) {
				if (lin + half <= x && col + half <= y) { //cadranul 4
					lin += half;
					col += half;
					val += 3 * half * half;
				}
				else if (lin + half <= x && col + half >= y) { //cadranul 3
					lin += half;
					maxc -= half;
					val += 2 * half * half;
				}
				else if (lin + half >= x && col + half <= y) { //cadranul 2
					maxl -= half;
					col += half;
					val += half * half;
				}
				else { //cadranul 1
					maxl -= half;
					maxc -= half;
				}
			
				half = half / 2;
			}


		
			return val;
		}

		public void solve() {
			readInput();
			writeOutput(getAnswer(n, x, y));
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
	
	
