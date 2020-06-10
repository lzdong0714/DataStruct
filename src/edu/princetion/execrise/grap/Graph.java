package edu.princetion.execrise.grap;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 无权图
 */
public class Graph {

    private int V;
    private int E;
    private TreeSet<Integer>[] adj; // 邻接表，用集合代替数组的形式
    private boolean isDirect;


    public Graph(String fileName, boolean isDirect){
        this.isDirect = isDirect;

        File file = new File(fileName);

        try(Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if(V < 0) throw new IllegalArgumentException("V must be non-negative");
            adj = new TreeSet[V];
            for(int i = 0; i < V; i++){
                adj[i] = new TreeSet<Integer>();
            }

            E = scanner.nextInt();

            if(E < 0 ) throw new IllegalArgumentException("E must be non-negative");
            for(int i = 0; i < E; i++){
                int a = scanner.nextInt();
                validateVertex(a);

                int b = scanner.nextInt();
                validateVertex(b);

                if(a == b) throw new IllegalArgumentException("Self loop is Detected");
                if(adj[a].contains(b)) throw new IllegalArgumentException("Parallel Edges are Detected!");

                adj[a].add(b);
                if( ! isDirect ) adj[b].add(a);
            }
        }catch (IOException e){
                e.printStackTrace();
        }

    }

    public Graph(String fileName){
        this(fileName, false);
    }

    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = new TreeSet[V];
        for(int i = 0; i < V;  i++){
            adj[i] = new TreeSet<>();
        }
    }

    public boolean isDirect() {
        return isDirect;
    }

    public int E() {
        return E;
    }

    public int V() {
        return V;
    }

    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v];
    }

    public void addEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        if(adj[v].contains(w)) return;
        adj[v].add(w);
        E++;
    }

    public void removeEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        if(adj[v].contains(w)) E--;
        adj[v].remove(w);

        if( !isDirect ) adj[w].remove(v);
    }

    public Graph reverse(){
        Graph retGraph = new Graph(V);
        for(int v = 0; v < V; v++ ){
            for(int w : adj[v]){
                retGraph.addEdge(w, v);
            }
        }
        return retGraph;
    }

    private void validateVertex(int v) {
        if( v < 0 || v> V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    public void validate(int v){
        validateVertex(v);
    }


}
