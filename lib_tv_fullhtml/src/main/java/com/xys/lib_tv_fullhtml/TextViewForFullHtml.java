package com.xys.lib_tv_fullhtml;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TextViewForFullHtml是一个增强的TextView,修改了TextView.fromHtml不能设置
 * 字体大小的问题(原生的只能设置small\normal\big),同时,兼容了ActionScript脚本
 */
public class TextViewForFullHtml extends TextView {

    private static Context sContext;

    public TextViewForFullHtml(Context context) {
        super(context);
        sContext = context;
    }

    private void txtViewSetText(TextView view, CharSequence text, BufferType type) {
        CharSequence t = text;
        if (t instanceof Spannable) {
            int end = text.length();
            Spannable sp = (Spannable) t;
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            if (urls.length > 0) {
                view.setMovementMethod(LinkMovementMethod.getInstance());
                for (URLSpan url : urls) {
                    HJURLSpan myURLSpan = new HJURLSpan(view, url.getURL());
                    style.clearSpans();
                    style.setSpan(myURLSpan, sp.getSpanStart(url),
                            sp.getSpanEnd(url),
                            Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                }
            }
            view.setText(style, type);
        }
    }

    public void loadContent(String content) {
        int gravity = Gravity.NO_GRAVITY;
        content = content.replace("<P", "<d");
        content = content.replace("</P>", "</d><br/>");

        content = ActionscriptTextUtils.parseFontHTML(content);

        String p_regex = "<d ALIGN=\"(LEFT|CENTER|RIGHT)\">";
        Pattern p2 = Pattern.compile(p_regex);
        Matcher m2 = p2.matcher(content);
        String c_str = "left";
        while (m2.find()) {
            c_str = m2.group(1);
            break;
        }
        if (c_str.equalsIgnoreCase("LEFT")) {
            gravity = Gravity.LEFT;
        } else if (c_str.equalsIgnoreCase("CENTER")) {
            gravity = Gravity.CENTER;
        } else if (c_str.equalsIgnoreCase("RIGHT")) {
            gravity = Gravity.RIGHT;
        }
        Spanned spaned = FullHtml.fromHtml(content);
        txtViewSetText(TextViewForFullHtml.this, spaned, BufferType.SPANNABLE);
        setGravity(gravity);
    }

    private static class HJURLSpan extends URLSpan {
        private String mUrl;
        private View mView;

        public HJURLSpan(View view, String url) {
            super(url);
            mView = view;
            mUrl = url;
        }

        @Override
        public void onClick(View widget) {
            Toast.makeText(sContext, mUrl, Toast.LENGTH_SHORT).show();
        }
    }
}
