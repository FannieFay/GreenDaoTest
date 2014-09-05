package com.example.testactionbar.presenter.modle;

import java.io.Serializable;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.example.testactionbar.common.Constans;

public class BookInfo implements Serializable
{
    String bookName;// 书名
    String image; // 图片地址
    String url; // 书籍地址
    String introduce;// 简介
    String author;// 作者
    String newChapter;// 最新章节名称
    String state; // 状态 完结 连载
    String detailIntroduce;// 详细简介
    String detailNewChapter;// 详细的最新章节
    ArrayList<Chapter> characters;// 章节列表

    /**
     * 获取书本信息
     * 
     * @param doc
     * @return
     */
    public static BookInfoExpand getBookInfoByType(Document doc)
    {
        BookInfoExpand bookInfoExpand = new BookInfoExpand();
        ArrayList<BookInfo> arrayList = new ArrayList<BookInfo>();
        Elements elementsByClass = doc.select("div[class=content clearfix]");
        Elements elementsLink = elementsByClass.select("span[class=list-item]");

        Elements elementsByPage = doc.select("div[class=page][id=page]");
        Elements elementsByPages = elementsByPage.select("a[href]");
        int pageSize = elementsByPages.size();
        Element maxElement = elementsByPages.get(pageSize - 1);
        String maxHref = Constans.url + maxElement.attr("href");

        String string[] = maxHref.split("-");
        int maxIndex = Integer.parseInt(string[1].replace(".html", ""));

        if (elementsLink.size() != 0)
        {
            for (int i = 0; i < elementsLink.size(); i++)
            {
                Element lElement = elementsLink.get(i);
                arrayList.add(getBookInfo(lElement));
            }
        }
        bookInfoExpand.setaBookInfos(arrayList);
        bookInfoExpand.setMaxIndex(maxIndex);
        return bookInfoExpand;
    }

    private static BookInfo getBookInfo(Element lElement)
    {
        BookInfo bookInfo = new BookInfo();

        Elements urlElements = lElement.select("a[class=bookImg]");
        if (urlElements.size() != 0)
        {
            bookInfo.setUrl(Constans.url + urlElements.attr("href"));
            bookInfo.setImage(Constans.url + urlElements.select("img[onerror]").attr("src"));
            bookInfo.setBookName(urlElements.select("img[onerror]").attr("alt"));
        }

        Elements stateElements = lElement.select("span[class=status-text]");
        if (stateElements.size() != 0)
        {
            bookInfo.setState(urlElements.get(0).text());
        }

        bookInfo.setAuthor(lElement.select("dt[class=book-item]:contains(作者)").text());
        bookInfo.setIntroduce(lElement.select("dt[class=book-item]:contains(简介)").text());
        bookInfo.setNewChapter(lElement.select("dt[class=book-item]:contains(最新章节)").text());
        return bookInfo;
    }

    /**
     * 获取详细的书籍信息
     * 
     * @param bookInfo
     *            章节列表信息
     * @param doc
     *            简介详情
     * @return
     */
    public static BookInfo getDetailbookInfo(BookInfo bookInfo, Document doc)
    {
        Elements elements1 = doc.select("dd[class=jieshao_content]");
        Elements detailIntroduceElements = elements1.select("h3:contains(简介)");

        StringBuffer detailIntroduce = new StringBuffer();
        if (detailIntroduceElements.size() != 0)
        {
            for (int i = 0; i < detailIntroduceElements.get(0).childNodes().size(); i++)
            {
                Object object = detailIntroduceElements.get(0).childNodes().get(i);
                if (object instanceof TextNode)
                {
                    if (!((TextNode) object).text().contains("－－－－"))
                    {
                        detailIntroduce.append(((TextNode) object).text().replace("http://", "")
                                .replace("www.uukanshu.com", "").replace("Www.uukanshu.com", "")
                                .replace("Www.uuKanShu.Com", "").trim()
                                + "\n");
                    }
                }
            }
        }

        bookInfo.setDetailIntroduce(detailIntroduce.toString());
        Elements elements2 = doc.select("div[class=zhangjie clear]");
        Elements elements3 = elements2.select("ul[id=chapterList]");
        Elements elementsChapter = elements3.select("a[href]");

        ArrayList<Chapter> chapters = new ArrayList<Chapter>();
        for (int i = 0; i < elementsChapter.size(); i++)
        {
            Chapter chapter = new Chapter();
            Element chapterElement = elementsChapter.get(i);
            String text = chapterElement.text();
            String url = Constans.url + chapterElement.attr("href");
            chapter.setName(text);
            chapter.setUrl(url);
            chapters.add(chapter);
        }
        bookInfo.setCharacters(chapters);
        return bookInfo;
    }

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getIntroduce()
    {
        return introduce;
    }

    public void setIntroduce(String introduce)
    {
        this.introduce = introduce;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getNewChapter()
    {
        return newChapter;
    }

    public void setNewChapter(String newChapter)
    {
        this.newChapter = newChapter;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getDetailIntroduce()
    {
        return detailIntroduce;
    }

    public void setDetailIntroduce(String detailIntroduce)
    {
        this.detailIntroduce = detailIntroduce;
    }

    public String getDetailNewChapter()
    {
        return detailNewChapter;
    }

    public void setDetailNewChapter(String detailNewChapter)
    {
        this.detailNewChapter = detailNewChapter;
    }

    public ArrayList<Chapter> getCharacters()
    {
        return characters;
    }

    public void setCharacters(ArrayList<Chapter> characters)
    {
        this.characters = characters;
    }

}
