public class TestDemo {
    public static void main(String[] args) {
        String str = "https://attachment-hub.oss-cn-hangzhou.aliyuncs.com/credit_china/2022-09-27-6332a0948505640001e30bf1.zip?Expires=1666857293&OSSAccessKeyId=TMP.3Kk3BpTDadhrCxGCh7hP9fJmwY8YFkdQXN8tA6oert4CYFamQE6VKWciuwxLjUiNyKjXBWRnaewbS6QAw2f9a8eDs8yGR1&Signature=%2F2T0B85w6PlCvs2Om0ohl5NAZDY%3D";
        int index1 = str.lastIndexOf("/");
        int index2 = str.lastIndexOf("?");
        System.out.println(str.substring(index1 + 1, index2));
    }
}
