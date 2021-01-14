import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Bomboane {
	public static int n; //numarul de elevi
	public static int m; //numarul de bomboane;
	
	public static int MOD = 1000000007;

	public static void main(String[] args) throws IOException {

		FileReader fr = new FileReader("bomboane.in");
		BufferedReader br = new BufferedReader(fr);
		String line;
		line = br.readLine();
		String[] parts = line.split(" ");
		n = Integer.parseInt(parts[0]);
		m = Integer.parseInt(parts[1]);
		
		Vector<Interval> v = new Vector<Interval>();
		for (int i = 0; i < n ; i++) {
			line = br.readLine();
			String[] parts1 = line.split(" ");
			v.add(new Interval(Integer.parseInt(parts1[0]), Integer.parseInt(parts1[1])));
		}
		
		FileWriter fw = new FileWriter("bomboane.out");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(String.valueOf(getResult(v)));

		br.close();
		bw.close();
	}
	
	public static long getResult(Vector<Interval> v) {
		
		int[][] table = new int[n][m + 1];
			
		//se completeaza prima linie
		for (int j = 0; j < m + 1; j++) { //complexitate O(m) - constant
			if (v.get(0).x <= j && j <= v.get(0).y) {
				table[0][j] = 1;
			} else {
				table[0][j] = 0;
			}
		}
		
		//se parcurge matricea
		for (int i = 1; i < n; i++) { //complexitate O(n)
			for (int j = 0; j < m + 1; j++) { //complexitate O(m) - constant
				if (v.get(i).x <= j && j <= v.get(i).y) {
					for (int k = 0; j + 1 + k < m + 1; k++) { //complexitate O(m) - constant 
						table[i][j + 1 + k] = ((table[i][j + 1 + k] % MOD) 
							+ (table[i - 1][1 + k] % MOD)) % MOD;
					}
				}
			}
		}

		//se returneaza rezulatul final
		return table[n - 1][m]; 
	}
}
