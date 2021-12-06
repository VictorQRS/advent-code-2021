// Better than naive approach
#include <iostream>
#include <vector>

using namespace std;

long count_zeroes(const vector<int>& vec) {
    long count = 0;
    for (int n : vec)
        if (n == 0)
            count++;
    return count;
}

void generate_offspring(vector<int>& school) {
    long newborns = count_zeroes(school);
    school.resize(school.size()+newborns, 9);
}

int run_internal_clock(int clock) {
    return clock == 0 ? 6 : clock-1;
}

void process_school(vector<int>& school) {
    generate_offspring(school);
    for (int i=0; i<school.size(); i++)
        school[i] = run_internal_clock(school[i]);
}

long process_school_for_inner(int n_days, vector<int>& school) {
    for (int i=0; i<n_days; i++)
        process_school(school);
    return school.size();
}

long process_school_for(int n_days, const vector<int>& school) {
    vector<long> memo(7);

    long count = 0;
    for (int fish : school) {
        if (memo[fish] == 0) {
            vector<int> isolated_fish(1);
            isolated_fish[0] = fish;
            memo[fish] = process_school_for_inner(n_days, isolated_fish);
        }

        count += memo[fish];
    }

    return count;
}

int main() {
    int n_days = 256;
    vector<int> initial_school = {1,1,1,2,1,1,2,1,1,1,5,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,4,1,1,1,1,3,1,1,3,1,1,1,4,1,5,1,3,1,1,1,1,1,5,1,1,1,1,1,5,5,2,5,1,1,2,1,1,1,1,3,4,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,5,4,1,1,1,1,1,5,1,2,4,1,1,1,1,1,3,3,2,1,1,4,1,1,5,5,1,1,1,1,1,2,5,1,4,1,1,1,1,1,1,2,1,1,5,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,3,1,1,3,1,3,1,4,1,5,4,1,1,2,1,1,5,1,1,1,1,1,5,1,1,1,1,1,1,1,1,1,4,1,1,4,1,1,1,1,1,1,1,5,4,1,2,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,4,1,1,1,2,1,4,1,1,1,1,1,1,1,1,1,4,2,1,2,1,1,4,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,3,2,1,4,1,5,1,1,1,4,5,1,1,1,1,1,1,5,1,1,5,1,2,1,1,2,4,1,1,2,1,5,5,3};
    cout << process_school_for(n_days, initial_school);
}
