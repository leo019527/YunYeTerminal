package com.yunye.code.analyzeAndPrint;

import com.yunye.code.InformationBean;
import com.yunye.code.PayInfo;
import org.apache.pdfbox.PrintPDF;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by 李凌耀 on 2017/4/26.
 */
public class OfficeAnalyze {
    private int usedPages;

    public void setUsedPages(int usedPages) {
        this.usedPages = usedPages;
    }

    private JLabel remainingPaper;
    private int initPaper;

    public void setRemainingPaper(JLabel remainingPaper) {
        this.remainingPaper = remainingPaper;
        initPaper = 500;
    }

    private File file = null;
    private int pages;
    private int type;

    private static final OfficeAnalyze instance = new OfficeAnalyze();

    private OfficeAnalyze(){}

    public static OfficeAnalyze getInstance()
    {
        return instance;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    //    public OfficeAnalyze(File file) {
//        this.file = file;
//    }

    public int getPages(){
        String path = file.getPath();
        String split = path.substring(path.indexOf('.') + 1, path.length());
        split = split.toLowerCase();
        Runtime mt = Runtime.getRuntime();
        String comm = "";
        if(split != "pdf") {
            comm = "E:\\lib\\OfficeToPDF.exe " + file.getPath() + " D:\\out.pdf";
            System.out.println(comm);
        }
        else
        {
            comm = "copy" + file.getPath() + " D:\\out.pdf";
            System.out.println(comm);
        }
        System.out.println("开始获取文件页数");

        try {
            switch (split) {
                case "doc":
                    WordExtractor doc;
                    doc = new WordExtractor(new FileInputStream(file.getPath()));
                    pages = doc.getSummaryInformation().getPageCount();
                    Process process = mt.exec(comm);

                    try {
                        process.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("文章共"+pages+"页");
                    type = 1;
                    InformationBean.getInformationBean().setPages(pages);
                    System.out.println("文件转换pdf开始");
                    ChangPDF.PDFtoJPG();
                    return pages;
                case "docx":
                    XWPFDocument docx = new XWPFDocument(POIXMLDocument.openPackage(file.getPath()));
                    pages = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
                    Process process2 = mt.exec(comm);
                    try {
                        process2.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("文章共"+pages+"页");
                    type = 1;
                    InformationBean.getInformationBean().setPages(pages);
                    System.out.println("文件转换pdf开始");
                    ChangPDF.PDFtoJPG();
                    return pages;
                case "pdf":
                    PDDocument pdf = PDDocument.load(file);
                    pages = pdf.getNumberOfPages();
                    Process process3 = mt.exec(comm);
                    try {
                        process3.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("文章共"+pages+"页");
                    type = 2;
                    InformationBean.getInformationBean().setPages(pages);
                    System.out.println("文件转换pdf开始");
                    ChangPDF.PDFtoJPG();
                    return pages;
                case "ppt":
                    try {
                        HSLFSlideShow ppt = new HSLFSlideShow(new FileInputStream("D:\\1.ppt"));
                        List<HSLFSlide> page = ppt.getSlides();
                        pages = page.size();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("文章共"+pages+"页");
                    type = 3;
                    InformationBean.getInformationBean().setPages(pages);
                    System.out.println("文件转换pdf开始");
                    ChangPDF.PDFtoJPG();
                    return pages;
                case "pptx":
                    try {
                        XMLSlideShow xmlSlideShow = new XMLSlideShow(new FileInputStream("D:\\1.pptx"));
                        List<XSLFSlide> page = xmlSlideShow.getSlides();
                        pages = page.size();
                        System.out.println(pages);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("文章共"+pages+"页");
                    type = 3;
                    InformationBean.getInformationBean().setPages(pages);
                    System.out.println("文件转换pdf开始");
                    ChangPDF.PDFtoJPG();
                    return pages;
                default:
                    System.out.println("输入文件类型错误");
                    return -1;
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void print()
    {
        String path2 = file.getPath();
        String split = path2.substring(path2.indexOf('.') + 1, path2.length());
        split = split.toLowerCase();
        if(split == "doc" || split == "docx")
        {
            type = 1;
        }
        else if(split == "ppt" || split == "pptx")
        {
            type = 3;
        }
        else if(split == "pdf")
        {
            type = 2;
        }
        String command;
        Runtime runtime = Runtime.getRuntime();
//        try {
//            PrintPDF.main(new String[]{"-silentPrint","D:\\out.pdf"});
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String path = "lib/yunye.exe";
        String name = file.getPath();
        PayInfo payInfo = PayInfo.getInstance();
        switch (type)
        {
            case 1:
                if (payInfo.isAll()) {
                    command = path + " " + name + " 1-" + pages + " "+ payInfo.getMultiPages() +" " + (payInfo.isIsdouble() ? 1:0);
                    System.out.println(command);

                    for (int a=0;a<payInfo.getCopies();a++) {
                        try {
                            runtime.exec(command);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    int d = payInfo.isIsdouble() ? 1 : 0;
                    command = path + " " + name+ " " + payInfo.getStart() + "-" + payInfo.getEnd() + " "+payInfo.getMultiPages()+" "+d;
                    System.out.println(command);
                    for(int a=0;a<payInfo.getCopies();a++) {
                        try {
                            runtime.exec(command);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("doc打印开始");
                break;
            case 3:
                if (payInfo.isAll()) {
                    command = path + " " + name + " 1 " + pages;
                    System.out.println(command);
                    for(int a=0;a<payInfo.getCopies();a++) {
                        try {
                            runtime.exec(command);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    command = path + " " + name+ " " + payInfo.getStart() + "-" + payInfo.getEnd();
                    System.out.println(command);
                    for(int a=0;a<payInfo.getCopies();a++) {
                        try {
                            runtime.exec(command);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("ppt打印开始");
                break;
            case 2:
                for(int a=0;a<payInfo.getCopies();a++) {
                    try {
                        PrintPDF.main(new String[]{"-silentPrint", file.getPath()});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("pdf打印开始");
                break;
            default:
                break;
        }
        this.initPaper -= this.usedPages;
        this.remainingPaper.setText("剩余纸张："+ initPaper +" 张");
    }
}
