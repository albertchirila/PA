#include <fstream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

const int kNmax = 100005;

class Task {
 public:
	void solve() {
		read_input();
		print_output(get_result());
	}

 private:
	int n;
	int m;
	vector<int> adj[kNmax];

	void read_input() {
		ifstream fin("in");
		fin >> n >> m;
		for (int i = 1, x, y; i <= m; i++) {
			fin >> x >> y;
			adj[x].push_back(y);
		}
		fin.close();
	}

	vector<int> get_result() {
		vector<int> topsort;
		vector<int> v(n + 1);
		int crtNode;
		queue<int> q;

		for (const auto& elem : adj){
			for (int node : elem){
				++v[node];
			}
		}

		for (int i = 1; i <= n; i++){
			if (!v[i]){
				q.push(i);
			}
		}

		while(!q.empty()){
			crtNode = q.front();
			topsort.emplace_back(crtNode);
			q.pop();

			for (int next : adj[crtNode]){
				if (!--v[next]){
					q.push(next);
				}
			}
		}

		return topsort;
	}

	void print_output(vector<int> result) {
		ofstream fout("out");
		for (int i = 0; i < int(result.size()); i++) {
			fout << result[i] << ' ';
		}
		fout << '\n';
		fout.close();
	}
};

int main() {
	Task *task = new Task();
	task->solve();
	delete task;
	return 0;
}
