package com.yunye.code;

/**
 * Created by 李凌耀 on 2017/6/6.
 */
public class PayInfo {
    private int pages;//页面
    private boolean isdouble;//是否双面打印
    private int start;//开始页面
    private int copies;//打印份数
    private int end;//结束页面
    private int multiPages;//一页多打
    private boolean all;//全文打印

    public PayInfo() {
        isdouble = false;
        all = true;
        pages = 0;
        start = 0;
        copies = 1;
        end = 0;
        multiPages = 1;
    }

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isIsdouble() {
        return isdouble;
    }

    public void setIsdouble(boolean isdouble) {
        this.isdouble = isdouble;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getMultiPages() {
        return multiPages;
    }

    public void setMultiPages(int multiPages) {
        this.multiPages = multiPages;
    }

    private static final PayInfo instance = new PayInfo();

    public static PayInfo getInstance()
    {
        return instance;
    }
}
