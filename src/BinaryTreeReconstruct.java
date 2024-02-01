import java.util.HashMap;

public class BinaryTreeReconstruct {
    HashMap<Integer, Integer> valToIndex = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }
        return build(
                preorder, inorder, 0,
                preorder.length - 1,
                0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder,
                           int preStart, int preEnd,
                           int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int index = valToIndex.get(preorder[preStart]);
        int leftSize = index - inStart;
        TreeNode root = new TreeNode(preorder[preStart]);
        root.left = build(preorder, inorder,
                preStart + 1, preStart + leftSize,
                inStart, index - 1);
        root.right = build(preorder, inorder,
                preStart + leftSize + 1, preEnd,
                index + 1, inEnd);
        return root;
    }
}
