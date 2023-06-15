/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojoland;

import java.util.*;

/**
 *
 * @author Xin Jie
 */
class Graph<T extends Comparable<T>, N extends Comparable <N>> {
    Vertex<T,N> head;
    int size;
	
    public Graph(){
        head=null;
        size=0;
    }
    
    public int getDeg(T v){
        if (hasVertex(v)==true){
            Vertex<T,N> temp = head;
            while (temp!=null){
                if ( temp.vertexInfo.compareTo( v ) == 0 )
                    return temp.deg;
                temp=temp.nextVertex;
            }
        }
        return -1;
    }
    
    //for extra feature 4
    //resetting the vertices in case the values of the variables have been changed 
    public void resetVertices() {
        Vertex<T, N> temp = head;
        while (temp != null) {
            temp.setDistance(Integer.MAX_VALUE);
            temp.setVisited(false);
            temp.setShortestPaths(new ArrayList());
            temp = temp.nextVertex;
        }
    } 
    
    public boolean hasVertex(T v){
        if (head==null)
            return false;
        Vertex<T,N> temp = head;
        while (temp!=null){
            if ( temp.vertexInfo.compareTo( v ) == 0 )
                return true;
            temp=temp.nextVertex;
        }
        return false;
    }

    public void addVertex(T v){
        if (hasVertex(v)==false){
            Vertex<T,N> temp=head;
            Vertex<T,N> newVertex = new Vertex<>(v, null);
            if (head==null)   
                head=newVertex;
            else{
                Vertex<T,N> previous=head;
                while (temp!=null){
                    previous=temp;
                    temp=temp.nextVertex;
                }
                previous.nextVertex=newVertex;
            }
            size++;
        }
    }
    
    //for extra feature 4
    //method to get the required vertex
    public Vertex<T, N> getVertex(T v){
        Vertex<T, N> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0) {
                return temp;
            }
            temp = temp.nextVertex;
        }
        return null;
    }
   
    public ArrayList<T> getAllVertexObjects(){
        ArrayList<T> list = new ArrayList<>();
        Vertex<T,N> temp = head;
        while (temp!=null){
            list.add(temp.vertexInfo);
            temp=temp.nextVertex;
        }
        return list;
    }

    public ArrayList<Vertex<T,N>> getAllVertices(){
        ArrayList<Vertex<T,N>> list = new ArrayList<>();
        Vertex<T,N> temp = head;
        while (temp!=null){
            list.add(temp);
            temp=temp.nextVertex;
        }
        return list;
    }
   
    public void addEdge(T source, T destination, N w){
        addVertex(source);
        addVertex(destination);
        Vertex<T,N> sourceVertex = head;
        while (sourceVertex!=null){
            if ( sourceVertex.vertexInfo.compareTo( source ) == 0 ){
                // Reached source vertex, look for destination now
                Vertex<T,N> destinationVertex = head;
                while (destinationVertex!=null){
                    if ( destinationVertex.vertexInfo.compareTo( destination ) == 0 ){
                        // Reached destination vertex, add edge here
                        Edge<T,N> newEdgeOne = new Edge<>(sourceVertex, destinationVertex, w, sourceVertex.firstEdge);
                        Edge<T,N> newEdgeTwo = new Edge<>(destinationVertex, sourceVertex, w, destinationVertex.firstEdge);                  
                        sourceVertex.firstEdge=newEdgeOne;
                        destinationVertex.firstEdge=newEdgeTwo;
                        sourceVertex.deg++;
                        destinationVertex.deg++;
                    }
                    destinationVertex=destinationVertex.nextVertex;
                }
            }
            sourceVertex=sourceVertex.nextVertex;
        }
    }
   
    public boolean hasEdge(T source, T destination){
        if (head==null)
            return false;
        if (!hasVertex(source) || !hasVertex(destination)) 
            return false;
        Vertex<T,N> sourceVertex = head;
        while (sourceVertex!=null){
            if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )  {
                // Reached source vertex, look for destination now 
                Edge<T,N> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null) {
                   if (currentEdge.toVertex.vertexInfo.compareTo(destination)==0) 
                   // destination vertex found 
                        return true;
                   currentEdge=currentEdge.nextEdge;
                }
            }
            sourceVertex=sourceVertex.nextVertex;
        }
        return false;
    }
   
    public N getEdgeWeight(T source, T destination){
        N notFound=null;
        if (head==null)
            return notFound;
        if (!hasVertex(source) || !hasVertex(destination)) 
            return notFound;
        Vertex<T,N> sourceVertex = head;
        while (sourceVertex!=null){
            if ( sourceVertex.vertexInfo.compareTo( source ) == 0 ){
            // Reached source vertex, look for destination now 
                Edge<T,N> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null){
                   if (currentEdge.toVertex.vertexInfo.compareTo(destination)==0) 
                   // destination vertex found 
                        return currentEdge.weight;
                   currentEdge=currentEdge.nextEdge;
                }
            }
            sourceVertex=sourceVertex.nextVertex;
        }
        return notFound;
    }
   
    public ArrayList<T> getNeighbours (T v){
        if (!hasVertex(v))
            return null;
        ArrayList<T> list = new ArrayList<T>();
        Vertex<T,N> temp = head;
        while (temp!=null)	{
            if ( temp.vertexInfo.compareTo( v ) == 0 ){
                // Reached vertex, look for destination now
                Edge<T,N> currentEdge = temp.firstEdge;
                while (currentEdge != null) {
                    list.add(currentEdge.toVertex.vertexInfo);
                    currentEdge=currentEdge.nextEdge;
                }
            }
            temp=temp.nextVertex;
        }
        return list;   
    }
   
    public void printEdges(){
        Vertex<T,N> temp=head;
        while(temp!=null){
            System.out.print("# " + temp.vertexInfo + " : " );
            Edge<T,N> currentEdge = temp.firstEdge;
            while (currentEdge != null){
                System.out.print("[" + temp.vertexInfo + "," + currentEdge.toVertex.vertexInfo +"] " );
                currentEdge=currentEdge.nextEdge;
            }
            System.out.println();
            temp=temp.nextVertex;
        }  
    }
    
    //for extra feature
    public void resetEdgeWeight(T source, T destination, N w){
        Vertex<T,N> sourceVertex = getVertex(source);
        Vertex<T,N> destinationVertex = getVertex(destination);
        Edge<T, N> resetEdge = sourceVertex.firstEdge;
        while(resetEdge!=null){
            if(resetEdge.toVertex.vertexInfo.compareTo(destination) == 0){
                resetEdge.setWeight(w);
            }
            resetEdge = resetEdge.nextEdge;
        }
        Edge<T, N> resetEdgeTwo = destinationVertex.firstEdge;
        while(resetEdgeTwo!=null){
            if(resetEdgeTwo.toVertex.vertexInfo.compareTo(source) == 0){
                resetEdgeTwo.setWeight(w);
                break;
            }
            resetEdgeTwo = resetEdgeTwo.nextEdge;
        }
    }
   
}
