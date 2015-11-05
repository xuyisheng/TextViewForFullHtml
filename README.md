# TextViewForFullHtml

TextViewForFullHtml是对原生TextView解析Html格式文本的增强。

## 原生TextView对Html的支持

原生的TextView同样支持Html的显示，但是Develop Doc里面也写了，并不是支持所有的Html标签，例如，font的size，默认的Android系统只支持small、normal、big三种，但是不支持具体的字号，比如textsize=14这种。

## TextViewForFullHtml

TextViewForFullHtml这个库的目的在于在同一个TextView中给不同的文字设置不同大小的字体，当然，顺便也支持了其他的一些效果，比如对齐方式、字体风格等。

具体可以看示例图：

![](https://github.com/xuyisheng/TextViewForFullHtml/blob/master/art/demo.png)

## 代码使用示例

目前给出的String示例是ActionScript的，Html应该也可以支持（需要具体测试）。

```
String mContentTextSize = "<TEXTFORMAT LEADING=\"2\"><P ALIGN=\"LEFT\"><FONT FACE=\"Microsoft Yahei,微软雅黑\" SIZE=\"24\" COLOR=\"#333333\" LETTERSPACING=\"0\" KERNING=\"0\"><FONT SIZE=\"40\">我是很大的字……</FONT>我居然比旁边的字小<FONT SIZE=\"14\">我最小...啊啊啊</FONT><FONT SIZE=\"12\">......居然可以设置不同的字体字号</FONT></FONT></P></TEXTFORMAT>";
String mContentGravityCenter = "<TEXTFORMAT LEADING=\"2\"><P ALIGN=\"CENTER\"><FONT FACE=\"Microsoft Yahei,微软雅黑\" SIZE=\"24\" COLOR=\"#333333\" LETTERSPACING=\"0\" KERNING=\"0\">我先来个居中对齐!</FONT></P></TEXTFORMAT>";
String mContentGravityRight = "<TEXTFORMAT LEADING=\"2\"><P ALIGN=\"RIGHT\"><FONT FACE=\"Microsoft Yahei,微软雅黑\" SIZE=\"24\" COLOR=\"#333333\" LETTERSPACING=\"0\" KERNING=\"0\">我是来右对齐的!</FONT></P></TEXTFORMAT>";
String mContentStyle = "<TEXTFORMAT LEADING=\"2\"><P ALIGN=\"LEFT\"><FONT FACE=\"Microsoft Yahei,微软雅黑\" SIZE=\"24\" COLOR=\"#333333\" LETTERSPACING=\"0\" KERNING=\"0\">我可以设置很多不同的字体风格,比如:<B>加粗</B>、<I>斜体</I>、<U>下划线</U>。</FONT></P></TEXTFORMAT>";
String mContentUrl = "<TEXTFORMAT LEADING=\"2\"><P ALIGN=\"LEFT\"><FONT FACE=\"Microsoft Yahei,微软雅黑\" SIZE=\"24\" COLOR=\"#333333\" LETTERSPACING=\"0\" KERNING=\"0\">我可以设置一个超链接,牛逼吗 <FONT COLOR=\"#0000ff\"><A HREF=\"我是超链接\" TARGET=\"_blank\"><FONT COLOR=\"#6698ff\"><U>快戳我看看</U></FONT></A></FONT></FONT></P></TEXTFORMAT>";
```

使用方法：

```
// 示例:演示设置不同文字的字体大小
TextViewForFullHtml textViewTextSize = new TextViewForFullHtml(this);
textViewTextSize.loadContent(mContentTextSize);
// 示例:演示设置不同文字的对齐风格——居中
TextViewForFullHtml textViewGravityCenter = new TextViewForFullHtml(this);
textViewGravityCenter.loadContent(mContentGravityCenter);
// 示例:演示设置不同文字的对齐风格——右对齐
TextViewForFullHtml textViewGravityRight = new TextViewForFullHtml(this);
textViewGravityRight.loadContent(mContentGravityRight);
// 示例:演示设置不同文字的字体风格
TextViewForFullHtml textViewStyle = new TextViewForFullHtml(this);
textViewStyle.loadContent(mContentStyle);
// 示例:演示设置不同文字的超链接
TextViewForFullHtml textViewUrl = new TextViewForFullHtml(this);
textViewUrl.loadContent(mContentUrl);
```

使用非常简单，用TextViewForFullHtml替换掉TextView并调用loadContent方法即可。