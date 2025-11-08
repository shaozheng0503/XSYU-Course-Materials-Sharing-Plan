#include <iostream>
#include <algorithm> 
#include <string>
using namespace std;

class PCB {
public:
    int ID;
    int PRIORITY;
    int CPUTIME;
    int ALLTIME;
    string STATE;
public:
	PCB(){};
    PCB(int i, int p, int c, int a, string s)
        : ID(i), PRIORITY(p), CPUTIME(c), ALLTIME(a), STATE(s) {}
};

int main() {
	PCB td[5];
	td[0] = PCB(0, 9, 0, 3, "W");
    td[1] = PCB(1, 38, 0, 3, "W");
    td[2] = PCB(2, 30, 0, 6, "W");
    td[3] = PCB(3, 29, 0, 3, "W");
    td[4] = PCB(4, 0, 0, 4, "W");
    int newpro[5];
    int pro[5] = {9, 38, 30, 29, 0};

    while (true) {
        bool allDone = true;
        for (int i = 0; i < 5; ++i) {
            if (td[i].ALLTIME != 0) {
                allDone = false;
                break;
            }
        }
        if (allDone) break;
        for (int i = 0; i < 5; ++i) {
            newpro[i] = pro[i];
        }
        sort(newpro, newpro + 5);
        for (int i = 0; i < 5; ++i) {
            if (pro[i] == newpro[4]) {
            	cout<<"当前正在运行的进程："<<td[i].ID<<endl;
            	switch(i){
					case 0:cout<<"当前就绪队列："<<"1 2 3 4"<<endl;break;
					case 1:cout<<"当前就绪队列："<<"0 2 3 4"<<endl;break;
					case 2:cout<<"当前就绪队列："<<"0 1 3 4"<<endl;break;
					case 3:cout<<"当前就绪队列："<<"0 1 2 4"<<endl;break;
					case 4:cout<<"当前就绪队列："<<"0 1 2 3"<<endl;break;
				}
                pro[i] -= 3;
                td[i].PRIORITY -= 3;
                td[i].CPUTIME += 1;
                td[i].ALLTIME -= 1;
                td[i].STATE = "R";
                for (int j = 0; j < 5; ++j) {
                    if (j != i && td[j].STATE != "F") {
                        td[j].STATE = "W";
                    }
                }
                if (td[i].ALLTIME == 0) {
                    td[i].STATE = "F";
                    pro[i] = -100;
                    break;
                }
                break;
            }
        }
        cout << td[0].ID << "       " << td[1].ID << "       " << td[2].ID << "       " << td[3].ID << "       " << td[4].ID << "       进程标识数" << endl;
        cout << td[0].PRIORITY << "       " << td[1].PRIORITY << "      " << td[2].PRIORITY << "      "<< td[3].PRIORITY << "      " << td[4].PRIORITY << "       进程优先数 " << endl;
        cout << td[0].CPUTIME << "       " << td[1].CPUTIME << "       " << td[2].CPUTIME << "       "<< td[3].CPUTIME << "       " << td[4].CPUTIME << "       进程已占用时间片 " << endl;
        cout << td[0].ALLTIME << "       " << td[1].ALLTIME << "       " << td[2].ALLTIME << "       "<< td[3].ALLTIME << "       " << td[4].ALLTIME << "       进程尚需时间片数" << endl;
        cout << td[0].STATE << "       " << td[1].STATE << "       " << td[2].STATE << "       "<< td[3].STATE << "       " << td[4].STATE << "       进程状态" << endl;
        cout << endl << endl;
    }
    return 0;
}
