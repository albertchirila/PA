
public class Pair implements Comparable<Pair> {

	public int start;
	public int end;
	
	public Pair(int x_start, int x_end) {
		start = x_start;
		end = x_end;
	}

	// se sorteaza vectorul crescator dupa campul start
	@Override
	public int compareTo(Pair o) {

		if (this.start == o.start) {
			return o.end - this.end;
		}
		return this.start - o.start;
	}
}
