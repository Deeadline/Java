package pl.lodz.uni.math;

import pl.lodz.uni.math.storage.StorageProvider;

public class Main {
    public static void main(String[] args) {
        StorageProvider storageProvider = new StorageProvider();
    }
}
/*
MAGAZYN
Coding rules:
- max 10 lines of code per function,
- test coverage - 65%,
- storage: x(W) - y(L) - z(H),
- add type of package (toys, furnitures),
- just add only one lift,
- each package might have: description, count of moves, added date, #, priority (!),
         package with lower priority cannot be put over package with higher priority
- Implement:
    getPackageByNumber(Number);
	getAllPackagesByType(Type);
	getHistoryofPreviousMoves(); //historia ruchów dźwigni
	getStatusOfStorage(); // informacja o każdej paczce

 */