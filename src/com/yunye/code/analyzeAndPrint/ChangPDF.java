package com.yunye.code.analyzeAndPrint;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

/**
 * Created by 李凌耀 on 2017/6/5.
 */


public class ChangPDF {

    public static void PDFtoJPG() {
        String filePath = "D:\\out.pdf";
        //这里必须new mydocumnet，否则有版权保护水印，MYDOCUMENT中的Init方法通过反射把水印中的内容全部清空了
        MyDocument document = new MyDocument();
        try {
            document.setFile(filePath);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        float scale = 2.5f;// 缩放比例
        float rotation = 0f;// 旋转角度
        long start;
        long end;
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            start = System.currentTimeMillis();
            BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
                    org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                File file = new File("D:\\out\\" + i + ".jpg");
                ImageIO.write(rendImage, "jpg", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
            end = System.currentTimeMillis();
            System.out.println("所用时间："+(end-start)+"毫秒");
        }
        document.dispose();
    }

    public static void PDFtoOneJPG() {
        String filePath = "D:\\out.pdf";
        Document document = new Document();
       document.setFile(filePath);
        float scale = 2.5f;// 缩放比例-可以自己设定
        float rotation = 0f;// 旋转角度这个就是0，不要修改了

        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
                    org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                File file = new File("D:\\out\\" + i + ".jpg");
                ImageIO.write(rendImage, "jpg", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        }
        document.dispose();
    }

    public static void main(String[] args) {
        ChangPDF.PDFtoJPG();
    }
}

