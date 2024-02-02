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

    HashMap<Integer, Integer> indexMap = new HashMap<>();

    public TreeNode buildTree_2(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return build_2(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode build_2(int[] inorder, int[] postorder,
                           int inStart, int inEnd,
                           int postStart, int postEnd) {
        if (postStart > postEnd)
            return null;
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int index = indexMap.get(rootVal);

        int leftSize = index - inStart;

        root.left = build(inorder, postorder, inStart, index - 1, postStart, postStart + leftSize - 1);
        root.right = build(inorder, postorder, index + 1, inEnd, postStart + leftSize, postEnd - 1);
        return root;
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            indexMap.put(postorder[i], i);
        }
        return build_3(preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode build_3(int[] preorder, int[] postorder,
                           int preStart, int preEnd,
                           int postStart, int postEnd) {
        if (preStart > preEnd)
            return null;
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        if(preStart + 1 > preEnd) return root;
        int leftVal = preorder[preStart + 1];
        int index = indexMap.get(leftVal);
        int leftSize = index - postStart;

        root.left = build(preorder, postorder, preStart + 1, preStart + leftSize + 1, postStart,index - 1);
        root.right = build(preorder, postorder, preStart + leftSize + 2, preEnd,  index + 1, postEnd - 1);

        return root;
    }
}
