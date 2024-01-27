import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        int up = 0, bot = m - 1, left = 0, right = n - 1;
        while (res.size() < m * n) {
            if (up <= bot) {
                for (int j = left; j <= right; j++)
                    res.add(matrix[up][j]);
                up++;
            }
            if (right >= left) {
                for (int i = up; i <= bot; i++)
                    res.add(matrix[i][right]);
                right--;
            }
            if (bot >= up) {
                for (int j = right; j >= left; j--)
                    res.add(matrix[bot][j]);
                bot--;
            }
            if (left <= right) {
                for (int i = bot; i >= up; i--)
                    res.add(matrix[i][left]);
                left++;
            }
        }
        return res;
    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int up = 0, bot = n - 1, left = 0, right = n - 1;
        int counter = 1;
        while (counter <= n * n) {
            if (up <= bot) {
                for (int j = left; j <= right; j++) {
                    res[up][j] = counter;
                    counter++;
                }
                up++;
            }
            if (right >= left) {
                for (int i = up; i <= bot; i++) {
                    res[i][right] = counter;
                    counter++;
                }
                right--;
            }
            if (bot >= up) {
                for (int j = right; j >= left; j--) {
                    res[bot][j] = counter;
                    counter++;
                }
                bot--;
            }
            if (left <= right) {
                for (int i = bot; i >= up; i--) {
                    res[i][left] = counter;
                    counter++;
                }
                left++;
            }
        }
        return res;
    }

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] res = new int[m][n];
        int up = 0, bot = m - 1, left = 0, right = n - 1;
        int counter = 1;
        while (counter <= m * n) {
            if (up <= bot) {
                for (int j = left; j <= right; j++) {
                    if (head != null) {
                        res[up][j] = head.val;
                        head = head.next;
                    } else
                        res[up][j] = -1;
                    counter++;
                }
                up++;
            }
            if (right >= left) {
                for (int i = up; i <= bot; i++) {
                    if (head != null) {
                        res[i][right] = head.val;
                        head = head.next;
                    } else
                        res[i][right] = -1;
                    counter++;
                }
                right--;
            }
            if (bot >= up) {
                for (int j = right; j >= left; j--) {
                    if (head != null) {
                        res[bot][j] = head.val;
                        head = head.next;
                    } else {
                        res[bot][j] = -1;
                    }
                    counter++;
                }
                bot--;
            }
            if (left <= right) {
                for (int i = bot; i >= up; i--) {
                    if (head != null) {
                        res[i][left] = head.val;
                        head = head.next;
                    } else {
                        res[i][left] = -1;
                    }
                    counter++;
                }
                left++;
            }
        }
        return res;
    }

}
