#include <bits/stdc++.h>
using namespace std;

class Task {
 public:
    void solve() {
        read_input();
        print_output(get_result());
    }

 private:
    int n, k;

    void read_input() {
        ifstream fin("in");
        fin >> n >> k;
        fin.close();
    }

    void bkt(int pos, vector<vector<int>> &all, vector<int> &crt_sol, vector<bool> &visited){
        if (pos == k){
            all.emplace_back(crt_sol);
            return;
        }

        for (int i = 1; i <= n; i++){
            if (!visited[i]){
                visited[i] = true;
                crt_sol.emplace_back(i);

                bkt(pos + 1, all, crt_sol, visited);

                visited[i] = false;
                crt_sol.pop_back();
            }
        }
    }

    vector<vector<int> > get_result() {
        vector<vector<int> > all;
        vector<int> crt_sol;
        vector<bool> visited(n, false);

        bkt(0, all, crt_sol, visited);

        return all;
    }

    void print_output(vector<vector<int> > result) {
        ofstream fout("out");
        fout << result.size() << '\n';
        for (int i = 0; i < (int)result.size(); i++) {
            for (int j = 0; j < (int)result[i].size(); j++) {
                fout << result[i][j] <<
                    (j + 1 != result[i].size() ? ' ' : '\n');
            }
        }
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
