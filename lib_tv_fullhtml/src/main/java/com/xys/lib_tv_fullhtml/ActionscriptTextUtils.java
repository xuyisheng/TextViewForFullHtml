package com.xys.lib_tv_fullhtml;

import android.text.TextUtils;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.ArrayList;

/**
 * utils api for adobe action script/html.
 */
public class ActionscriptTextUtils {

    static boolean hasData = false;

    public static float getScaleYNumber(int h) {
        float f_num = 1f;
        if (h == 1080) {
            //OK
            f_num = 1.2f;
        } else if (h == 480) {
            //OK
            f_num = 0.9375f;
        } else if (h == 540) {
            f_num = 1.05f;
        } else if (h == 800) {
            //ok
            f_num = 1.0888f;
        } else if (h == 720) {
            //OK
            f_num = 1.0788f;
        } else if (h == 320) {
            f_num = 1f;
        }
        return f_num;
    }

    public static float getScaleXNumber(int w) {
        float f_num = 1.04f;
        if (w == 1920) {
            f_num = 1.05f;
        } else if (w == 800) {
            f_num = 1.04888f;
        } else if (w == 960) {
            f_num = 1.088888f;
        } else if (w == 1280) {
            f_num = 1.04f;
        }
        return f_num;
    }

    public static String parseFontHTML(String content) {
        hasData = false;
        Parser parser = Parser.createParser(content, "UTF-8");

        StringBuilder sb = null;
        try {
            NodeList list = (NodeList) parser.parse(null);
            if (hasFont(list)) {
                sb = getNewHtml(list);
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        if (sb == null) {
            return content;
        }
        return sb.toString().replace("</FONT></FONT></FONT>", "</FONT>").replace("</FONT></FONT>", "</FONT>");

    }

    private static boolean hasFont(NodeList list) {
        int count = 0;
        try {
            int len = list.size();
            for (int i = 0; i < len; i++) {
                Node node = list.elementAt(i);
                if (node instanceof ParagraphTag) {
                    NodeList p_list = node.getChildren();
                    count = checkFontTag(p_list);
                } else {
                    if (node instanceof TagNode && ((TagNode) node).getRawTagName().equals("FONT")) {
                        count++;
                    }
                    if (count >= 2) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count >= 2;
    }

    private static int checkFontTag(NodeList list) {
        int count = 0;
        int len = list.size();
        for (int i = 0; i < len; i++) {
            Node node = list.elementAt(i);
            if (node instanceof TagNode && ((TagNode) node).getRawTagName().equals("FONT")) {
                count++;
            }
            if (count >= 2) {
                break;
            }
        }
        return count;
    }

    private static StringBuilder getNewHtml(NodeList list) {
        int level = 0;
        ArrayList<TagNode> fontList = new ArrayList<TagNode>();
        StringBuilder rt = new StringBuilder();
        int defaultSize = 22;
        try {
            for (NodeIterator e = list.elements(); e.hasMoreNodes(); ) {
                Node node = e.nextNode();
                StringBuilder _sbBuilder = null;
                if (node instanceof ParagraphTag) {
                    _sbBuilder = getNewHtml(node.getChildren());
                    if (_sbBuilder != null) {
                        rt.append(_sbBuilder.toString());
                    }
                } else {
                    if (node instanceof TagNode) {
                        TagNode tagNode = (TagNode) node;
                        if (tagNode.getRawTagName().equals("FONT")) {
                            level++;
                            String sizeStr = getFontAttribute("SIZE", tagNode, fontList);
                            String colorStr = getFontAttribute("COLOR", tagNode, fontList);
//                            String sizeStr = tagNode.getAttribute("SIZE");
                            if (sizeStr != null) {
                                int tmpSize = Integer.parseInt(sizeStr);
                                tagNode.setAttribute("SIZE", "" + tmpSize);
                            } else {
                                tagNode.setAttribute("SIZE", "" + defaultSize);
                            }
                            if (colorStr != null) {
                                tagNode.setAttribute("COLOR", colorStr);
                            }
                            fontList.add(tagNode);
                        } else if (tagNode.getRawTagName().equals("/FONT")) {
                            if (level > 0) {
                                fontList.remove(level - 1);
                                level--;
                            }
                        } else {
                            String str = node.toHtml();
                            rt.append(str);
                        }
                    } else if (node instanceof TextNode) {
                        if (level <= 0) {
                            level = 1;
                        }
                        TagNode fontStr = fontList.get((level - 1));
                        String str = node.toHtml();
                        str = clearLastSpace(str);

                        rt.append(fontStr.toHtml()).append(str).append("</FONT>");
                    }
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rt;

    }

    private static String getFontAttribute(String attribute,
                                           TagNode tagNode, ArrayList<TagNode> fontList) {
        String attrStr = tagNode.getAttribute(attribute);
        if (attrStr == null) {
            for (TagNode fontNode : fontList) {
                attrStr = fontNode.getAttribute(attribute);
                if (attrStr != null) {
                    break;
                }
            }
        }
        return attrStr;
    }

    private static String clearLastSpace(String txt) {
        if (TextUtils.isEmpty(txt)) {
            return txt;
        }

        return txt.replaceAll("(&#160;)*$", "");
    }
}
