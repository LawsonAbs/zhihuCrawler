package crawler.result;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1.代表爬虫的获取的ip队列
 */
public class IPPort {
    //01.volatile 修饰
    private volatile Queue<String> ipPortQueue = new LinkedList<String>();

    public Queue<String> getIpPortQueue() {
        return ipPortQueue;
    }
}
