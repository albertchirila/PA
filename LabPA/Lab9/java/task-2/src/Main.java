import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static class Task {
		public static final String INPUT_FILE = "in";
		public static final String OUTPUT_FILE = "out";
		public static final int NMAX = 50005;
		public static final int MAX_VALUE = 11000;

		int n;
		int m;
		int source;

		public class Edge {
			public int node;
			public int cost;

			Edge(int _node, int _cost) {
				node = _node;
				cost = _cost;
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<Edge> adj[] = new ArrayList[NMAX];

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				source = sc.nextInt();

				for (int i = 1; i <= n; i++)
					adj[i] = new ArrayList<>();
				for (int i = 1; i <= m; i++) {
					int x, y, w;
					x = sc.nextInt();
					y = sc.nextInt();
					w = sc.nextInt();
					adj[x].add(new Edge(y, w));
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int[] result) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(
								OUTPUT_FILE));
				StringBuilder sb = new StringBuilder();
				if (result.length == 0) {
					sb.append("Ciclu negativ!\n");
				} else {
					for (int i = 1; i <= n; i++) {
						sb.append(result[i]).append(' ');
					}
					sb.append('\n');
				}
				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int[] getResult() {
			Queue<Integer> q = new LinkedList<>();
			int[] d = new int[n + 1];
			for (int i = 0; i <= n; i++) {
				d[i] = MAX_VALUE; //orice nod diferit de sursa
			}
			d[source] = 0;
			q.add(source);

			for (int k = 1; k < n - 1; k++) {
				while (q.size() != 0) {
					int u = q.remove();
					for (int j = 0; j < adj[u].size(); j++) {
						int v = adj[u].get(j).node;
						int cost = adj[u].get(j).cost;
						if (d[v] > d[u] + cost) {
							d[v] = d[u] + cost;
							q.add(v);
						}
					}
				}
			}
			return d;
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
