package utils.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.CustomedMethod;

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
        //System.out.println("next Url: "+getNext(jsonString));
        //System.out.println("content: "+ getContent(jsonString));
        String jsonString = "{\"data\":[{\"id\":25866777,\"type\":\"answer\",\"question\":{\"type\":\"question\",\"id\":23776164,\"title\":\"哪张风景照片让你产生了「此生一定要去一次」的想法？\",\"question_type\":\"normal\",\"created\":1400115498,\"updated_time\":1400631650,\"url\":\"https://www.zhihu.com/api/v4/questions/23776164\",\"relationship\":{}},\"author\":{\"id\":\"19d0cc1033bcf7d2292cb096be91eace\",\"url_token\":\"li-jia-10\",\"name\":\"黎佳\",\"avatar_url\":\"https://pic2.zhimg.com/ccfb61741_is.jpg\",\"avatar_url_template\":\"https://pic2.zhimg.com/ccfb61741_{size}.jpg\",\"is_org\":false,\"type\":\"people\",\"url\":\"https://www.zhihu.com/api/v4/people/19d0cc1033bcf7d2292cb096be91eace\",\"user_type\":\"people\",\"headline\":\"不想当厨子的裁缝不是好司机\",\"badge\":[],\"gender\":1,\"is_advertiser\":false,\"is_followed\":false,\"is_privacy\":false},\"url\":\"https://www.zhihu.com/api/v4/answers/25866777\",\"thumbnail\":\"https://pic1.zhimg.com/8c06b785ee29555fd747acaf0729b1bf_200x112.jpg\",\"is_collapsed\":false,\"created_time\":1400647995,\"updated_time\":1400757884,\"extras\":\"\",\"is_copyable\":true,\"is_normal\":true,\"voteup_count\":11,\"comment_count\":5,\"is_sticky\":false,\"admin_closed_comment\":false,\"comment_permission\":\"all\",\"can_comment\":{\"reason\":\"\",\"status\":true},\"reshipment_settings\":\"allowed\",\"content\":\"因为威斯特法伦球场，所以多特蒙德。\\u003cfigure\\u003e\\u003cnoscript\\u003e\\u003cimg src=\\\"https://pic4.zhimg.com/8c06b785ee29555fd747acaf0729b1bf_b.jpg\\\" data-rawwidth=\\\"640\\\" data-rawheight=\\\"360\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"640\\\" data-original=\\\"https://pic4.zhimg.com/8c06b785ee29555fd747acaf0729b1bf_r.jpg\\\"\\u003e\\u003c/noscript\\u003e\\u003cimg src=\\\"data:image/svg+xml;utf8,\\u0026lt;svg%20xmlns='http://www.w3.org/2000/svg'%20width='640'%20height='360'\\u0026gt;\\u0026lt;/svg\\u0026gt;\\\" data-rawwidth=\\\"640\\\" data-rawheight=\\\"360\\\" class=\\\"origin_image zh-lightbox-thumb lazy\\\" width=\\\"640\\\" data-original=\\\"https://pic4.zhimg.com/8c06b785ee29555fd747acaf0729b1bf_r.jpg\\\" data-actualsrc=\\\"https://pic4.zhimg.com/8c06b785ee29555fd747acaf0729b1bf_b.jpg\\\"\\u003e\\u003c/figure\\u003e\\u003cfigure\\u003e\\u003cnoscript\\u003e\\u003cimg src=\\\"https://pic4.zhimg.com/ab8093203f76d425b382158f1af72cfb_b.jpg\\\" data-rawwidth=\\\"550\\\" data-rawheight=\\\"367\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"550\\\" data-original=\\\"https://pic4.zhimg.com/ab8093203f76d425b382158f1af72cfb_r.jpg\\\"\\u003e\\u003c/noscript\\u003e\\u003cimg src=\\\"data:image/svg+xml;utf8,\\u0026lt;svg%20xmlns='http://www.w3.org/2000/svg'%20width='550'%20height='367'\\u0026gt;\\u0026lt;/svg\\u0026gt;\\\" data-rawwidth=\\\"550\\\" data-rawheight=\\\"367\\\" class=\\\"origin_image zh-lightbox-thumb lazy\\\" width=\\\"550\\\" data-original=\\\"https://pic4.zhimg.com/ab8093203f76d425b382158f1af72cfb_r.jpg\\\" data-actualsrc=\\\"https://pic4.zhimg.com/ab8093203f76d425b382158f1af72cfb_b.jpg\\\"\\u003e\\u003c/figure\\u003e\\u003cfigure\\u003e\\u003cnoscript\\u003e\\u003cimg src=\\\"https://pic2.zhimg.com/ef05467611c05176d8bad5a0b0c1d511_b.jpg\\\" data-rawwidth=\\\"723\\\" data-rawheight=\\\"1024\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"723\\\" data-original=\\\"https://pic2.zhimg.com/ef05467611c05176d8bad5a0b0c1d511_r.jpg\\\"\\u003e\\u003c/noscript\\u003e\\u003cimg src=\\\"data:image/svg+xml;utf8,\\u0026lt;svg%20xmlns='http://www.w3.org/2000/svg'%20width='723'%20height='1024'\\u0026gt;\\u0026lt;/svg\\u0026gt;\\\" data-rawwidth=\\\"723\\\" data-rawheight=\\\"1024\\\" class=\\\"origin_image zh-lightbox-thumb lazy\\\" width=\\\"723\\\" data-original=\\\"https://pic2.zhimg.com/ef05467611c05176d8bad5a0b0c1d511_r.jpg\\\" data-actualsrc=\\\"https://pic2.zhimg.com/ef05467611c05176d8bad5a0b0c1d511_b.jpg\\\"\\u003e\\u003c/figure\\u003e\",\"editable_content\":\"\",\"excerpt\":\"因为威斯特法伦球场，所以多特蒙德。\",\"collapsed_by\":\"nobody\",\"collapse_reason\":\"\",\"annotation_action\":[],\"relevant_info\":{\"is_relevant\":false,\"relevant_type\":\"\",\"relevant_text\":\"\"},\"suggest_edit\":{\"reason\":\"\",\"status\":false,\"tip\":\"\",\"title\":\"\",\"unnormal_details\":{\"status\":\"\",\"description\":\"\",\"reason\":\"\",\"reason_id\":0,\"note\":\"\"},\"url\":\"\"},\"is_labeled\":false,\"reward_info\":{\"can_open_reward\":false,\"is_rewardable\":false,\"reward_member_count\":0,\"reward_total_money\":0,\"tagline\":\"\"},\"relationship\":{\"is_author\":false,\"is_authorized\":false,\"is_nothelp\":false,\"is_thanked\":false,\"voting\":0,\"upvoted_followees\":[]}},{\"id\":25866522,\"type\":\"answer\",\"question\":{\"type\":\"question\",\"id\":23776164,\"title\":\"哪张风景照片让你产生了「此生一定要去一次」的想法？\",\"question_type\":\"normal\",\"created\":1400115498,\"updated_time\":1400631650,\"url\":\"https://www.zhihu.com/api/v4/questions/23776164\",\"relationship\":{}},\"author\":{\"id\":\"8c58bd4e3dc16d5e17c2be723dccb0b6\",\"url_token\":\"\",\"name\":\"知乎用户\",\"avatar_url\":\"https://pic4.zhimg.com/da8e974dc_is.jpg\",\"avatar_url_template\":\"https://pic4.zhimg.com/da8e974dc_{size}.jpg\",\"is_org\":false,\"type\":\"people\",\"url\":\"https://www.zhihu.com/api/v4/people/0\",\"user_type\":\"people\",\"headline\":\"\",\"badge\":[],\"gender\":0,\"is_advertiser\":false,\"is_followed\":false,\"is_privacy\":true},\"url\":\"https://www.zhihu.com/api/v4/answers/25866522\",\"thumbnail\":\"https://pic3.zhimg.com/f0b98f75b75b2b727d043680e8486a12_200x112.jpg\",\"is_collapsed\":false,\"created_time\":1400647535,\"updated_time\":1400685407,\"extras\":\"\",\"is_copyable\":true,\"is_normal\":true,\"voteup_count\":21,\"comment_count\":8,\"is_sticky\":false,\"admin_closed_comment\":false,\"comment_permission\":\"all\",\"can_comment\":{\"reason\":\"\",\"status\":true},\"reshipment_settings\":\"allowed\",\"content\":\"\\u003cb\\u003e有生之年系列\\u003c/b\\u003e\\u003cbr\\u003e\\u003cbr\\u003e湘南海岸\\u003cfigure\\u003e\\u003cnoscript\\u003e\\u003cimg data-rawheight=\\\"759\\\" data-rawwidth=\\\"1256\\\" src=\\\"https://pic3.zhimg.com/f0b98f75b75b2b727d043680e8486a12_b.jpg\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1256\\\" data-original=\\\"https://pic3.zhimg.com/f0b98f75b75b2b727d043680e8486a12_r.jpg\\\"\\u003e\\u003c/noscript\\u003e\\u003cimg data-rawheight=\\\"759\\\" data-rawwidth=\\\"1256\\\" src=\\\"data:image/svg+xml;utf8,\\u0026lt;svg%20xmlns='http://www.w3.org/2000/svg'%20width='1256'%20height='759'\\u0026gt;\\u0026lt;/svg\\u0026gt;\\\" class=\\\"origin_image zh-lightbox-thumb lazy\\\" width=\\\"1256\\\" data-original=\\\"https://pic3.zhimg.com/f0b98f75b75b2b727d043680e8486a12_r.jpg\\\" data-actualsrc=\\\"https://pic3.zhimg.com/f0b98f75b75b2b727d043680e8486a12_b.jpg\\\"\\u003e\\u003c/figure\\u003e\\u003ca class=\\\" wrap external\\\" href=\\\"https://link.zhihu.com/?target=http%3A//tuchong.com/242947/4176167/\\\" target=\\\"_blank\\\" rel=\\\"nofollow noreferrer\\\"\\u003e神奈川県，湘南海岸①\\u003c/a\\u003e\\u003cbr\\u003e东京都立武藏野北高校\\u003ca class=\\\" wrap external\\\" href=\\\"https://link.zhihu.com/?target=http%3A//tieba.baidu.com/p/560261401\\\" target=\\\"_blank\\\" rel=\\\"nofollow noreferrer\\\"\\u003e SLAMDUNK原型，湘北高校--东京都立武野北高校\\u003c/a\\u003e\\u003cbr\\u003e镰仓高校\\u003cbr\\u003e\\u003cbr\\u003e图片都贴不上\\u003cbr\\u003e贴一个游记：\\u003ca href=\\\"http://zhuanlan.zhihu.com/lvxing/19721423\\\" class=\\\"internal\\\"\\u003e\\u003cspan class=\\\"invisible\\\"\\u003ehttp://\\u003c/span\\u003e\\u003cspan class=\\\"visible\\\"\\u003ezhuanlan.zhihu.com/lvxi\\u003c/span\\u003e\\u003cspan class=\\\"invisible\\\"\\u003eng/19721423\\u003c/span\\u003e\\u003cspan class=\\\"ellipsis\\\"\\u003e\\u003c/span\\u003e\\u003c/a\\u003e\",\"editable_content\":\"\",\"excerpt\":\"\\u003cb\\u003e有生之年系列\\u003c/b\\u003e 湘南海岸\\u003ca class=\\\" wrap external\\\" href=\\\"https://link.zhihu.com/?target=http%3A//tuchong.com/242947/4176167/\\\" target=\\\"_blank\\\" rel=\\\"nofollow noreferrer\\\"\\u003e神奈川県，湘南海岸①\\u003c/a\\u003e 东京都立武藏野北高校\\u003ca class=\\\" wrap external\\\" href=\\\"https://link.zhihu.com/?target=http%3A//tieba.baidu.com/p/560261401\\\" target=\\\"_blank\\\" rel=\\\"nofollow noreferrer\\\"\\u003e SLAMDUNK原型，湘北高校--东京都立武野北高校\\u003c/a\\u003e 镰仓高校 图片都贴不上 贴一个游记：\\u003ca href=\\\"http://zhuanlan.zhihu.com/lvxing/19721423\\\" class=\\\"internal\\\"\\u003e\\u003cspan class=\\\"invisible\\\"\\u003ehttp://\\u003c/span\\u003e\\u003cspan class=\\\"visible\\\"\\u003ezhuanlan.zhihu.com/lvxi\\u003c/span\\u003e\\u003cspan class=\\\"invisible\\\"\\u003eng/19721423\\u003c/span\\u003e\\u003cspan class=\\\"ellipsis\\\"\\u003e\\u003c/span\\u003e\\u003c/a\\u003e\",\"collapsed_by\":\"nobody\",\"collapse_reason\":\"\",\"annotation_action\":[],\"relevant_info\":{\"is_relevant\":false,\"relevant_type\":\"\",\"relevant_text\":\"\"},\"suggest_edit\":{\"reason\":\"\",\"status\":false,\"tip\":\"\",\"title\":\"\",\"unnormal_details\":{\"status\":\"\",\"description\":\"\",\"reason\":\"\",\"reason_id\":0,\"note\":\"\"},\"url\":\"\"},\"is_labeled\":false,\"reward_info\":{\"can_open_reward\":false,\"is_rewardable\":false,\"reward_member_count\":0,\"reward_total_money\":0,\"tagline\":\"\"},\"relationship\":{\"is_author\":false,\"is_authorized\":false,\"is_nothelp\":false,\"is_thanked\":false,\"voting\":0,\"upvoted_followees\":[]}}],\"paging\":{\"is_end\":true,\"is_start\":false,\"next\":\"https://www.zhihu.com/api/v4/questions/23776164/answers?data%5B%2A%5D.author.follower_count%2Cbadge%5B%2A%5D.topics=\\u0026data%5B%2A%5D.mark_infos%5B%2A%5D.url=\\u0026include=data%5B%2A%5D.is_normal%2Cadmin_closed_comment%2Creward_info%2Cis_collapsed%2Cannotation_action%2Cannotation_detail%2Ccollapse_reason%2Cis_sticky%2Ccollapsed_by%2Csuggest_edit%2Ccomment_count%2Ccan_comment%2Ccontent%2Ceditable_content%2Cvoteup_count%2Creshipment_settings%2Ccomment_permission%2Ccreated_time%2Cupdated_time%2Creview_info%2Crelevant_info%2Cquestion%2Cexcerpt%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%2Cis_labeled\\u0026limit=2\\u0026offset=210\\u0026sort_by=created\",\"previous\":\"https://www.zhihu.com/api/v4/questions/23776164/answers?data%5B%2A%5D.author.follower_count%2Cbadge%5B%2A%5D.topics=\\u0026data%5B%2A%5D.mark_infos%5B%2A%5D.url=\\u0026include=data%5B%2A%5D.is_normal%2Cadmin_closed_comment%2Creward_info%2Cis_collapsed%2Cannotation_action%2Cannotation_detail%2Ccollapse_reason%2Cis_sticky%2Ccollapsed_by%2Csuggest_edit%2Ccomment_count%2Ccan_comment%2Ccontent%2Ceditable_content%2Cvoteup_count%2Creshipment_settings%2Ccomment_permission%2Ccreated_time%2Cupdated_time%2Creview_info%2Crelevant_info%2Cquestion%2Cexcerpt%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%2Cis_labeled\\u0026limit=2\\u0026offset=206\\u0026sort_by=created\",\"totals\":250}}\n";
        System.out.println(jsonUtils.isEnd(jsonString));
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
}
