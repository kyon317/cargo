import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// LeetCode 710
public class RandomPickWithBlackList {
    // Brute Force
    HashSet<Integer> blackset;
    ArrayList<Integer> valueList;

    public void RandomPickWithBlackList_brute(int n, int[] blacklist) {
        blackset = new HashSet<Integer>();
        valueList = new ArrayList<Integer>();
        for (int num : blacklist) {
            blackset.add(num);
        }

        for (int i = 0; i < n; i++) {
            if (!blackset.contains(i))
                valueList.add(i);
        }
    }

//    public int pick() {
//        return valueList.get((int) (Math.random() * valueList.size()));
//    }
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    int actual_size;
    public RandomPickWithBlackList(int n, int[] blacklist) {
        actual_size = n - blacklist.length;
        for (int num : blacklist) {
            // -1 for blacklist numbers
            map.put(num, -1);
        }
        int last = n - 1;
        // construct mapping regarding blacklist numbers
        for (int num : blacklist) {
            if (num >= actual_size)
                continue;
            // update last
            while (map.containsKey(last))
                last--;
            map.put(num, last);
            last--;
        }
    }

    public int pick() {
        // if index hit black list, return map(index), else return index
        int index = (int) (Math.random() * actual_size);
        return map.getOrDefault(index,index);
    }
}
