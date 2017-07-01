package com.yunye.code.analyzeAndPrint;

import com.yunye.code.PayInfo;

/**
 * Created by 李凌耀 on 2017/6/4.
 */
public class Price {
    public static double getPrice()
    {
        PayInfo instance = PayInfo.getInstance();
        int pages=0;
        double paice;
        if(instance.isAll())
        {
            System.out.println("全文打印");
            if(instance.isIsdouble())
            {
                System.out.println("双面打印");
                pages = instance.getPages();
                pages = (pages+instance.getMultiPages()-1)/instance.getMultiPages();
                pages *= instance.getCopies();
                System.out.println("打印"+instance.getCopies()+"份");
                pages = (pages+1)/2;
                paice = (double)pages * 0.2;
                OfficeAnalyze.getInstance().setUsedPages(pages);
            }
            else
            {
                System.out.println("单面打印");
                pages = instance.getPages();
                pages = (pages+instance.getMultiPages()-1)/instance.getMultiPages();
                pages *= instance.getCopies();
                System.out.println("打印"+instance.getCopies()+"份");
                paice = (double)pages * 0.2;
                OfficeAnalyze.getInstance().setUsedPages(pages);
            }
        }
        else
        {
            System.out.println("非全文打印");
            if(instance.isIsdouble())
            {
                System.out.println("双面打印");
                pages = instance.getEnd()-instance.getStart();
                System.out.println("从"+instance.getStart()+"开始到"+instance.getEnd()+"页");
                pages = (pages+instance.getMultiPages()-1)/instance.getMultiPages();
                pages *= instance.getCopies();
                System.out.println("打印"+instance.getCopies()+"份");
                pages = (pages+1)/2;
                paice = (double)pages * 0.2;
                OfficeAnalyze.getInstance().setUsedPages(pages);
            }
            else
            {
                System.out.println("单面打印");
                pages = instance.getEnd()-instance.getStart();
                System.out.println("从"+instance.getStart()+"开始到"+instance.getEnd()+"页");
                pages = (pages+instance.getMultiPages()-1)/instance.getMultiPages();
                pages *= instance.getCopies();
                System.out.println("打印"+instance.getCopies()+"份");
                paice = (double)pages * 0.2;
                OfficeAnalyze.getInstance().setUsedPages(pages);
            }
        }
        System.out.println("共需支付:" + paice + "元");
        return paice;
    }
}
