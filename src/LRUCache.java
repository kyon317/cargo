import java.util.HashMap;

class LRUCache {
    private HashMap<Integer, Node> map = new HashMap<>();
    private DoubleList cache = new DoubleList();
    private int capacity;

    private void makeRecent(int key){
        Node x = map.get(key);
        cache.remove(x);
        cache.addLast(x);
    }

    private void addRecent(int key, int val){
        Node x = new Node(key,val);
        cache.addLast(x);
        map.put(key, x);
    }

    private void deleteKey(int key){
        Node x = map.get(key);
        cache.remove(x);
        map.remove(key);
    }

    private void removeLeastR(){
        Node x = cache.removeFirst();
        map.remove(x.key);
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        makeRecent(key);
        return map.get(key).val;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            deleteKey(key);
            addRecent(key,value);
            return;
        }
        if(capacity == cache.getSize()){
            removeLeastR();
        }
        addRecent(key,value);
    }

    private class Node {
        public int key, val;
        public Node next, prev;

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    private class DoubleList {
        private Node head, tail;
        private int size;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            this.size = 0;
        }

        public void addLast(Node x) {
            x.prev = tail.prev;
            x.next = tail;
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }

        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        public Node removeFirst() {
            if (head.next == tail)
                return null;
            Node first = head.next;
            remove(first);
            return first;
        }

        public int getSize() {
            return size;
        }
    }
}