package com.yunye;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.Sides;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by 李凌耀 on 2017/6/8.
 */
public class test {
    /*
     * 加边框
     */
    private static int[] addMerge(int[] old,int width,int height)
    {
        int Merge = Color.BLACK.getRGB();
        int i,j;
        int[] Mer = new int[(width+2) * (height+2)];
        for(i=0;i<width+2;i++)
        {
            Mer[i] = Merge;
        }
        for(i=1;i<height+1;i++)
        {
            Mer[(width+2)*i] = Merge;
            for(j=1;j<width+1;j++)
            {
                Mer[(width+2)*i+j] = old[(i-1)*width+j-1];
            }
            Mer[(width+2)*(i+1)-1] = Merge;
        }
        for(i=0;i<width+2;i++)
        {
            Mer[(width+2)*(height+1)+i] = Merge;
        }
        return Mer;
    }
    /**
     * @Description:图片拼接 （注意：必须两张图片长宽一致哦）
     * @author:liuyc
     * @time:2016年5月27日 下午5:52:24
     * @param files 要拼接的文件列表
     * @param type  横向拼接， 2 纵向拼接
     */
    public static void mergeImage(String[] files, int type, String targetFile) {
        int len = files.length;
        if (len < 1) {
            throw new RuntimeException("图片数量小于1");
        }
        File[] src = new File[len];
        BufferedImage[] images = new BufferedImage[len];
        int[][] ImageArrays = new int[len][];
        for (int i = 0; i < len; i++) {
            try {
                src[i] = new File(files[i]);
                images[i] = ImageIO.read(src[i]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            int width = images[i].getWidth();
            int height = images[i].getHeight();
            ImageArrays[i] = new int[width * height];
            ImageArrays[i] = images[i].getRGB(0, 0, width, height, ImageArrays[i], 0, width);
        }
        int newHeight = 0;
        int newWidth = 0;
        for (int i = 0; i < images.length; i++) {
            // 横向
            if (type == 1) {
                newHeight = newHeight > images[i].getHeight() ? newHeight : images[i].getHeight();
                newWidth += images[i].getWidth();
            } else if (type == 2) {// 纵向
                newWidth = newWidth > images[i].getWidth() ? newWidth : images[i].getWidth();
                newHeight += images[i].getHeight();
            }
        }
        if (type == 1 && newWidth < 1) {
            return;
        }
        if (type == 2 && newHeight < 1) {
            return;
        }

        // 生成新图片
        try {
            BufferedImage ImageNew = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            int height_i = 0;
            int width_i = 0;
            for (int i = 0; i < images.length; i++) {
                if (type == 1) {
                    ImageNew.setRGB(width_i, 0, images[i].getWidth(), newHeight, ImageArrays[i], 0,
                            images[i].getWidth());
                    width_i += images[i].getWidth();
                } else if (type == 2) {
                    ImageNew.setRGB(0, height_i, newWidth, images[i].getHeight(), ImageArrays[i], 0, newWidth);
                    height_i += images[i].getHeight();
                }
            }
            //输出想要的图片
            ImageIO.write(ImageNew, targetFile.split("\\.")[1], new File(targetFile));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//        String[] files = {"D:\\out\\2.jpg", "D:\\out\\3.jpg"};
//        String targetFile = "D:\\out.jpg";
//        mergeImage(files,1,targetFile);
//    }

    /**
     * @param src
     *            原始图像
     * @return 返回处理后的图像
     */
    public BufferedImage zoomImage(String src) {

        BufferedImage result = null;

        try {
            File srcfile = new File(src);
            if (!srcfile.exists()) {
                System.out.println("文件不存在");

            }
            BufferedImage im = ImageIO.read(srcfile);

			/* 原始图像的宽度和高度 */
            int width = im.getWidth();
            int height = im.getHeight();

            //压缩计算
            float resizeTimes = 0.3f;  /*这个参数是要转化成的倍数,如果是1就是转化成1倍*/

			/* 调整后的图片的宽度和高度 */
            int toWidth = (int) (width * resizeTimes);
            int toHeight = (int) (height * resizeTimes);

			/* 新生成结果图片 */
            result = new BufferedImage(toWidth, toHeight,
                    BufferedImage.TYPE_INT_RGB);

            result.getGraphics().drawImage(
                    im.getScaledInstance(toWidth, toHeight,
                            java.awt.Image.SCALE_SMOOTH), 0, 0, null);


        } catch (Exception e) {
            System.out.println("创建缩略图发生异常" + e.getMessage());
        }

        return result;

    }

    public boolean writeHighQuality(BufferedImage im, String fileFullPath) {
        try {
	            /*输出到文件流*/
            FileOutputStream newimage = new FileOutputStream(fileFullPath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(im);
	            /* 压缩质量 */
            jep.setQuality(0.9f, true);
            encoder.encode(im, jep);
	           /*近JPEG编码*/
            newimage.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


//    public static void main(String[] args) {
//
//        String inputFoler = "D:\\out\\0.jpg" ;
//         /*这儿填写你存放要缩小图片的文件夹全地址*/
//        String outputFolder = "D:\\0.jpg";
//        /*这儿填写你转化后的图片存放的文件夹*/
//
//
//        test test = new test();
//        test.writeHighQuality(test.zoomImage(inputFoler), outputFolder);
//
//    }

//    public static void main(String[] args) {
//
//        //1.得到一个文件的输入流
//        FileInputStream fiStream;
//        try {
//            psStream = new FileInputStream("./sample.PDF");
//        } catch (FileNotFoundException ffne) {
//        }
//        if (psStream == null) {
//            return;
//        }
//
//        //这是要打印文件的格式，如果是PDF文档要设为自动识别
//        DocFlavor fileFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
//        //2.得到要打印的文档类DOC
//        Doc myDoc = new SimpleDoc(fiStream, fileFormat, null);
//        //3.生成一个打印属性设置对象
//        PrintRequestAttributeSet aset =
//                new HashPrintRequestAttributeSet();
//        aset.add(new Copies(5));//Copies-打印份数5份
//        aset.add(MediaSize.A4);//A4纸张
//        aset.add(Sides.DUPLEX);//双面打印
//        //4.关键一步，得到当前机器上所有已经安装的打印机
////传入的参数是文档格式跟打印属性，只有支持这个格式与属性的打印机才会被找到
//        PrintService[] services =
//                PrintServiceLookup.lookupPrintServices(fileFormat, aset);
//        if (services.length > 0)
//
//        {
//            //5.用打印服务生成一个文档打印任务，这里用的是第一个服务
//            //也可以进行筛选，services[i].getName()可以得到打印机名称，可用名称进行比较得到自己想要的打印机
//            DocPrintJob job = services[0].createPrintJob();
//            try {
//                //6.最后一步，执行打印文档任务，传入的参数是Doc文档类，与属性(5份，双面,A4)
//                job.print(myDoc, aset);//成功后电脑会提示已有文档添加到打印队列
//            } catch (PrintException pe) {
//            }
//        }
//
//    }
}
