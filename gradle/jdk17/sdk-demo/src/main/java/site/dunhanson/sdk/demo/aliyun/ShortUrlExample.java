package site.dunhanson.sdk.demo.aliyun;

import com.aliyun.dysmsapi20170525.models.AddShortUrlResponse;
import com.aliyun.tea.TeaException;

public class ShortUrlExample {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId key
     * @param accessKeySecret secret
     * @return Client
     * @throws Exception 异常
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        com.aliyun.dysmsapi20170525.Client client = ShortUrlExample.createClient(AliyunUtils.accessKeyId, AliyunUtils.accessKeySecret);
        com.aliyun.dysmsapi20170525.models.AddShortUrlRequest addShortUrlRequest = new com.aliyun.dysmsapi20170525.models.AddShortUrlRequest()
                .setSourceUrl("https://attachment-hub.oss-cn-hangzhou.aliyuncs.com/0000/credit_china/2022-09-27-6332a0948505640001e30bf1.zip?Expires=1665422886&OSSAccessKeyId=TMP.3Ke9PAiKD5piWSkaRXMHDjJoqZQoEidej58W1s2YgsvZCpPubAZ7srbbp2e3xWRuWMPYscZv1y8RagyTZKFKTXkR4mw74J&Signature=wDsMkXSM8qW1SiYLsRVCvgbP4Tg%3D")
                .setShortUrlName("阿里短链测试")
                .setEffectiveDays("7");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            AddShortUrlResponse response = client.addShortUrlWithOptions(addShortUrlRequest, runtime);
            System.out.println(response.getBody().getData().getShortUrl());
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }

}
