#include <iostream>
#include<vector>
#include<algorithm>
using namespace std;

int findYesChildren(vector<int>& children,vector<int>& cookies);
int main() {
    vector<int> children;
    vector<int> cookies;
    children.push_back(1);
    children.push_back(2);
    cookies.push_back(2);
    cookies.push_back(1);
    cookies.push_back(3);
    cout<<findYesChildren(children,cookies)<<endl;
}

int findYesChildren(vector<int>& children,vector<int>& cookies){
    sort(children.begin(),children.end());
    sort(cookies.begin(),cookies.end());
    int countChildren=0,countCookies=0;
    for(;countChildren<children.size()&&countCookies<cookies.size();++countCookies){
        if(children[countChildren]<=cookies[countCookies]) ++countChildren;
    }
    return countChildren;
}