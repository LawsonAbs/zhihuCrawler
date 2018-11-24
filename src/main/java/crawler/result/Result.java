package crawler.result;

/**
 * 1.代表爬虫的获取结果
 */
public class Result {
    long accessNumber;
    int score;
    int ranking ;

    //construct one instance
    public Result(long accessNumber,int score,int ranking){
        this.accessNumber = accessNumber;
        this.score = score;
        this.ranking = ranking;
    }
}
