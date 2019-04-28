import java.util.*;
import java.io.*;

public class LRUCache {
    static Deque<Integer> que;
    static Set<Integer> map;
    int c_size;
    
    LRUCache(int n){
        que = new LinkedList<Integer>();
        map = new HashSet<Integer>();
        c_size = n;
    }
    
    public void refer(int x){
        if(!map.contains(x)){
            if(que.size() == c_size){
                que.removeLast();
                map.remove(x);
            }
        } else {
            int index=0, i=0;
            Iterator<Integer> itr = que.iterator();
            while(itr.hasNext()){
                if(itr.next() == x){
                    index = i;
                    break;
                }
                i++;
            }
            que.remove(index);
        }
        que.push(x);
        map.add(x);
    }
    
    public void display(){
        Iterator<Integer> itr = que.iterator();
        while(itr.hasNext()){
            System.out.print(itr.next() + " ");
        }
    }
  
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(4);
        cache.refer(1);
        cache.refer(2);
        cache.refer(3);
        cache.refer(1);
        cache.refer(4);
        cache.refer(5);
        cache.display();
    }
}
