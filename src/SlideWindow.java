import java.util.*;

public class SlideWindow {

    // 76
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<Character, Integer>();
        Map<Character, Integer> window = new HashMap<Character, Integer>();
        int left = 0, right = 0;
        int start = 0, len = Integer.MAX_VALUE, valid = 0;
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c)))
                    valid++;
            }
            while (valid == need.size()) {
                if (len > right - left) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d)))
                        valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    // 387
    public int firstUniqChar(String s) {
        int[] freq = new int[26];
        for(char c: s.toCharArray()){
            freq[c - 'a']++;
        }
        for(int i = 0; i < s.length(); i++){
            if(freq[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }

    // 239
    private static class MonotonicQueue {
        LinkedList<Integer> list = new LinkedList<>();

        public void push(int n) {
            while (!list.isEmpty() && list.getLast() < n) {
                list.pollLast();
            }
            list.addLast(n);
        }

        public int getMax() {
            return list.getFirst();
        }

        public void pop(int n) {
            if (n == list.getFirst())
                list.pollFirst();
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue mq = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1)
                mq.push(nums[i]);
            else {
                mq.push(nums[i]);
                res.add(mq.getMax());
                mq.pop(nums[i - k + 1]);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
