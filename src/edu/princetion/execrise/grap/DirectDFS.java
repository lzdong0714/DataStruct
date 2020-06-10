package edu.princetion.execrise.grap;


/**
 * 有向图的可达性
 * deep first search 深度优先遍历
 */
public class DirectDFS {

    // TODO 深度优先遍历， 广度优先遍历就可以
    public DirectDFS(Graph graph, int s){
        graph.validate(s);

    }


    public DirectDFS(Graph graph,  Iterable<Integer> source){

    }

    boolean marked(int v){
        return true;
    }
}
