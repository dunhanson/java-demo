package site.dunhanson.sdk.demo.aliyun;

import com.aliyun.oss.OSS;
import org.apache.commons.io.IOUtils;
import site.dunhanson.sdk.demo.aliyun.utils.AliyunUtils;
import site.dunhanson.sdk.demo.aliyun.utils.OSSUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class OssExample {
    public static void main(String[] args) throws IOException {
        String endpoint = "oss-cn-shanghai.aliyuncs.com";
        String accessKeyId = AliyunUtils.accessKeyId;
        String accessKeySecret = AliyunUtils.accessKeySecret;
        // inputStream
        File file = new File("D:\\Test\\credit_china\\2022-09-27-6332a0948505640001e30bf1.zip");
        InputStream inputStream = Files.newInputStream(file.toPath());
        // OSSConfig
        OSSUtils.OSSConfig ossConfig = new OSSUtils.OSSConfig(endpoint, accessKeyId, accessKeySecret);
        // OSS
        OSS oss = OSSUtils.getOSS(ossConfig);
        // OSSPutContent
        OSSUtils.OSSPutContent content = new OSSUtils.OSSPutContent();
        content.setInputStream(inputStream);
        content.setFileType(OSSUtils.OSSFileType.FILE);
        content.setBucketName("bxkc");
        content.setObjectName(String.format("credit_china/%s", file.getName()));
        // putAndGetUrl
        String putAndGetUrl = OSSUtils.putAndGetUrl(oss, content);
        System.out.println(putAndGetUrl);
    }
}
