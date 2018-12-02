package crawler.utils.ip.Consume;

import crawler.result.IPPort;
import crawler.utils.ip.IpUtils;

public abstract class Consumer implements  Runnable{
    private volatile IPPort ipPort;// very important
    private IpUtils ipUtils = new IpUtils();

    public IpUtils getIpUtils() {
        return ipUtils;
    }

    public void setIpUtils(IpUtils ipUtils) {
        this.ipUtils = ipUtils;
    }

    public Consumer(IPPort ipPort){
        this.ipPort = ipPort;
    }

    public IPPort getIpPort() {
        return ipPort;
    }

    public void setIpPort(IPPort ipPort) {
        this.ipPort = ipPort;
    }
}
