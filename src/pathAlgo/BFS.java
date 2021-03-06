package pathAlgo;

import graph.*;

import java.util.*;

public class BFS {
    private static HashMap<String, Boolean> marked = new HashMap<String,Boolean>();
    private static HashMap<String, String> previous = new HashMap<String,String>();
    private static HashMap<String, Integer> distance = new HashMap<String, Integer>();
    private static String sourceNode;
    public static ArrayList<String> bfs(Graph G, String srcStopName) {

        sourceNode = srcStopName;
        for(String s : G.getMyStops().keySet()){
            marked.put(s, false);
            previous.put(s, "null");
            distance.put(s, Integer.MAX_VALUE);

        }

        ArrayList<String> visitOrder = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Queue<Integer> distanceQueue = new LinkedList<>();

        queue.add(sourceNode);
        marked.put(sourceNode, true);
        distanceQueue.add(0);
        distance.put(sourceNode, 0);
        while(!queue.isEmpty())  {
            String nodeIndex = queue.remove();
            int nodeDistance = distanceQueue.remove();
            visitOrder.add(nodeIndex);
            for (Edges childEdge : G.getMyGraph().get(nodeIndex)){
                String stopName = childEdge.getDst().getstopName();
                if (!marked.get(stopName)){
                    queue.add(stopName);
                    marked.put(stopName, true);
                    previous.put(stopName, nodeIndex);

                    distanceQueue.add(nodeDistance + 1);
                    distance.put(stopName, nodeDistance + 1);
                }
            }
        }
        return visitOrder;
    }

    //get the number of connected components
    public static int cc(Graph G) {
        for (Stops s : G.getMyStops().values()){
            String source = s.getstopName();
        }
        int count = 0;
        for(String s : G.getMyStops().keySet()){
            marked.put(s, false);
        }

        for (String s : G.getMyStops().keySet()){
            if(!marked.get(s)){
                bfs(G,s);
                count++;
            }
        }

        return count;
    }
    
  //get the shortest path to v
  	public static ArrayList<String> getSP(String v) {
        if(!hasPathTo(v)){
            System.out.println("there is no path to stop " + v);
            return null;
        }
  		ArrayList<String> shortestPath = new ArrayList<>();
  		String thisNode = v;
  		while (!thisNode.equals("null")) {
  			shortestPath.add(thisNode);
  			thisNode = previous.get(thisNode);
  			if (thisNode == sourceNode) {
  				shortestPath.add(sourceNode);
  				break;
  			}
  		}
  		Collections.reverse(shortestPath);
  		return shortestPath;
  	}
  	

//
    public static boolean hasPathTo(String stopName){
        return marked.get(stopName);
    }
//
//    public  static int disTo(int v){
//        return distance[v];
//    }
//
//    public static ArrayList<Integer> printSP(int v){
//        ArrayList<Integer> shortestPath = new ArrayList<>();
//        int node = v;
//        while(node > -1){
//            shortestPath.add(node);
//            node = previous[node];
//            if(node == sourceNode){
//                shortestPath.add(sourceNode);
//                break;
//            }
//        }
//        Collections.reverse(shortestPath);
//        return shortestPath;
    }

