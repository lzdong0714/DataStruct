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


    // TODO 深度优先遍历， 广度优先遍历就可以
    public DirectDFS(Graph graph, int s){
        graph.validate(s);
        marked = new boolean[graph.V()];
        pre = new ArrayList<>(graph.V());
        pos = new ArrayList<>(graph.V());
        dfs(graph, s);
    }

    public List<Integer> getPrePath(){

        return pre;
    }

    public List<Integer> getPostPath(){
        return pos;
    }


    public DirectDFS(Graph graph,  Iterable<Integer> source){

    }

    public boolean marked(int v){
        return marked[v];
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
