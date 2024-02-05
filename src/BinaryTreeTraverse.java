import java.util.ArrayList;
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

    // 230
    List<Integer> res_ = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        kthSmallest_traverse(root);
        return res.get(k - 1);
    }
    private void kthSmallest_traverse(TreeNode root){
        if(root == null) return;
        kthSmallest_traverse(root.left);
        res.add(root.val);
        kthSmallest_traverse(root.right);
    }

    /* BST */
    // 1038
    public TreeNode bstToGst(TreeNode root) {
        bstToGst_traverse(root);
        return root;
    }
    int sum = 0;
    private void bstToGst_traverse(TreeNode root) {
        if (root == null)
            return;
        bstToGst_traverse(root.right);
        sum += root.val;
        root.val = sum;
        bstToGst_traverse(root.left);
    }

    // 98
    public boolean isValidBST(TreeNode root) {
        return isValidBST_validate(root, null, null);
    }
    private boolean isValidBST_validate(TreeNode root, TreeNode min, TreeNode max){
        if(root == null) return true;
        if(min !=null && root.val <= min.val) return false;
        if(max !=null && root.val >= max.val) return false;

        return isValidBST_validate(root.left, min, root) && isValidBST_validate(root.right, root, max);
    }

    // 700
    public TreeNode searchBST(TreeNode root, int val) {
        return search(root,val);
    }
    private TreeNode search(TreeNode root, int val){
        if(root == null) return null;
        if(root.val == val) return root;
        else if(root.val < val) return search(root.right, val);
        else return search(root.left, val);
    }

    // 701
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        else
            root.right = insertIntoBST(root.right, val);

        return root;
    }

    // 450
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        if (root.val == key) {
            // found, delete;
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;
            if (root.left != null && root.right != null) {
                TreeNode min = getMin(root.right);
                root.right = deleteNode(root.right, min.val);
                min.left = root.left;
                min.right = root.right;
                root = min;
            }
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最小的
        while (node.left != null)
            node = node.left;
        return node;
    }
}
