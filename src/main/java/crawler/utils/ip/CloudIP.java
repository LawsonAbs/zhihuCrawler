package crawler.utils.ip;


import crawler.httpClient.HttpClientUtils;
import crawler.result.IPPort;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Set;

public class CloudIP extends WebSite{
    private HttpClientUtils httpClientUtils = new HttpClientUtils();
    IPPort ipPort ;

    public CloudIP(IPPort ipPort, String url, String keyWord, String webName) {
        this.ipPort = ipPort;
        this.setUrl(url);
        this.setKeyword(keyWord);
        this.setWebName(webName);
    }

    public Set<String> getFreeIpInSet() {
        return null;
    }

    public void getFreeIpInQueue() {
        //ipPort port,for example: 13.23.49.128 80
        String content = httpClientUtils.getEntityContent(this.getUrl());
        System.out.println("content "+content);
        Document document = Jsoup.parse(content);

        Element ip_list = document.getElementById(this.getKeyword());

        //get the td
        Elements classEmpty = ip_list.select("tr");
        System.out.println("tr.size is: "+classEmpty.size());

        //default value
        String ip="0.0.0.0";
        String port="8888";

        for (Element trEle : classEmpty) {
            System.out.println(trEle.toString());
            Elements tds = trEle.select("td");

            //if it not a efficient ipPort entry
            if(tds.size()<2)    continue;
            ip = tds.get(1).text();
            port = trEle.select("td").get(2).text();
            System.out.println("ipPort: " + ip + " port: "+port);

            //the synchronized to ipPort
            synchronized (ipPort) {
                ipPort.getIpPortQueue().add(ip + " " + port);// add to set
            }
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setUrl(this.getNextUrl("http://www.ip3366.net/?stype=1&page="));
        System.out.println("after update: "+this.getUrl());
    }
}
