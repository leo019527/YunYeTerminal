yunye.exe
打印程序，支持doc、docx、ppt、pptx

运行环境要求：.Net 4.5.2 （以上）、 Microsoft Office 2013（以上）

使用默认打印机

打印doc或docx格式：

输入参数：
文档路径 页码（字符串，例如“1-12”打印一到十二页，或者“2,5-6”打印第二页和第五到六页） 一页多打（支持参数：1,2,4,6）是否双面（0为单面，1为双面）

输入样例：
yunye G:\VS2015\yunye\yunye\1.docx 1-20 2 0

打印ppt或pptx格式：

输入参数：
文档路径 起始页码 最终页码

输入样例：
yunye G:\VS2015\yunye\yunye\1.pptx 1 20