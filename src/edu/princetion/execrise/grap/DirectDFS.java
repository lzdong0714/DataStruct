package edu.princetion.execrise.grap;


import java.util.ArrayList;
import java.util.List;

/**
 * 有向图的可达性
 * deep first search 深度优先遍历
 */
public class DirectDFS {

    private boolean[] marked;

    private int count;

    private ArrayList<Integer> pre;
    private ArrayList<Integer> pos;



    public DirectDFS(Graph g){
        marked = new boolean[g.V()];
        for(int v = 0; v < g.V(); v++){
            if( !marked[v] )
                dfs(g, v);
        }
    }

    private void dfs(Graph g, int v){
        marked[v] = true;
        count ++;
        //前序路径
        pre.add(v);
        for(int w : g.adj(v)){
            if( !marked[w] ){
                dfs(g, w); // 递归
            }
        }
        // 后序路径
        pos.add(v);
    }

    /**
     * graph 中找到s所有可以到达的顶点
     * @param graph
     * @param s
     */
    public DirectDFS(Graph graph, int s){
        graph.validate(s);
        marked = new boolean[graph.V()];
        pre = new ArrayList<>(graph.V());
        pos = new ArrayList<>(graph.V());
        dfs(graph, s);
    }

    /**
     * graph 中找到所有source出发可以到达的顶点
    * @param graph
     * @param source
     */
    public DirectDFS(Graph graph,  Iterable<Integer> source){
        marked = new boolean[graph.V()];
        for(int v : source){
            if( !marked[v] ){
                dfs(graph, v);
            }
        }

    }

    public boolean marked(int v){
        return marked[v];
    }


    public List<Integer> getPrePath(){

        return pre;
    }

    public List<Integer> getPostPath(){
        return pos;
    }


    public static void main(String[] args) {
        String path = "D:\\myproject\\DataStruct\\src\\resource\\ug.txt";
        Graph graph = new Graph(path, true);
        DirectDFS directDFS = new DirectDFS(graph, 3);
        directDFS.getPostPath().forEach(item->{
            System.out.print(item + ": -> ");
        });

        System.out.println("===============");
        directDFS.getPrePath().forEach(item->{
            System.out.print(item  + ": ->");
        });
    }


}
