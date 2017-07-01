package com.yunye.code;


/**
 * Created by 李凌耀 on 2017/6/6.
 */
public class USBDesignInfo {
    private boolean all;//是否全文打印
    private int TextFields;//选中的输入框
    private boolean isDouble;//是否双面打印

    public boolean isDouble() {
        return isDouble;
    }

    public void setDouble(boolean aDouble) {
        isDouble = aDouble;
    }

    public int getTextFields() {
        return TextFields;
    }

    public void setTextFields(int textFields) {
        TextFields = textFields;
    }

    private static final USBDesignInfo instance = new USBDesignInfo();

    private USBDesignInfo() {
        this.all = true;
    }

    public static USBDesignInfo getInstance()
    {
        return instance;
    }

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }
}
