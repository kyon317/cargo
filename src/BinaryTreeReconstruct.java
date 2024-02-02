import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "#";

        String left = serialize(root.left);
        String right = serialize(root.right);
        // preorder
        String curr = root.val + "," + left + "," + right;
        return curr;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> NodeList = new LinkedList<>();
        for(String s: data.split(",")){
            NodeList.add(s);
        }

        return deserialize(NodeList);
    }

    private TreeNode deserialize(List<String> nodes){
        if(nodes.isEmpty()) return null;

        String rootVal = nodes.get(0);
        nodes.remove(0);
        if(rootVal.equals("#")) return null;
        int RootIntVal = Integer.parseInt(rootVal);
        TreeNode root = new TreeNode(RootIntVal);

        root.left = deserialize(nodes);
        root.right = deserialize(nodes);

        return root;
    }
}
