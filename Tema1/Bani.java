import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Bani {
	public static int type; //setul de instructiuni
	public static int n; //numarul de bancnote
	
	public static int MOD = 1000000007;
	
	public static void main(String[] args) throws IOException {

		// se citeste din fisierul de input
		FileReader fr = new FileReader("bani.in");
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		String[] parts = line.split(" ");
		
		// se initializeaza campurile
		type = Integer.parseInt(parts[0]);
		n = Integer.parseInt(parts[1]);

		// se scrie rezultatul in fisierul de output
		FileWriter fw = new FileWriter("bani.out");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(String.valueOf(getResult()));
		
		br.close();
		bw.close();
	}
	
	public static long getResult() {

		long res = 0;
		if (type == 1) {
			res = (5 * fastPow(2, n - 1)) % MOD;
		} else {
			res = getResultAux();
		}
		return res;
	}
	
	// functia realizeaza ridicarea la putere a unui numar
	public static long fastPow(int base, int exponent) {

		if (exponent == 1) {
			return base;
		}

		if (exponent == 0) {
			return 1;
		}

		long aux = fastPow(base, exponent / 2);
		if (exponent % 2 == 1) {
			return ((((aux % MOD) * (aux % MOD)) % MOD) * (base % MOD)) % MOD;
		} else {
			return ((aux % MOD) * (aux % MOD)) % MOD;
		}
	}

	public static long getResultAux() {

		long res = 0;
		int[][] table = new int[n][5];
		
		for (int j = 0; j < 5; j++) {
			table[0][j] = 1;
		}
		
		/* se calculeaza modurile in care bancnotele pot fi aranjate
		   complexitate: theta(n) */
		for (int i = 1; i < n; i++) {		//O(n)
			for (int j = 0; j < 5; j++) { //O(5)
				switch (j) {
					case 0:
						table[i][j] = ((table[i - 1][1] % MOD) + (table[i - 1][2] % MOD)) % MOD;
						break;
					case 1:
						table[i][j] = ((table[i - 1][0] % MOD) + (table[i - 1][3] % MOD)) % MOD;
						break;
					case 2:
						table[i][j] = ((table[i - 1][0] % MOD) + (table[i - 1][2] % MOD)) % MOD;
						break;
					case 3:
						table[i][j] = ((((table[i - 1][1] % MOD) + (table[i - 1][2] % MOD)) % MOD)
							+ (table[i - 1][4] % MOD)) % MOD;
						break;
					case 4:
						table[i][j] = ((table[i - 1][0] % MOD) + (table[i - 1][3] % MOD)) % MOD;
						break;
					default:
						break;
				}
			}
		}
		
		// se aduna valorile de pe ultima linie a matricei
		for (int k = 0; k < 5; k++) {
			res = ((res % MOD) + (table[n - 1][k] % MOD)) % MOD;
		}
		
		return res;
	}
}
