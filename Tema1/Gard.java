import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

public class Gard {
	
	public static int n; //numarul de bucati de gard
	public static Object[] v; //vector de perechi

	public static void main(String[] args) throws IOException {

		// se citeste din fisierul de input
		FileReader fr = new FileReader("gard.in");
		BufferedReader br = new BufferedReader(fr);

		// se initilizeaza campurile
		n = Integer.parseInt(br.readLine());
		String line;
		v = new Object[n];
		for (int i = 0; i < n; i++) {
			line = br.readLine();
			String[] parts = line.split(" ");
			v[i] = new Pair(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
		}

		// se sorteaza vectorul -- complexitate theta(n * log(n))
		Arrays.sort(v);
		
		// se scrie rezultatul in fisierul de output
		FileWriter fw = new FileWriter("gard.out");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(String.valueOf(getResult()));
		
		br.close();
		bw.close();

	}
	
	public static int getResult() {
		
		int res = 0;
		Pair p2 = (Pair)v[0];
		
		for (int i = 1; i < n; i++) {
			if (((Pair)v[i]).end > p2.end) {
				p2 = (Pair)v[i];
				continue;
			}
			if (redundant(((Pair)v[i]), p2) == true) {
				res++;
				continue;
			}
		}
		return res;
	}
	
	// se verifica daca perechea p1 este redundanta
	public static boolean redundant(Pair p1, Pair p2) {

		if ((p2.start <= p1.start) && (p1.end <= p2.end)) {
			return true;
		}
		return false;
	}

}
