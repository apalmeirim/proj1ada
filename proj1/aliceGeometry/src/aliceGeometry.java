import java.util.Random;

public class aliceGeometry {

    private int numVertex;
    private String[] edges;
    private int[] vertexs;
    private long[][][] matrix;
    private int[] bestsIndex;
    private long best;
    private int numBests;

    public aliceGeometry(int numVertex, String[] edges, int[] vertexs){// vetor vertexs é defenida temporariamente
        this.numVertex = numVertex;
        this.edges = edges;
        this.vertexs = vertexs;
        this.matrix = new long[numVertex][numVertex][2]; // max posicao 0
        this.bestsIndex = new int[numVertex];
        this.numBests = 0;
        this.best = Long.MIN_VALUE;

    }

    public long getBest(){
        return this.best;
    }
    public int getNumBests(){
        return this.numBests;
    }
    public int[] getBestIndexs(){
       return bestsIndex;
    }

    public void result() {
        for(int i = 0; i < numVertex; i++) {
            long result = calculate();
            if (result > best) {
                best = result;
                bestsIndex = new int[numVertex];
                numBests = 0;
                bestsIndex[numBests++] = i + 1;
            }
            else if (result == best) bestsIndex[numBests++] = i + 1;
            shuffleEdges();
            shuffleVertex();
            this.matrix = new long[numVertex][numVertex][2];
        }
    }

    private long calculate(){
        //caso base
        for(int i = 0; i < numVertex; i++) {
            matrix[i][i] = new long[]{vertexs[i], vertexs[i]};
        }
        //preencher matriz
        for(int d = 1; d <= numVertex; d++) {
            for(int j = 0; j < numVertex - d; j++){
                int k = j + d;
                matrix[j][k][0] = calculateExpression(edges[j + 1], matrix[j][j][0], matrix[j + 1][k][0]);
                matrix[j][k][1] = calculateExpression(edges[j + 1], matrix[j][j][1], matrix[j + 1][k][1]);
                //for, valores intermedios
                for(int l = j + 1; l < k; l++){
                    long value1 = calculateExpression(edges[l + 1], matrix[j][l][0], matrix[l + 1][k][0]);
                    long value2 = calculateExpression(edges[l + 1], matrix[j][l][1], matrix[l + 1][k][1]);
                    long value3 = calculateExpression(edges[l + 1], matrix[j][l][0], matrix[l + 1][k][1]);
                    long value4 = calculateExpression(edges[l + 1], matrix[j][l][1], matrix[l + 1][k][0]);
                    long valueMax = Math.max(Math.max(value1, value2), Math.max(value3, value4));
                    long valueMin = Math.min(Math.min(value1, value2), Math.min(value3, value4));
                    if(valueMax > matrix[j][k][0]) matrix[j][k][0] = valueMax;
                    if(valueMin < matrix[j][k][1]) matrix[j][k][1] = valueMin;
                }
            }
        }
        return matrix[0][numVertex - 1][0];
    }

    private void shuffleVertex(){
        for(int i = 0; i < vertexs.length - 1; i++) {
            int temp = vertexs[i];
            vertexs[i] = vertexs[i + 1];
            vertexs[i + 1] = temp;
        }
    }

    private void shuffleEdges() {
        for(int i = 0; i < edges.length - 1; i++) {
            String temp = edges[i];
            edges[i] = edges[i + 1];
            edges[i + 1] = temp;
        }
    }

    private long calculateExpression(String operation, long num1, long num2) {
        if (operation.equals("+")) {
            return num1 + num2;
        }
        else return num1 * num2;
    }

}
