package site.dunhanson.sdk.demo.aliyun.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.AddShortUrlRequest;
import com.aliyun.dysmsapi20170525.models.AddShortUrlResponse;
import com.aliyun.tea.TeaException;
import lombok.extern.slf4j.Slf4j;

/**
 * 短链接工具类
 * @author dunhanson
 * @since 2022-10-11
 */
@Slf4j
public class ShortLinkUtils {

    /**
     * 创建短链接
     * @param sourceUrl 原链接
     * @param shortUrlName 短链接名称
     * @param effectiveDays 有天数
     * @return 短链接URL
     */
    public static String create(String sourceUrl, String shortUrlName, int effectiveDays) {
        try {
            Client client = AliyunUtils.createClient();
            AddShortUrlRequest addShortUrlRequest = new AddShortUrlRequest()
                    .setSourceUrl(sourceUrl)
                    .setShortUrlName(shortUrlName)
                    .setEffectiveDays(String.valueOf(effectiveDays));
            AddShortUrlResponse response = client.addShortUrl(addShortUrlRequest);
            return response.getBody().getData().getShortUrl();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
