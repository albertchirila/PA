#include <bits/stdc++.h>
using namespace std;

const int kNmax = 50005;
const int kInf = 0x3f3f3f3f;

class Task {
 public:
    void solve() {
        read_input();
        print_output(get_result());
    }

 private:
    int n;
    int m;
    int source;
    vector<pair<int, int> > adj[kNmax];

    void read_input() {
        ifstream fin("in");
        fin >> n >> m >> source;
        for (int i = 1, x, y, w; i <= m; i++) {
            fin >> x >> y >> w;
            adj[x].push_back(make_pair(y, w));
        }
        fin.close();
    }

    vector<int> get_result() {
       int crtNode;
        vector<int> d(n + 1, -1);
        auto cmp = [&d](int x, int y) {
            return d[x] > d[y];
        };
        priority_queue<int, vector<int>, decltype(cmp)> pq(cmp);
        d[source] = 0;
        pq.emplace(source);

        for (; !pq.empty(); pq.pop()) {
            crtNode = pq.top();

            for (const auto& nextNode : adj[crtNode]) {
                if (d[nextNode.first] == -1 || d[nextNode.first] > nextNode.second + d[crtNode]) {
                    d[nextNode.first] = nextNode.second + d[crtNode];
                    pq.emplace(nextNode.first);
                }
            }
        }

        return d;
    }

    void print_output(vector<int> result) {
        ofstream fout("out");
        for (int i = 1; i <= n; i++) {
            fout << result[i] << " ";
        }
        fout << "\n";
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
