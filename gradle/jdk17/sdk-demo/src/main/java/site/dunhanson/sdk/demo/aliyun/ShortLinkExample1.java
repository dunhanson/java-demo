package site.dunhanson.sdk.demo.aliyun;

import site.dunhanson.sdk.demo.aliyun.utils.ShortLinkUtils;

public class ShortLinkExample1 {
    public static void main(String[] args_) throws Exception {
        String sourceUrl = "https://bxkc.oss-cn-shanghai.aliyuncs.com/test/test.txt";
        String shortUrlName = "test.txt";
        int effectiveDays = 7;
        String shortUrl = ShortLinkUtils.create(sourceUrl, shortUrlName, effectiveDays);
        System.out.println(shortUrl);
    }

}
