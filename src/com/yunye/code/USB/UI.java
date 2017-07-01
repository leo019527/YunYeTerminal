package com.yunye.code.USB;

import com.yunye.code.InformationBean;
import com.yunye.code.analyzeAndPrint.OfficeAnalyze;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;

/**
 * Created by 李凌耀 on 2017/4/24.
 */
public class UI extends JPanel {


    static final long serialVersionUID = 0l;


    static int LEFT_WIDTH;


    static int RIGHT_WIDTH;


    static int WINDOW_HEIGHT;


    JPanel fileListed = null;


    public UI(JPanel fileListed,File USB) {

        //EmptyBorder eb = new EmptyBorder(1,1,1,1);

//        WINDOW_HEIGHT = (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.749);

        LEFT_WIDTH = (int)(Toolkit.getDefaultToolkit().getScreenSize().width*0.213);
        RIGHT_WIDTH = (int)(Toolkit.getDefaultToolkit().getScreenSize().width*0.213);

        this.fileListed = fileListed;

        setPreferredSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().width*0.426),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.749)));


        setBorder(new BevelBorder(BevelBorder.LOWERED));


        setLayout(new BorderLayout());


        FileList list = new FileList();

        /*-------------------------------------*/
        list.addListSelectionListener(new change());
        /*-------------------------------------*/
        FileTree tree = new FileTree(list,USB);

        tree.setDoubleBuffered(true);

        list.setDoubleBuffered(true);


        JScrollPane treeView = new JScrollPane(tree);

        treeView.setPreferredSize(

                new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().width*0.213),
                        (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.749)));

        JScrollPane listView = new JScrollPane(list);

        listView.setPreferredSize(

                new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().width*0.213),
                        (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.749)));


        JSplitPane pane =

                new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeView,

                        listView);

        pane.setDividerLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().width*0.213));

        pane.setDividerSize(4);

        //pane.setDoubleBuffered(true);


        add(pane);
    }

}

class change implements ListSelectionListener
{
    @Override
    public void valueChanged(ListSelectionEvent e) {
        FileList mylist = (FileList)e.getSource();
        int index = mylist.getSelectedIndex();
        Object elementAt = mylist.getModel().getElementAt(index);
        InformationBean informationBean = InformationBean.getInformationBean();
        if(isCorrectFile(((FolderNode)elementAt).getTheFile()))
            informationBean.setBean(((FolderNode)elementAt).getTheFile());
        else
            informationBean.setBean(null);
    }

    private boolean isCorrectFile(File file)
    {
        String path = file.getPath();
        String split = path.substring(path.indexOf('.') + 1, path.length());
        split = split.toLowerCase();
        switch (split)
        {
            case "doc":
            case "docx":
            case "ppt":
            case "pptx":
            case "pdf":
                return true;
            default:
                return false;
        }
    }
}