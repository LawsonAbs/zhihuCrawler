package crawler.entrance;

import crawler.httpClient.HttpClientUtils;
import crawler.result.IPPort;
import crawler.utils.ip.CloudIP;
import crawler.utils.ip.Consume.IpConsumer;
import crawler.utils.ip.XiCiIP;
import crawler.utils.ip.produce.IpProducer;
import crawler.utils.json.JsonUtils;
import crawler.utils.other.CustomedMethod;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {
    private String dirPath = "E:\\intellij_Project\\zhihu_picture\\";
    //the url we get picture's url
    //private static List<String> topicUrl = new ArrayList<String>();
    private static String topicUrl = "https://www.zhihu.com/api/v4/questions/28997505/answers?include=data[*].is_normal,admin_closed_comment,reward_info,is_collapsed,annotation_action,annotation_detail,collapse_reason,is_sticky,collapsed_by,suggest_edit,comment_count,can_comment,content,editable_content,voteup_count,reshipment_settings,comment_permission,created_time,updated_time,review_info,relevant_info,question,excerpt,relationship.is_authorized,is_author,voting,is_thanked,is_nothelp,is_labeled;data[*].mark_infos[*].url;data[*].author.follower_count,badge[*].topics&offset=0&limit=4&sort_by=created";
    private static Set<String> pictureUrls = new HashSet<String>();
    //01.CloseableHttpClient is a abstract class => in order to use in all method
    private static CloseableHttpClient httpClient = HttpClients.createDefault();
    private static JsonUtils jsonUtils = new JsonUtils();
    private static HttpClientUtils httpClientUtils = new HttpClientUtils();
    private static String ip = null;
    private static int port = 0;

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

        //get first thread to fetch the ip
        IpProducer cloudIpProducer = new IpProducer(cloudIP,ipPort);
        Thread cloudThread = new Thread(cloudIpProducer);
        cloudThread.start();

        IpProducer xiciIpProducer = new IpProducer(xiCiIP, ipPort);
        Thread xiciThread = new Thread(xiciIpProducer);
        xiciThread.start();

        IpConsumer ipConsumer = new IpConsumer(ipPort);
        Thread ipConsumerThread = new Thread(ipConsumer);
        ipConsumerThread.start();

        Set<String> tempPictureUrls = null;
        //01.通过url获取entity
        boolean flag = false;
        while(!flag) {
            //ensure get correct ip and port -> the ipAndpost
            synchronized (ipConsumer) {
                ip = ipConsumer.getIp();
                port = Integer.parseInt(ipConsumer.getPort());
            }

            CustomedMethod.printDelimiter(topicUrl);
            String jsonString = httpClientUtils.getEntityContentByProxy(topicUrl,ip,port,"utf-8");
            if(jsonString == null) continue;//maybe jsonString is null -> get new ip and port
            tempPictureUrls = httpClientUtils.getImageUrlByJson(jsonString);
            flag = jsonUtils.isEnd(jsonString);

            Iterator<String> iterator = tempPictureUrls.iterator();
            while (iterator.hasNext()) {
                pictureUrls.add(iterator.next()); // add the total set -> pictureUrl
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //print the next url
            topicUrl = jsonUtils.getNext(jsonString);
            System.out.println(pictureUrls.size());
        }

        //print the pictureUrl's size
        System.out.println(pictureUrls.size());

        //pictureUrl is a list,so you must use get() rather than index to fetch a value
        for(String imageUrl : pictureUrls) {
            synchronized (ipConsumer) {
                ip = ipConsumer.getIp();
                port = Integer.parseInt(ipConsumer.getPort());
            }
            //System.out.println(imageUrl);
            httpClientUtils.getPictureByProxy(imageUrl,ip,port);
        }
    }
}
