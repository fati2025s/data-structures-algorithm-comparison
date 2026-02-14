public interface RMQ {
    void build(int[] A);
    int query(int l, int r); // return index of minimum
    long memory();
}
