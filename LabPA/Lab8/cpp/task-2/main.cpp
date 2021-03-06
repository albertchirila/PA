#include <bits/stdc++.h>
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
			adj[y].push_back(x);
		}
		fin.close();
	}

	void DFS(int crtNode, int& time, vector<int>& disc, vector<int>& firstAccessible,
             vector<int>& sol) {
        disc[crtNode] = time;
        firstAccessible[crtNode] = time++;
        vector<int> children;

        for (int nextNode : adj[crtNode]) {
            if (!disc[nextNode]) {
                children.emplace_back(nextNode);
                DFS(nextNode, time, disc, firstAccessible, sol);
                firstAccessible[crtNode] = min(firstAccessible[nextNode], firstAccessible[crtNode]);
            } else {
                firstAccessible[crtNode] = min(disc[nextNode], firstAccessible[crtNode]);
            }
        }

        if (disc[crtNode] == firstAccessible[crtNode] && children.size() >= 2) {
            sol.emplace_back(crtNode);
        } else if (disc[crtNode] != firstAccessible[crtNode]) {
            for (int childNode : children) {
                if (firstAccessible[childNode] >= disc[crtNode]) {
                    sol.emplace_back(crtNode);
                    break;
                }
            }
        }
    }

	vector<int> get_result() {
		/*
		TODO: Gasiti nodurile critice ale grafului neorientat stocat cu liste
		de adiacenta in adj.
		*/
        vector<int> sol;
        vector<int> disc(n + 1), firstAccessible(n + 1);
        int startTime = 1;

        for (int i = 1; i <= n; ++i) {
            if (!disc[i]) {
                DFS(i, startTime, disc, firstAccessible, sol);
            }
        }

		return sol;
	}

	void print_output(const vector<int>& result) {
		ofstream fout("out");
		for (int i = 0; i < int(result.size()); i++) {
			fout << result[i] << ' ';
		}
		fout << '\n';
		fout.close();
	}
};

// Please always keep this simple main function!
int main() {
	// Allocate a Task object on heap in order to be able to
	// declare huge static-allocated data structures inside the class.
	Task *task = new Task();
	task->solve();
	delete task;
	return 0;
}
