package thread.pool;

import com.lile.thread.pool.MySelfThreadPool;
import com.lile.thread.pool.MyTask;
import org.junit.Test;

public class TestMySelfThreadPool {
    private static final int TASK_NUM = 50;//任务的个数
    @Test
    public void test1() {
        MySelfThreadPool myPool = new MySelfThreadPool(3,50);
        for (int i = 0; i < TASK_NUM; i++) {
            myPool.execute(new MyTask("task_"+i));
        }
    }
}
