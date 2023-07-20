package http;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

@Slf4j
public class DownloadExample {
    public static void main(String[] args) {
        String fileUrl = "https://storage-short-test.bidizhaobiao.com/moose/moose-report-api/project-bidding-guoyi/2023-07-19/%E9%A1%B9%E7%9B%AE%E6%8A%A5%E5%91%8A%28%E6%8B%9B%E6%A0%87%E7%89%88%29_2021%E5%B9%B4%E7%AC%AC%E4%BA%8C%E6%89%B9%E7%A7%91%E6%8A%80%E4%BC%81%E4%B8%9A%E7%A0%94%E5%8F%91%E6%8A%95%E5%85%A5%E6%BF%80%E5%8A%B1%E9%A1%B9%E7%9B%AE.pdf?Expires=1689756958&OSSAccessKeyId=TMP.3Kk9CeabAjR9Sqn5QN3MwtuqP1Tmyc24gQTr4L99qryy3fgEKjkeHgWBsFscS7S438QpWWW9QPssCoD6zcCKF27GS2EiST&Signature=gaORnep2uRnJzUu13Pp7h8glK30%3D";
        test1(fileUrl);
        //test2(fileUrl);
    }

    public static void test2(String fileUrl) {
        String fileContent = HttpUtil.get(fileUrl);
        log.info(fileContent);
    }

    public static void test1(String fileUrl) {
        // 文件的 URL
        try {
            // 创建 URL 对象
            URL url = new URL(fileUrl);
            // 打开连接
            InputStream inputStream = url.openStream();
            // 将输入流转换为 BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // 读取文件内容
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            // 关闭文件流和连接
            reader.close();
            inputStream.close();
            // 将文件内容转换为字符串
            String fileContent = stringBuilder.toString();
            log.info("文件内容：\n" + fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
