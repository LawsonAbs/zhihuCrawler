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
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
