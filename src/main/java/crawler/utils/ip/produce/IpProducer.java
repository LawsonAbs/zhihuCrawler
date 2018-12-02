package crawler.utils.ip.produce;

import crawler.result.IPPort;
import crawler.utils.ip.WebSite;

/**
 * 01. specific Ip producer
 */
public class IpProducer extends Producer {
    public IpProducer(WebSite webSite, IPPort ipPort) {
        super(webSite, ipPort);
    }

    /**
     * 01.the producer don't stop,so you should ensure the list not out of memory
     * 02.keep the consumer consume timely
     *
     */
    public void run() {
        while (true) {// get the IP forever
            this.getWebSite().getFreeIpInQueue();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
