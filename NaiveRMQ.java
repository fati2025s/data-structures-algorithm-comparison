public class NaiveRMQ implements RMQ {
    int[] A;


    public void build(int[] A) {
        this.A = A;
    }


    public int query(int l, int r) {
        int idx = l;
        int min = A[l];
        for (int i = l + 1; i <= r; i++) {
            if (A[i] < min) {
                min = A[i];
                idx = i;
            }
        }
        return idx;
    }


    public long memory() {
        return A.length * 4L;
    }
}
