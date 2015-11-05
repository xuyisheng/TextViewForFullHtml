package com.xys.textviewforfullhtml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.xys.lib_tv_fullhtml.TextViewForFullHtml;

public class MainActivity extends AppCompatActivity {

    String mContentTextSize = "<TEXTFORMAT LEADING=\"2\"><P ALIGN=\"LEFT\"><FONT FACE=\"Microsoft Yahei,微软雅黑\" SIZE=\"24\" COLOR=\"#333333\" LETTERSPACING=\"0\" KERNING=\"0\"><FONT SIZE=\"40\">我是很大的字……</FONT>我居然比旁边的字小<FONT SIZE=\"14\">我最小...啊啊啊</FONT><FONT SIZE=\"12\">......居然可以设置不同的字体字号</FONT></FONT></P></TEXTFORMAT>";
    String mContentGravityCenter = "<TEXTFORMAT LEADING=\"2\"><P ALIGN=\"CENTER\"><FONT FACE=\"Microsoft Yahei,微软雅黑\" SIZE=\"24\" COLOR=\"#333333\" LETTERSPACING=\"0\" KERNING=\"0\">我先来个居中对齐!</FONT></P></TEXTFORMAT>";
    String mContentGravityRight = "<TEXTFORMAT LEADING=\"2\"><P ALIGN=\"RIGHT\"><FONT FACE=\"Microsoft Yahei,微软雅黑\" SIZE=\"24\" COLOR=\"#333333\" LETTERSPACING=\"0\" KERNING=\"0\">我是来右对齐的!</FONT></P></TEXTFORMAT>";
    String mContentStyle = "<TEXTFORMAT LEADING=\"2\"><P ALIGN=\"LEFT\"><FONT FACE=\"Microsoft Yahei,微软雅黑\" SIZE=\"24\" COLOR=\"#333333\" LETTERSPACING=\"0\" KERNING=\"0\">我可以设置很多不同的字体风格,比如:<B>加粗</B>、<I>斜体</I>、<U>下划线</U>。</FONT></P></TEXTFORMAT>";
    String mContentUrl = "<TEXTFORMAT LEADING=\"2\"><P ALIGN=\"LEFT\"><FONT FACE=\"Microsoft Yahei,微软雅黑\" SIZE=\"24\" COLOR=\"#333333\" LETTERSPACING=\"0\" KERNING=\"0\">我可以设置一个超链接,牛逼吗 <FONT COLOR=\"#0000ff\"><A HREF=\"我是超链接\" TARGET=\"_blank\"><FONT COLOR=\"#6698ff\"><U>快戳我看看</U></FONT></A></FONT></FONT></P></TEXTFORMAT>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
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

        container.addView(textViewTextSize);
        container.addView(textViewGravityCenter);
        container.addView(textViewGravityRight);
        container.addView(textViewStyle);
        container.addView(textViewUrl);
    }
}
