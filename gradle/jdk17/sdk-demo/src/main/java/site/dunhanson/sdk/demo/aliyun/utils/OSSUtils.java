package site.dunhanson.sdk.demo.aliyun.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * OSS工具类
 * @author dunhanson
 * @since 2022/3/3
 */
@Slf4j
public class OSSUtils {

    @Data
    public static class OSSPutContent {
        /**
         * 存储空间名称
         */
        private String bucketName;
        /**
         * 存储对象名称
         */
        private String objectName;
        /**
         * 文件流
         */
        private InputStream inputStream;
        /**
         * 访问链接的过期时间（时间戳）
         */
        private LocalDateTime expire;
        /**
         * uuid
         */
        private String uuid;
        /**
         * 文件类型
         */
        private OSSFileType fileType;
    }

    @Data
    @AllArgsConstructor
    public static class OSSConfig {
        /**
         * 访问域名
         */
        private String endpoint;
        /**
         * 账号
         */
        private String accessKeyId;
        /**
         * 密码
         */
        private String accessKeySecret;
    }

    public enum OSSFileType {
        /**
         * 文件
         */
        FILE,
        /**
         * URL链接
         */
        URL,
        /**
         * 字符串
         */
        STR,
        /**
         * base64二进制字符串
         */
        BYTES
    }

    /**
     * 文件上传
     * @param oss OSS
     * @param content 上传内容
     */
    public static void put(OSS oss, OSSPutContent content) {
        oss.putObject(content.getBucketName(), content.getObjectName(), content.getInputStream());
    }

    /**
     * 上传并返回URL链接
     * @param oss OSS
     * @param content 上传内容
     * @return 访问链接
     */
    public static String putAndGetUrl(OSS oss, OSSPutContent content) {
        String bucketName = content.getBucketName();
        String objectName = content.getObjectName();
        LocalDateTime expire = content.getExpire();
        put(oss, content);
        if(exist(oss, bucketName, objectName)) {
            // 上传成功，返回访问链接
            return generateUrl(oss, bucketName, objectName, expire);
        }
        return null;
    }


    /**
     * 文件是否存在
     * @param oss OSS
     * @param bucketName 存储空间名称
     * @param objectName 存储对象名称
     * @return boolean
     */
    public static boolean exist(OSS oss, String bucketName, String objectName) {
        return oss.doesObjectExist(bucketName, objectName);
    }

    /**
     * 生成签名链接
     * @param oss OSS
     * @param bucketName 存储空间名称
     * @param objectName 存储对象名称
     * @return 带签名的URL
     */
    public static String generatePreSignedUrl(OSS oss, String bucketName, String objectName) {
        // 默认过期时间设置为1小时
        LocalDateTime expire  = LocalDateTime.now().plusHours(1);
        return generatePreSignedUrl(oss, bucketName, objectName, expire);
    }

    /**
     * 生成签名链接
     * @param bucketName 存储空间名称
     * @param objectName 存储对象名称
     * @param expire 过期时间
     * @return 带签名的URL
     */
    public static String generatePreSignedUrl(OSS oss, String bucketName, String objectName, LocalDateTime expire) {
        long epochMilli = expire.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return oss.generatePresignedUrl(bucketName, objectName, new Date(epochMilli)).toString();
    }

    /**
     * 生成公用读URL
     * @param oss OSS
     * @param bucketName 存储空间名称
     * @param objectName 存储对象名称
     * @return String
     */
    public static String generatePublicUrl(OSS oss, String bucketName, String objectName) {
        String endpoint = oss.getBucketInfo(bucketName).getBucket().getExtranetEndpoint();
        return String.format("https://%s.%s/%s", bucketName, endpoint, objectName);
    }

    /**
     * 生成访问链接
     * @param oss OSS
     * @param bucketName 存储空间名称
     * @param objectName 存储对象名称
     * @return 访问链接
     */
    public static String generateUrl(OSS oss, String bucketName, String objectName, LocalDateTime expire) {
        CannedAccessControlList cannedACL = oss.getBucketInfo(bucketName).getCannedACL();
        if(cannedACL == CannedAccessControlList.Private) {
            // 私有读
            return generatePreSignedUrl(oss, bucketName, objectName, expire);
        }
        // 公有读
        return generatePublicUrl(oss, bucketName, objectName);
    }

    /**
     * 获取新文件名
     * @param filename 文件名
     * @return 新文件名
     */
    public static String getNewFileName(String filename) {
        return new Date().getTime() + getSuffix(filename);
    }


    /**
     * 获取文件后缀名
     * @param filename 文件名
     * @return 后缀名
     */
    public static String getSuffix(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

    /**
     * 获取OSS client
     * @param config 配置文件
     * @return OSS
     */
    public static OSS getOSS(OSSConfig config) {
        return new OSSClientBuilder().build(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());
    }

}
