import java.util.Random;


public class Main {
    static Random rnd = new Random(1);

    static int[] generateArray(int n) {
        int[] A = new int[n];
        for (int i = 0; i < n; i++)
            A[i] = rnd.nextInt(1_000_000_001);
        return A;
    }


    static int[][] generateQueries(int q, int n) {
        int[][] res = new int[q][2];
        for (int i = 0; i < q; i++) {
            int l = rnd.nextInt(n);
            int r = rnd.nextInt(n);
            if (l > r) { int t = l; l = r; r = t; }
            res[i][0] = l;
            res[i][1] = r;
        }
        return res;
    }


    static long runQueries(RMQ rmq, int[][] queries) {
        long start = System.nanoTime();
        for (int[] q : queries)
            rmq.query(q[0], q[1]);
        return System.nanoTime() - start;
    }

    public static void main(String[] args) {


        int[] sizes = {1000, 3000, 10000, 30000, 100000};
        int Q = 20000;


        System.out.println("n,method,buildTime(ns),queryTime(ns),memory(bytes)");


        for (int n : sizes) {


            int[] A = generateArray(n);
            int[][] queries = generateQueries(Q, n);


            RMQ[] methods = {
                    new NaiveRMQ(),
                    new SparseTableRMQ(),
                    new SegmentTreeRMQ(),
                    new BlockRMQ(),
                    new EmtiazyRMQ()
            };


            String[] names = {"Naive", "SparseTable", "SegmentTree", "Block", "Emtiazy"};


            for (int i = 0; i < methods.length; i++) {
                RMQ rmq = methods[i];


                long t1 = System.nanoTime();
                rmq.build(A);
                long buildTime = System.nanoTime() - t1;


                long queryTime = runQueries(rmq, queries);


                long mem = rmq.memory();


                System.out.println(n + "," + names[i] + "," + buildTime + "," + queryTime + "," + mem);
            }
        }
    }
}