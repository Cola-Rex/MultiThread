package star;
//今天偶然看到个题，大概题意：一个Map，key为String，value为对应key的put次数，要求线程安全。

import java.util.concurrent.ConcurrentHashMap;

public class CountKey {

    ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    public void count(ConcurrentHashMap<String, Integer> map, String str) {
        map.putIfAbsent(str, 0);
        int c;
        while(!map.replace(str, c = map.get(str), c+1));
    }
}

//思路解析：核心是 map的 3个参数的replace()方法，利用了CAS思想，c与内存中的值相等时，将c+1赋值，并返回true。
//放在while的意思是：如果返回false，则可能有其他线程在修改。则直至true为止。
//很不错的方法，学习学习！
