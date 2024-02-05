import java.util.Stack;

public class Stack_mono {

    // 739
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> s = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!s.isEmpty() && temperatures[s.peek()] <= temperatures[i]) {
                s.pop();
            }
            res[i] = s.isEmpty() ? 0 : (s.peek() - i);
            s.push(i);
        }
        return res;
    }
}
