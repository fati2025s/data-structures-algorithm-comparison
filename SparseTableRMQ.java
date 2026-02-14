public class SparseTableRMQ implements RMQ {
    int[] A;
    int[][] st; // store indices
    int[] log2;


    public void build(int[] A) {
        this.A = A;
        int n = A.length;
        log2 = new int[n + 1];
        for (int i = 2; i <= n; i++) log2[i] = log2[i / 2] + 1;


        int K = log2[n] + 1;
        st = new int[n][K];


        for (int i = 0; i < n; i++) st[i][0] = i;


        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                int x = st[i][j - 1];
                int y = st[i + (1 << (j - 1))][j - 1];
                st[i][j] = (A[x] <= A[y]) ? x : y;
            }
        }
    }

    public int query(int l, int r) {
        int len = r - l + 1;
        int k = log2[len];
        int x = st[l][k];
        int y = st[r - (1 << k) + 1][k];
        return (A[x] <= A[y]) ? x : y;
    }


    public long memory() {
        long mem = 0;
        mem += A.length * 4L;
        mem += log2.length * 4L;
        mem += (long) st.length * st[0].length * 4L;
        return mem;
    }
}
