public class SegmentTreeRMQ implements RMQ{
    int[] A;
    int[] tree; // store indices
    int n;


    public void build(int[] A) {
        this.A = A;
        this.n = A.length;
        tree = new int[4 * n];
        build(1, 0, n - 1);
    }


    private void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = l;
            return;
        }
        int mid = (l + r) / 2;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
        int x = tree[node * 2];
        int y = tree[node * 2 + 1];
        tree[node] = (A[x] <= A[y]) ? x : y;
    }


    public int query(int l, int r) {
        return query(1, 0, n - 1, l, r);
    }


    private int query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) return tree[node];
        int mid = (l + r) / 2;


        if (qr <= mid) return query(node * 2, l, mid, ql, qr);
        if (ql > mid) return query(node * 2 + 1, mid + 1, r, ql, qr);


        int x = query(node * 2, l, mid, ql, qr);
        int y = query(node * 2 + 1, mid + 1, r, ql, qr);
        return (A[x] <= A[y]) ? x : y;
    }


    public long memory() {
        return A.length * 4L + tree.length * 4L;
    }
}
