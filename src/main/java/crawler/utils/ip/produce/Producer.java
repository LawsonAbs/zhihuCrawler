package crawler.utils.ip.produce;


import crawler.result.IPPort;
import crawler.utils.ip.IpUtils;
import crawler.utils.ip.WebSite;

/**
 * 01.抽象类，代表生产者
 */
public abstract class Producer implements Runnable{
    private volatile IPPort ipPort;// the share ipPort to write
    private WebSite webSite;

    public Producer(WebSite webSite, IPPort ipPort) {
        this.webSite = webSite;
        this.ipPort = ipPort;
    }

    public IPPort getIpPort() {
        return ipPort;
    }

    public void setIpPort(IPPort ipPort) {
        this.ipPort = ipPort;
    }

    public WebSite getWebSite() {
        return webSite;
    }

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }
}
