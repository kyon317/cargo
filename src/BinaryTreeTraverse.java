import java.util.LinkedList;
import java.util.List;

public class BinaryTreeTraverse {
    List<Integer> res = new LinkedList<Integer>();

    // 144
    // 遍历 + 外部变量 res
     public List<Integer> preorderTraversal_1(TreeNode root) {
         traverse(root);
         return res;
     }
     private void traverse(TreeNode root) {
         if (root == null)
             return;
         res.add(root.val);
         traverse(root.left);
         traverse(root.right);
     }


     // 分解问题
    public List<Integer> preorderTraversal_2(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        if (root == null)
            return res;
        res.add(root.val);
        res.addAll(preorderTraversal_2(root.left));
        res.addAll(preorderTraversal_2(root.right));

        return res;
    }


    public List<Integer> inorderTraversal_1(TreeNode root) {
        inorder(root);
        return res;
    }

    private void inorder(TreeNode root) {
        if (root == null)
            return;
        traverse(root.left);
        res.add(root.val);
        traverse(root.right);
    }

    public List<Integer> inorderTraversal_2(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null)
            return res;
        res.addAll(inorderTraversal_2(root.left));
        res.add(root.val);
        res.addAll(inorderTraversal_2(root.right));
        return res;
    }


    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null)
            return res;
        res.addAll(postorderTraversal(root.left));
        res.addAll(postorderTraversal(root.right));
        res.add(root.val);
        return res;
    }

    // 543
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxD(root);
        return max;
    }

    private int maxD(TreeNode root) {
        if (root == null)
            return 0;
        int left = maxD(root.left);
        int right = maxD(root.right);

        int d = left + right;
        max = Math.max(d, max);
        return Math.max(left,right) + 1;
    }

    // 226
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.right = left;
        root.left = right;
        return root;
    }

    // 114
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = left;

        TreeNode p = root;
        while (p.right != null)
            p = p.right;
        p.right = right;
    }

    // 654
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int max = lo;
        for (int i = lo; i <= hi; i++) {
            if (nums[max] < nums[i])
                max = i;
        }
        TreeNode root = new TreeNode(nums[max]);
        root.left = build(nums, lo, max - 1);
        root.right = build(nums, max + 1, hi);

        return root;
    }

}
