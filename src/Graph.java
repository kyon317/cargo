import java.util.*;
import java.util.LinkedList;
public class Graph {
    // 797
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<Integer>();
        traverse(graph, 0, path);
        return res;
    }
    private void traverse(int[][] graph, int s, List<Integer> path){
        path.addLast(s);
        int goal = graph.length - 1;
        if(s == goal){
            res.add(new LinkedList<>(path));
        }
        for(int v : graph[s]){
            traverse(graph,v,path);
        }

        path.removeLast();
    }

    // 207
    class CourseSchedule {
        private boolean[] visited;
        private boolean[] onPath;

        private boolean hasCycle = false;
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            visited = new boolean[numCourses];
            onPath = new boolean[numCourses];
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);
            for(int i = 0; i < numCourses; i++){
                traverse(graph, i);
            }
            return !hasCycle;
        }
        private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites){
            List<Integer>[] graph = new LinkedList[numCourses];
            for(int i = 0; i < numCourses; i++){
                graph[i] = new LinkedList<Integer>();
            }
            for(int[] p: prerequisites){
                int from = p[0], to = p[1];
                graph[from].add(to);
            }
            return graph;
        }

        private void traverse(List<Integer>[] graph, int s){
            if(onPath[s]){
                hasCycle = true;
            }
            if(hasCycle || visited[s]) return;
            visited[s] = true;
            onPath[s] = true;
            for(int t: graph[s]){
                traverse(graph,t);
            }
            onPath[s] = false;
        }
    }

    // 210
    class CourseSchedule_2 {
        private boolean[] visited;
        private boolean[] onPath;
        private List<Integer> res = new LinkedList<>();
        private boolean hasCycle = false;

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[] path = new int[numCourses];
            if(check(numCourses,prerequisites)) return new int[0];
            Collections.reverse(res);
            for(int i = 0; i < numCourses; i++){
                path[i] = res.get(i);
            }
            return path;
        }

        private boolean check(int numCourses, int[][] prerequisites){
            visited = new boolean[numCourses];
            onPath = new boolean[numCourses];
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);
            for(int i = 0; i < numCourses; i++){
                traverse(graph, i);
            }
            return hasCycle;
        }

        private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = new LinkedList[numCourses];
            for (int i = 0; i < numCourses; i++) {
                graph[i] = new LinkedList<Integer>();
            }
            for (int[] p : prerequisites) {
                int from = p[1], to = p[0];
                graph[from].add(to);
            }
            return graph;
        }

        private void traverse(List<Integer>[] graph, int s) {
            if (onPath[s]) {
                hasCycle = true;
            }
            if (hasCycle || visited[s])
                return;
            visited[s] = true;
            onPath[s] = true;
            for (int t : graph[s]) {
                traverse(graph, t);
            }
            res.add(s);
            onPath[s] = false;
        }
    }


    // 886
    private boolean[] color;
    private boolean[] visited;
    private boolean ok = true;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] graph = buildGraph(n, dislikes);
        color = new boolean[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!visited[i])
                bfs(i, graph);
        }
        return ok;
    }

    private void bfs(int v, List<Integer>[] graph){
        Queue<Integer> q = new LinkedList<>();
        if(!ok) return;
        visited[v] = true;
        q.offer(v);
        while(!q.isEmpty() && ok){
            int curr = q.poll();
            for(int w: graph[curr]){
                if(!visited[w]){
                    color[w] = !color[curr];
                    visited[w] = true;
                    q.offer(w);
                }else{
                    if(color[w] == color[curr]){
                        ok = false;
                        return;
                    }
                }
            }
        }

    }

    private void traverse(int v, List<Integer>[] graph) {
        if (!ok)
            return;
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                color[w] = !color[v];
                traverse(w, graph);
            } else {
                if (color[w] == color[v]) {
                    ok = false;
                    return;
                }
            }
        }
    }

    private List<Integer>[] buildGraph(int n, int[][] dislikes) {
        List<Integer>[] graph = new LinkedList[n + 1];
        for (int i = 0; i <= n; i++)
            graph[i] = new LinkedList<>();
        for (int[] pair : dislikes) {
            int v = pair[0];
            int w = pair[1];
            graph[v].add(w);
            graph[w].add(v);
        }
        return graph;
    }
}
