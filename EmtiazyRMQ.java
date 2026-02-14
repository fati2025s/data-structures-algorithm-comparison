public class EmtiazyRMQ implements RMQ {
    int[] A;
    int blockSize;
    int[] blockMinIndices;
    SparseTableRMQ st; // استفاده مجدد از کلاس قبلی خودت برای بین بلوک‌ها

    public void build(int[] A) {
        this.A = A;
        int n = A.length;
        // اندازه بلوک را به صورت لگاریتمی انتخاب می‌کنیم (یک انتخاب استاندارد)
        this.blockSize = Math.max(1, (int) (Math.log(n) / Math.log(2)) / 4);
        int numBlocks = (n + blockSize - 1) / blockSize;
        blockMinIndices = new int[numBlocks];

        // 1. پیدا کردن مینیمم هر بلوک
        for (int i = 0; i < numBlocks; i++) {
            int start = i * blockSize;
            int end = Math.min(n, start + blockSize);
            int minIdx = start;
            for (int j = start + 1; j < end; j++) {
                if (A[j] < A[minIdx]) minIdx = j;
            }
            blockMinIndices[i] = minIdx;
        }

        // 2. ساخت Sparse Table روی آرایه "نماینده‌ها" (فقط روی مینیمم بلوک‌ها)
        // این کار حافظه را از O(N log N) به O(N) تبدیل می‌کند
        int[] representativeValues = new int[numBlocks];
        for (int i = 0; i < numBlocks; i++) {
            representativeValues[i] = A[blockMinIndices[i]];
        }
        st = new SparseTableRMQ();
        st.build(representativeValues);
    }

    public int query(int l, int r) {
        int bL = l / blockSize;
        int bR = r / blockSize;

        if (bL == bR) {
            // کوئری داخل یک بلوک (پیمایش خطی چون بلوک خیلی کوچک است)
            return scan(l, r);
        }

        int resIdx = -1;

        // الف) بررسی انتهای ناقص بلوک اول
        int leftRes = scan(l, (bL + 1) * blockSize - 1);
        resIdx = leftRes;

        // ب) بررسی بلوک‌های کامل وسط با استفاده از Sparse Table (زمان O(1))
        if (bL + 1 <= bR - 1) {
            int midBlockResIdxInST = st.query(bL + 1, bR - 1);
            int midRes = blockMinIndices[midBlockResIdxInST];
            if (resIdx == -1 || A[midRes] < A[resIdx]) resIdx = midRes;
        }

        // ج) بررسی ابتدای ناقص بلوک آخر
        int rightRes = scan(bR * blockSize, r);
        if (resIdx == -1 || A[rightRes] < A[resIdx]) resIdx = rightRes;

        return resIdx;
    }

    private int scan(int l, int r) {
        int minIdx = l;
        for (int i = l + 1; i <= r; i++) {
            if (A[i] < A[minIdx]) minIdx = i;
        }
        return minIdx;
    }

    public long memory() {
        // حافظه این روش: آرایه اصلی + آرایه بلوک‌ها + Sparse Table کوچک
        return (A.length * 4L) + (blockMinIndices.length * 4L) + st.memory();
    }
}
