package crawler.entrance;

import crawler.result.IPPort;
import crawler.threadPool.ProduceIPPort;
import crawler.utils.ip.CloudIP;
import crawler.utils.ip.XiCiIP;

public class Main {
    public static void main(String[] args) {
        IPPort ipPort = new IPPort();
        String xiCiurl = "http://www.xicidaili.com/nn/1";
        String xiCiKeyWord = "ip_list";
        String xiCiWebName = "西刺代理";

        XiCiIP xiCiIP = new XiCiIP(ipPort, xiCiurl, xiCiKeyWord, xiCiWebName,"utf-8");

        String cloudUrl = "http://www.ip3366.net/?stype=1&page=1";
        String cloudKeyWord = "list";
        String cloudWebName = "云代理";
        CloudIP cloudIP = new CloudIP(ipPort, cloudUrl,cloudKeyWord,cloudWebName,"gb2312");


        //get first thread
//        ProduceIPPort producer1 = new ProduceIPPort(xiCiIP);
//        producer1.run();

        //get second thread
        ProduceIPPort producer2 = new ProduceIPPort(cloudIP);
        producer2.run();

        synchronized (ipPort) {
            System.out.println(ipPort.getIpPortQueue().size());
            while(ipPort.getIpPortQueue().size() > 0){
                System.out.println(ipPort.getIpPortQueue().poll());
            }
        }
    }
}
