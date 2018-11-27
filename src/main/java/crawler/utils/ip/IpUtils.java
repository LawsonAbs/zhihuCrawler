package crawler.utils.ip;


import crawler.httpClient.HttpClientUtils;
import crawler.result.IPPort;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * 01.ip crawler.utils
 * This method is used to get free but useful ip from internet.
 *
 * 02.the obtained results couldn't use hashMap,because the ip address has nothing to do with the port
 * so use HashSet
 *
 */
public class IpUtils{
    //urls' list
    private List<String> urls = new ArrayList<String>();
    private HttpClientUtils httpClientUtils = new HttpClientUtils();

    public static void main(String[] args) {
        IpUtils ipUtils = new IpUtils();
    }


    //the following function could get free ipPort by select class,but it couldn't get the all efficient ipPort
    @Deprecated
    public void getFreeIpByClass(WebSite webSite,IPPort ipPort) {
        Set<String> ipPortSet = new HashSet<String>();
        //the three website object
        String content = httpClientUtils.getEntityContent(webSite.getUrl(),"utf-8");
        System.out.println("content "+content);
        Document document = Jsoup.parse(content);

        Element ip_list = document.getElementById("ip_list");


        //following only to get the class=odd
        Elements classOdd = ip_list.select(".odd");
        System.out.println("classOdd.size is: "+classOdd.size());
        for (Element ele : classOdd) {
            Elements tdEles = ele.select("td");
            String ip = tdEles.get(1).text();
            String port = tdEles.get(2).text();
            System.out.println("ipPort: " + ip + " port: "+port);
            ipPortSet.add(ip + " " + port);// add to set
        }
        System.out.println(ipPortSet.size());
    }

    //is it a port?
    public boolean isPort(String string) {
        return string.matches("^[1-9]$|(^[1-9][0-9]$)|(^[1-9][0-9][0-9]$)|(^[1-9][0-9][0-9][0-9]$)|(^[1-6][0-5][0-5][0-3][0-5]$)");
    }

    //is it a ip?
    public static boolean checkAddress(String s) {
        return s.matches("((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))");
    }

    public Queue<String> getFreeIpInQueue(WebSite webSite){
        return  null;
    }
}
