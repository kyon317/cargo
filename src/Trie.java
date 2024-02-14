import java.util.LinkedList;
import java.util.List;

public class Trie {
    public static class TrieNode<V>{
        V val = null;
        TrieNode<V>[] children = new TrieNode[256];
    }
    public static class TrieMap<V>{
        private static final int R = 256;
        private int size = 0;

        private TrieNode<V> root = null;
        public int size(){
            return size;
        }

        private TrieNode<V> getNode(TrieNode<V> node, String key){
            TrieNode<V> p = node;
            for (int i = 0; i < key.length(); i++){
                if (p == null) return null;

                char c = key.charAt(i);
                p = p.children[c];
            }
            return p;
        }

        public V get(String key){
            TrieNode<V> x = getNode(root,key);
            if (x == null || x.val == null){
                return null;
            }
            return x.val;
        }

        public boolean containsKey(String key){
            return get(key) != null;
        }

        public boolean hasKeyWithPrefix(String prefix){
            return getNode(root,prefix) != null;
        }

        public String shortestPrefixOf(String query){
            TrieNode<V> p = root;
            for (int i = 0; i < query.length(); i++){
                if (p == null) return "";
                if (p.val != null) return query.substring(0,i);
                char c = query.charAt(i);
                p = p.children[c];
            }
            // check if query is a key
            if (p != null && p.val != null) return query;
            return "";
        }

        public String longestPrefixOf(String query){
            TrieNode<V> p = root;
            int max_len = 0;
            for (int i = 0; i < query.length(); i++){
                if (p == null) break;
                if (p.val != null) max_len = i;
                char c = query.charAt(i);
                p = p.children[c];
            }
            // check if query is a key
            if (p != null && p.val != null) return query;
            return query.substring(0,max_len);
        }

        public List<String> keysWithPrefix(String prefix){
            List<String> res = new LinkedList<>();
            TrieNode<V> x = getNode(root,prefix);
            if (x == null) return res;
            traverse(x, new StringBuilder(prefix), res);
            return res;
        }

        private void traverse(TrieNode<V> node, StringBuilder path, List<String> res){
            if (node == null) return;
            if (node.val != null) res.add(path.toString());

            for (char c = 0; c < R; c++){
                path.append(c);
                traverse(node.children[c], path,res);
                path.deleteCharAt(path.length() - 1);
            }
        }

        public List<String> keysWithPattern(String pattern) {
            List<String> res = new LinkedList<>();
            traverse(root, new StringBuilder(), pattern, 0, res);
            return res;
        }
        private void traverse(TrieNode<V> node, StringBuilder path, String pattern, int i, List<String> res) {
            if (node == null) {
                // 树枝不存在，即字符 pattern[i-1] 匹配失败
                return;
            }
            if (i == pattern.length()) {
                // pattern 匹配完成
                if (node.val != null) {
                    // 如果这个节点存储着 val，则找到一个匹配的键
                    res.add(path.toString());
                }
                return;
            }
            char c = pattern.charAt(i);
            if (c == '.') {
                // pattern[i] 是通配符，可以变化成任意字符
                // 多叉树（回溯算法）遍历框架
                for (char j = 0; j < R; j++) {
                    path.append(j);
                    traverse(node.children[j], path, pattern, i + 1, res);
                    path.deleteCharAt(path.length() - 1);
                }
            } else {
                // pattern[i] 是普通字符 c
                path.append(c);
                traverse(node.children[c], path, pattern, i + 1, res);
                path.deleteCharAt(path.length() - 1);
            }
        }

        // 判断是和否存在前缀为 prefix 的键
        public boolean hasKeyWithPattern(String pattern) {
            // 从 root 节点开始匹配 pattern[0..]
            return hasKeyWithPattern(root, pattern, 0);
        }

        // 函数定义：从 node 节点开始匹配 pattern[i..]，返回是否成功匹配
        private boolean hasKeyWithPattern(TrieNode<V> node, String pattern, int i) {
            if (node == null) {
                // 树枝不存在，即匹配失败
                return false;
            }
            if (i == pattern.length()) {
                // 模式串走到头了，看看匹配到的是否是一个键
                return node.val != null;
            }
            char c = pattern.charAt(i);
            // 没有遇到通配符
            if (c != '.') {
                // 从 node.children[c] 节点开始匹配 pattern[i+1..]
                return hasKeyWithPattern(node.children[c], pattern, i + 1);
            }
            // 遇到通配符
            for (int j = 0; j < R; j++) {
                // pattern[i] 可以变化成任意字符，尝试所有可能，只要遇到一个匹配成功就返回
                if (hasKeyWithPattern(node.children[j], pattern, i + 1)) {
                    return true;
                }
            }
            // 都没有匹配
            return false;
        }

        // 在 map 中添加或修改键值对
        public void put(String key, V val) {
            if (!containsKey(key)) {
                // 新增键值对
                size++;
            }
            // 需要一个额外的辅助函数，并接收其返回值
            root = put(root, key, val, 0);
        }

        // 定义：向以 node 为根的 Trie 树中插入 key[i..]，返回插入完成后的根节点
        private TrieNode<V> put(TrieNode<V> node, String key, V val, int i) {
            if (node == null) {
                // 如果树枝不存在，新建
                node = new TrieNode<>();
            }
            if (i == key.length()) {
                // key 的路径已插入完成，将值 val 存入节点
                node.val = val;
                return node;
            }
            char c = key.charAt(i);
            // 递归插入子节点，并接收返回值
            node.children[c] = put(node.children[c], key, val, i + 1);
            return node;
        }

        // 在 Map 中删除 key
        public void remove(String key) {
            if (!containsKey(key)) {
                return;
            }
            // 递归修改数据结构要接收函数的返回值
            root = remove(root, key, 0);
            size--;
        }

        // 定义：在以 node 为根的 Trie 树中删除 key[i..]，返回删除后的根节点
        private TrieNode<V> remove(TrieNode<V> node, String key, int i) {
            if (node == null) {
                return null;
            }
            if (i == key.length()) {
                // 找到了 key 对应的 TrieNode，删除 val
                node.val = null;
            } else {
                char c = key.charAt(i);
                // 递归去子树进行删除
                node.children[c] = remove(node.children[c], key, i + 1);
            }
            // 后序位置，递归路径上的节点可能需要被清理
            if (node.val != null) {
                // 如果该 TireNode 存储着 val，不需要被清理
                return node;
            }
            // 检查该 TrieNode 是否还有后缀
            for (int c = 0; c < R; c++) {
                if (node.children[c] != null) {
                    // 只要存在一个子节点（后缀树枝），就不需要被清理
                    return node;
                }
            }
            // 既没有存储 val，也没有后缀树枝，则该节点需要被清理
            return null;
        }
    }
}
