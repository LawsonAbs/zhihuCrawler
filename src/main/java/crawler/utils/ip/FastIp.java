package crawler.utils.ip;

import java.util.Set;

public class FastIp extends WebSite {
    private FastIp fastIp = new FastIp();

    public FastIp() {
        fastIp.setUrl("https://www.kuaidaili.com/free/");
    }

    public Set<String> getFreeIpInSet() {
        return null;
    }

    public void getFreeIpInQueue() {

    }
}
