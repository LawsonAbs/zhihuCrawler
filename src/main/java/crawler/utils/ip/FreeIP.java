package crawler.utils.ip;

import java.util.Queue;
import java.util.Set;

public interface FreeIP {

    /**
     * 01. get Free Ip from and store in set
     */
    Set<String> getFreeIpInSet();


    /**
     * 01. get Free ip and store in queue => write to a class named IPPort
     * @return
     */
    void getFreeIpInQueue();

}
