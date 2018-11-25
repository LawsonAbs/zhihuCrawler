package crawler.threadPool;

import crawler.utils.ip.WebSite;

public class ProduceIPPort implements Runnable {
    private WebSite webSite;

    public ProduceIPPort(WebSite webSite){
        this.webSite = webSite;
    }

    public void run() {
        while (true) {// get the IP forever
            webSite.getFreeIpInQueue();
        }
    }
}
