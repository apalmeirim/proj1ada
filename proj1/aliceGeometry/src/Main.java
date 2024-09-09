import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int numVertex = Integer.parseInt(in.readLine());
        String[] vertexsEdges = in.readLine().split(" ");

        String[] edges = new String[numVertex];
        int[] vertex = new int[numVertex];

        edges[0] = vertexsEdges[0];
        for(int i = 1; i < (numVertex * 2); i++) {
            if (i % 2 == 0) edges[i / 2] = vertexsEdges[i];
            else vertex[((i + 1) / 2) - 1] = Integer.parseInt(vertexsEdges[i]);
        }

        aliceGeometry alice = new aliceGeometry(numVertex, edges, vertex);

        alice.result();

        System.out.println(alice.getBest());

        for(int j = 0; j < alice.getNumBests(); j++) {
            System.out.printf("%d ",alice.getBestIndexs()[j]);
        }

    }

}
