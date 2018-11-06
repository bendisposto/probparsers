#include <iostream>
#include <string>
#include "BUtils.cpp"
#include "BSet.cpp"
#include "BInteger.cpp"
#include "BBoolean.cpp"

#ifndef Sieve_H
#define Sieve_H

using namespace std;

class Sieve {



    private:



        BSet<BInteger > numbers;
        BInteger cur;
        BInteger limit;

        bool initialized = false;

    public:

        Sieve(){}

        void initialize() {
            if(initialized) {
                throw runtime_error("Machine is already initialized");
            }
            numbers = static_cast<BSet<BInteger > >((BSet<BInteger>::range((BInteger(2)),(BInteger(10000)))));
            cur = static_cast<BInteger >((BInteger(2)));
            limit = static_cast<BInteger >((BInteger(10000)));
            initialized = true;
        }

        BInteger ComputeNumberOfPrimes() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            BInteger res;
            while((cur.greater((BInteger(1)))._and(cur.multiply(cur).lessEqual(limit))).booleanValue()) {
                if((numbers.elementOf(cur)).booleanValue()) {
                    BInteger n;
                    BSet<BInteger > set;
                    n = static_cast<BInteger >(cur);
                    set = static_cast<BSet<BInteger > >((BSet<BInteger >()));
                    while((n.lessEqual(limit.divide(cur))).booleanValue()) {
                        set = static_cast<BSet<BInteger > >(set._union((BSet<BInteger >((vector<BInteger>){cur.multiply(n)}))));
                        n = static_cast<BInteger >(n.plus((BInteger(1))));
                    }
                    numbers = static_cast<BSet<BInteger > >(numbers.complement(set));
                }
                cur = static_cast<BInteger >(cur.plus((BInteger(1))));
            }
            res = static_cast<BInteger >(numbers.card());
            return res;
        }

};

int main() {
    clock_t start,finish;
    double time;
    Sieve sieve;
    sieve.initialize();
    start = clock();
    int result = sieve.ComputeNumberOfPrimes().intValue();
    finish = clock();
    time = (double(finish)-double(start))/CLOCKS_PER_SEC;
    printf("%f\n", time);
    printf("%d\n", result);
    return 0;
}
#endif