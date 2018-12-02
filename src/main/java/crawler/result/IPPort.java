package crawler.result;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1.代表爬虫的获取的ip队列
 */
public class IPPort {
    //private volatile Queue<String> ipPortQueue = new LinkedList<String>();
    private Queue<String> ipPortQueue = new LinkedList<String>();
    private int capacity;// the queue's capacity

    public Queue<String> getIpPortQueue() {
        return ipPortQueue;
    }
}
