//Importing libraries
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Second {

    private static class FastReader
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// creating thread for inputting
        StringTokenizer st;

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt()
        {
            return Integer.parseInt(next());
        }
    }
    static class City {
        int id;
        int dst;
        public City(int id, int dst) {
            super();
            this.id = id;
            this.dst = dst;
        }

    }
    //function Djkstra to find the shortest distance
    static void Dijkstra(int source, ArrayList<City>[] adjacencyList, int[] dist) {
        PriorityQueue<City> pq = new PriorityQueue<>(new Comparator<City>() {

            @Override
            //function that compares costs of roads between cities
            public int compare(City o1, City o2) {
                // TODO Auto-generated method stub
                return o1.dst - o2.dst;
            }
        });
        pq.add(new City(source,0));//adding new city in queue
        dist[source] = 0;
        while(!pq.isEmpty()) {
            City top = pq.poll();//top is deleted top element of queue
            if(top.id == destination) break; //the trick here
            for(City neighbour: adjacencyList[top.id]) {
                int dst_new = neighbour.dst + top.dst;
                if(dst_new < dist[neighbour.id]) {
                    pq.add(new City(neighbour.id, dst_new));
                    dist[neighbour.id] = dst_new;
                }
            }
        }
    }
    static int destination;

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        int s = fr.nextInt();
        for(int tc = 0; tc < s; tc ++) {
            //Solving here
            Map<String, Integer> cityDirectory = new HashMap<>();
            int n = fr.nextInt();
            ArrayList<City>[] adjacencyList = new ArrayList[n+1];
            for(int i = 1; i <= n; i++ ) adjacencyList[i] = new ArrayList<>();
            for(int i = 0; i < n; i++) { // for each city
                String name = fr.next();
                cityDirectory.put(name, i+1);
                int edges = fr.nextInt();
                for(int j = 0; j < edges; j++) { // for each edge
                    int id = fr.nextInt();
                    int w = fr.nextInt();
                    adjacencyList[i+1].add(new City(id,w));
                }
            }
            int r = fr.nextInt();
            for(int i = 0; i < r; i ++) {
                String src = fr.next();
                String des = fr.next();
                int srcIndex = cityDirectory.get(src);
                int desIndex = cityDirectory.get(des);
                destination = desIndex;
                int[] dist = new int[n+1];
                Arrays.fill(dist, Integer.MAX_VALUE);
                Dijkstra(srcIndex, adjacencyList, dist);
                System.out.println(dist[desIndex]);
            }
        }

    }

}