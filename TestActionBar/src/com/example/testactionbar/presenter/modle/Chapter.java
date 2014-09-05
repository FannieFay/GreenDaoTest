package com.example.testactionbar.presenter.modle;

import java.io.Serializable;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class Chapter implements Serializable
{
    private String url;
    private String name;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public static String getChapterContent(Document doc)
    {
        Elements elements = doc.select("span[id=contentbox]");

        Element element = elements.get(0);
        List<Node> list_note = element.childNodes();
        int size = list_note.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++)
        {
            Object object = list_note.get(i);
            object = object.toString().replace("　", "").replace("<br />", "").replace("~~", "")
                    .replace("&nbsp;", "").replace("http://", "").replace("www.uukanshu.com", "")
                    .replace("<p>", "").replace("<p />", "").replace("Www.uukanshu.com", "")
                    .replace("Www.uuKanShu.Com", "").trim();
            if (!object.toString().contains("永久网址") && object.toString().trim().length() != 0)
                stringBuffer.append("　　" + object.toString().trim() + "\n");
        }

        return stringBuffer.toString();
    }
}
