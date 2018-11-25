package crawler.utils.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import crawler.utils.other.CustomedMethod;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class JsonUtils {
    private static Set<String> pictureUrls = new HashSet<String>();// set of pictures

    public Set<String> getPictureUrls() {
        return pictureUrls;
    }

    //get a normal string by json string
    public static void main(String[] args) {
        JsonUtils jsonUtils = new JsonUtils();
        jsonUtils.getValue();
    }

    public void outputUrl(Set<String> pictureUrls){
        Iterator<String> iterator = pictureUrls.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public String getNext(String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);//将该字符串解析成jsonObject
        String nextUrl = jsonObject.getJSONObject("paging").getString("next");//提取该对象中的data域，
        return  nextUrl;
    }

    public void getImageUrl(String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);//将该字符串解析成jsonObject
        JSONArray array = jsonObject.getJSONArray("data");

        CustomedMethod.printDelimiter(Integer.toString(array.size()));
        for(int i = 0;i < array.size();i++) {
            System.out.println(array.get(i));
            //get all content
            String content = array.getJSONObject(i).getString("content");
            Document document = Jsoup.parse(content);
            //04.根据已知的document，获取目标Elements 元素
            Elements imgElements = document.select("img[src$=.jpg]");//查找扩展名以.jpg 结尾的dom节点

            //05.获取Element节点中的指定属性值。在我这里我就需要获取src的值
            for (Element e : imgElements) {
                String tempUrl = e.attr("src");
                pictureUrls.add(tempUrl);
                // System.out.println("src:"+pictureUrl);
            }
        }
    }

    //judge the answer is in end?
    public boolean isEnd(String jsonString){
        JSONObject jsonObject1 = JSONObject.parseObject(jsonString);//将该字符串解析成jsonObject
        JSONObject jsonObject2 = jsonObject1.getJSONObject("paging");

        //get flag
        String flag = jsonObject2.getString("is_end");
        if (flag.equals("true")) {
            return true;
        }
        return false;
    }

    public void getValue() {
        String jsonString = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"id\": 524816977,\n" +
                "      \"type\": \"answer\",\n" +
                "      \"relationship\": {\n" +
                "        \"is_author\": false,\n" +
                "        \"is_authorized\": false,\n" +
                "        \"is_nothelp\": false,\n" +
                "        \"is_thanked\": false,\n" +
                "        \"voting\": 0,\n" +
                "        \"upvoted_followees\": []\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 524816978,\n" +
                "      \"type\": \"answer\",\n" +
                "      \"relationship\": {\n" +
                "        \"is_author\": true,\n" +
                "        \"is_authorized\": false,\n" +
                "        \"is_nothelp\": false,\n" +
                "        \"is_thanked\": false,\n" +
                "        \"voting\": 10,\n" +
                "        \"upvoted_followees\": []\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        System.out.println("id : "+jsonObject.getJSONObject("data").getJSONObject("id"));
    }
}
