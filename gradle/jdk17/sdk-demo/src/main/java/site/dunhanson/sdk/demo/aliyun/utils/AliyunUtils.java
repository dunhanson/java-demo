package site.dunhanson.sdk.demo.aliyun.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;

/**
 * 阿里云工具类
 * @author dunhanson
 * @since 2022-10-11
 */
public class AliyunUtils {
    /**
     * Key
     */
    public static final String accessKeyId = "******";
    /**
     * Secret
     */
    public static final String accessKeySecret = "******";

    /**
     * 获取Client
     * @return Client
     * @throws Exception 异常
     */
    public static Client createClient() throws Exception {
        Config config = new com.aliyun.teaopenapi.models.Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }
}
