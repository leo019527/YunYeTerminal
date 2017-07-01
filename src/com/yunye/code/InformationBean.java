package com.yunye.code;

import java.io.File;

/**
 * Created by 李凌耀 on 2017/4/24.
 */
public class InformationBean {
    private File bean = null;
    private int outNum;
    private int currentNum;
    private int pages;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }

    private static final InformationBean instance = new InformationBean();

    private InformationBean()
    {
        bean = null;
    }

    public static InformationBean getInformationBean()
    {
        return instance;
    }

    public File getBean() {
        return bean;
    }

    public void setBean(File bean) {
        this.bean = bean;
    }

    public int getOutNum() {
        return outNum;
    }

    public void setOutNum(int outNum) {
        this.outNum = outNum;
    }
}
