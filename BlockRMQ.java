public class BlockRMQ implements RMQ {
    int[] A;
    int[] blockMinIndex;
    int blockSize;
    int blocks;


    public void build(int[] A) {
        this.A = A;
        int n = A.length;
        blockSize = (int) Math.sqrt(n) + 1;
        blocks = (n + blockSize - 1) / blockSize;
        blockMinIndex = new int[blocks];


        for (int b = 0; b < blocks; b++) {
            int start = b * blockSize;
            int end = Math.min(n, start + blockSize);
            int idx = start;
            for (int i = start + 1; i < end; i++)
                if (A[i] < A[idx]) idx = i;
            blockMinIndex[b] = idx;
        }
    }


    public int query(int l, int r) {
        int minIdx = l;


        while (l <= r && l % blockSize != 0) {
            if (A[l] < A[minIdx]) minIdx = l;
            l++;
        }


        while (l + blockSize - 1 <= r) {
            int b = l / blockSize;
            int idx = blockMinIndex[b];
            if (A[idx] < A[minIdx]) minIdx = idx;
            l += blockSize;
        }


        while (l <= r) {
            if (A[l] < A[minIdx]) minIdx = l;
            l++;
        }


        return minIdx;
    }

    public long memory() {
        return A.length * 4L + blockMinIndex.length * 4L;
    }
}
