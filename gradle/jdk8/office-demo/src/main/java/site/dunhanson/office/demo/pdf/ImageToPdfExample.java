package site.dunhanson.office.demo.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * pdf例子
 */
public class ImageToPdfExample {

    /**
     * 生成图片
     * @throws IOException IO异常
     */
    public void testManyImageToPDF() throws IOException {
        String imagePath1 = "http://bxkc.oss-cn-shanghai.aliyuncs.com/2022-09-19/1732cb7c923f523d66e03d7c4b0e8427/C01_1663584778467.png";
        String imagePath2 = "http://bxkc.oss-cn-shanghai.aliyuncs.com/2022-09-19/d249f109b5e45cadf6477733ab79c03e/C01_1663584716453.png";
        Image image1 = Image.getInstance(imagePath1);
        Image image2 = Image.getInstance(imagePath2);
        float width = image1.getWidth();
        float height = image1.getHeight();
        String outPath = "D:\\Test\\guangzi\\hello.pdf";
        Document document = new Document();
        Rectangle pageSize = new Rectangle(width, height);
        document.setPageSize(pageSize);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(outPath)));
        document.open();
        pdfWriter.getInfo().put(PdfName.CREATOR, new PdfString(Document.getVersion()));
        document.add(image1);
        document.add(image2);
        document.close();
    }

    /**
     * 生成图片
     * @throws IOException IO异常
     */
    public void testImage() throws IOException {
        String imagePath = "D:\\Test\\guangzi\\A03_1663292971076.png";
        Image image = Image.getInstance(imagePath);
        float width = image.getWidth();
        float height = image.getHeight();
        String outPath = "D:\\Test\\guangzi\\hello.pdf";
        Document document = new Document();
        Rectangle pageSize = new Rectangle(width, height);
        document.setPageSize(pageSize);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(outPath)));
        document.open();
        pdfWriter.getInfo().put(PdfName.CREATOR, new PdfString(Document.getVersion()));
        document.add(image);
        document.close();
    }

    /**
     * 生成pdf
     * @throws FileNotFoundException IO异常
     */
    public void testHello() throws FileNotFoundException {
        String outPath = "D:\\Test\\guangzi\\hello.pdf";
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(outPath));
        document.open();
        pdfWriter.getInfo().put(PdfName.CREATOR, new PdfString(Document.getVersion()));
        document.add(new Paragraph("四川滨锋环保科技有限公司"));
        document.close();
    }

    /**
     * 图片带文字
     */
    public void testImageWithText() throws IOException {
        String imagePath = "D:\\Test\\guangzi\\D01_1663749898688.png";
        Image image = Image.getInstance(imagePath);
        float width = image.getWidth();
        float height = image.getHeight() + 200;
        String outPath = "D:\\Test\\guangzi\\imageWithText.pdf";
        Document document = new Document();
        Rectangle pageSize = new Rectangle(width, height);
        document.setPageSize(pageSize);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(outPath)));
        document.open();
        pdfWriter.getInfo().put(PdfName.CREATOR, new PdfString(Document.getVersion()));
        // document.add(image);
        Paragraph paragraph1 = new Paragraph("头部-111111", getFont());
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        Paragraph paragraph2 = new Paragraph("底部-111111", getFont());
        paragraph2.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph1);
        document.add(image);
        document.add(paragraph2);
        document.close();
    }

    public void testLink() throws IOException {
        String imagePath = "D:\\Test\\guangzi\\A03_1663292971076.png";
        Image image = Image.getInstance(imagePath);
        float width = image.getWidth();
        float height = image.getHeight() + 200;
        String outPath = "D:\\Test\\guangzi\\imageWithText.pdf";
        Document document = new Document();
        Rectangle pageSize = new Rectangle(width, height);
        document.setPageSize(pageSize);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(outPath)));
        document.open();
        pdfWriter.getInfo().put(PdfName.CREATOR, new PdfString(Document.getVersion()));
        // document.add(image);
        Paragraph paragraph1 = new Paragraph("头部-111111", getFont());
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        Paragraph paragraph2 = new Paragraph("底部-111111", getFont());
        paragraph2.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph1);
        document.add(image);
        document.add(paragraph2);
        document.close();
    }

    public Font getFont() throws IOException {
        BaseFont baseFont = BaseFont.createFont("D:\\Test\\guangzi\\PingFang-Medium.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        return new Font(baseFont, 10, Font.NORMAL);
    }

    public static void main(String[] args) throws IOException {
        ImageToPdfExample example = new ImageToPdfExample();
        example.testImageWithText();
    }

}
