package com.yunye.code.analyzeAndPrint;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by 李凌耀 on 2017/7/2.
 * 将D:\\out.pdf中的图片转换成pdf
 */
public class CreateOutPDF {
    public static void create()
    {
        try {
            File e = new File("D:\\out");
            e.mkdirs();
            File fs = new File("D:\\out.pdf");
            fs.mkdirs();
            Document document = new Document(PageSize.A4.rotate(), 0.0F, 0.0F, 0.0F, 0.0F);
            PdfWriter.getInstance(document, new FileOutputStream(fs));
            document.open();
            File[] var7;
            int var6 = (var7 = e.listFiles()).length;

            for(int var5 = 0; var5 < var6; ++var5) {
                File f = var7[var5];
                Image image = Image.getInstance(f.getAbsolutePath());
                image.setAlignment(1);
                image.scaleAbsolute(1487.0F, 2105.0F);
                document.add(image);
            }

            document.close();
        } catch (FileNotFoundException var9) {
            var9.printStackTrace();
        } catch (DocumentException var10) {
            var10.printStackTrace();
        } catch (MalformedURLException var11) {
            var11.printStackTrace();
        } catch (IOException var12) {
            var12.printStackTrace();
        }
    }
}
