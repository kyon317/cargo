import java.util.*;

public class Dijkstra {
    private int weight(int from, int to){
        return 0;
    }
    List<Integer> adj(int s){
        return new ArrayList<>();
    }
    int[] dijkstra(int start, List<Integer>[] graph) {
        // 图中节点的个数
        int V = graph.length;
        // 记录最短路径的权重，你可以理解为 dp table
        // 定义：distTo[i] 的值就是节点 start 到达节点 i 的最短路径权重
        int[] distTo = new int[V];
        // 求最小值，所以 dp table 初始化为正无穷
        Arrays.fill(distTo, Integer.MAX_VALUE);
        // base case，start 到 start 的最短距离就是 0
        distTo[start] = 0;

        // 优先级队列，distFromStart 较小的排在前面
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.distFromStart - b.distFromStart;
        });

        // 从起点 start 开始进行 BFS
        pq.offer(new State(start, 0));

        while (!pq.isEmpty()) {
            State curState = pq.poll();

            int curNodeID = curState.id;
            int curDistFromStart = curState.distFromStart;

            if (curDistFromStart > distTo[curNodeID]) {
                // 已经有一条更短的路径到达 curNode 节点了
                continue;
            }
            // 将 curNode 的相邻节点装入队列
            for (int nextNodeID : adj(curNodeID)) {
                // 看看从 curNode 达到 nextNode 的距离是否会更短
                int distToNextNode = distTo[curNodeID] + weight(curNodeID, nextNodeID);
                if (distTo[nextNodeID] > distToNextNode) {
                    // 更新 dp table
                    distTo[nextNodeID] = distToNextNode;
                    // 将这个节点以及距离放入队列
                    pq.offer(new State(nextNodeID, distToNextNode));
                }
            }
        }
        return distTo;
    }
    private class State{
        int id;
        int distFromStart;
        State(int id, int distFromStart){
            this.id  = id;
            this.distFromStart = distFromStart;
        }
    }
}
