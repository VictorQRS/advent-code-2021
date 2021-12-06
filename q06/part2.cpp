#include <iostream>
#include <vector>
#include <array>
#include <algorithm>

using namespace std;

#define MAX 10

using number = long long;
using Counter = array<number, MAX>;

Counter init_counter(const vector<int>& school) {
    Counter counter;
    for (int i=0; i<MAX; i++)
        counter[i] = 0;
    for (auto fish : school)
        counter[fish]++;
    return counter;
}

void generate_offspring(Counter& counter) {
    counter[9] = counter[0];
}

void run_generation(Counter& counter) {
    auto zero_count = counter[0];
    for(int i=1; i<MAX; i++)
        counter[i-1] = counter[i];
    counter[6] += zero_count;
}

void process_counter(Counter& counter) {
    generate_offspring(counter);
    run_generation(counter);
}

number process_school_for(int n_days, const vector<int>& school) {
    Counter counter = init_counter(school);
    for (int i=0; i<n_days; i++)
        process_counter(counter);
    
    number total = 0;
    for (int i=0; i<MAX-1; i++)
        total += counter[i];
    return total;
}

int main() {
    int n_days = 256;
    vector<int> initial_school = {1,1,1,2,1,1,2,1,1,1,5,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,4,1,1,1,1,3,1,1,3,1,1,1,4,1,5,1,3,1,1,1,1,1,5,1,1,1,1,1,5,5,2,5,1,1,2,1,1,1,1,3,4,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,5,4,1,1,1,1,1,5,1,2,4,1,1,1,1,1,3,3,2,1,1,4,1,1,5,5,1,1,1,1,1,2,5,1,4,1,1,1,1,1,1,2,1,1,5,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,3,1,1,3,1,3,1,4,1,5,4,1,1,2,1,1,5,1,1,1,1,1,5,1,1,1,1,1,1,1,1,1,4,1,1,4,1,1,1,1,1,1,1,5,4,1,2,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,4,1,1,1,2,1,4,1,1,1,1,1,1,1,1,1,4,2,1,2,1,1,4,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,3,2,1,4,1,5,1,1,1,4,5,1,1,1,1,1,1,5,1,1,5,1,2,1,1,2,4,1,1,2,1,5,5,3};
    cout << process_school_for(n_days, initial_school);
}
