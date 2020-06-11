package edu.princetion.execrise.grap;

import java.util.Stack;

public class DirectedCycle {

    private boolean[] marked; // 访问记录

    private int[] edgeTo;      // 记录环的搜索轨迹，最小生成树min(E) = V - 1
                                // 收索轨迹最大为V-1个边，当有环时 最多为V个边

    private Stack<Integer> cycle;  // 有向环中所有的顶点

    private boolean[] onStack; // 第二个访问记录，用于环的检测记录，

    private boolean hasCycle;

    private boolean isFast;
    public DirectedCycle(Graph g, boolean isFast){
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];
        G = g;
        this.isFast = isFast;
        if( !isFast ){
            for(int v = 0; v < g.V(); v++){
               if(!marked[v]) dfs(g, v);
            }
        }


        if(isFast){
            for(int v = 0; v < g.V(); v++){
                if(dfs(v, v)) hasCycle = true;
            }
        }
    }


    private void dfs(Graph g, int v){
        marked[v] = true;
        onStack[v] = true;
        for(int w : g.adj(v)){

            if(hasCycle()) return;

            // 没有访问过，肯定也不是环，记录轨迹，继续递归
            if( !marked[w] ){ edgeTo[w] = v; dfs(g, w); }

            //  有访问过，存在环，记录轨迹，跳出递归；
            else if( onStack[w] ){
                cycle = new Stack<>();
                // 记录构成环的顶点
                for(int x = v; x != w; x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;//从v点收索的没有环，清除v？

    }


    private Graph G;

    private boolean dfs(int v, int parent){
        marked[v] = true;
        for(int w: G.adj(v))
            if(!marked[w]){
                if(dfs(w, v)) return true;
            }
            else if(w != parent) // 最后，自己与自己相等，那么无环，递归最后，结束点与自己不相等，有环
                // 这里还是无向无环图的检测，不适合有向图
                return true;
        return false;
    }

    public boolean hasCycle(){
        if(isFast) return hasCycle;

        else return cycle != null;
    }


    public static void main(String[] args) {
        String path = "E:\\DataStruct\\src\\resource\\g.txt";
        Graph graph = new Graph(path, true);
        DirectedCycle cycle1 = new DirectedCycle(graph, false);
        System.out.println("cycle1 has cycle :" + cycle1.hasCycle());
        DirectedCycle cycle2 = new DirectedCycle(graph, true);
        System.out.println("cycle2 has cycle :" + cycle2.hasCycle());
    }

}
