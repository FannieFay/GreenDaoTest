package com.example.testactionbar.view;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.testactionbar.common.Constans;
import com.example.testactionbar.common.Constans.BookTypeUrl;
import com.example.testactionbar.common.Constans.RankUrl;

/**
 * Example program to list links from a URL.
 */
public class ListLinks extends Activity
{
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mTextView = new TextView(this);
        setContentView(mTextView);
        test5();
    }

    public void test1()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Document doc = null;
                try
                {
                    doc = Jsoup.connect("http://www.haohunhun.com").get();
                } catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Elements links = doc.select("a[href]");
                Elements media = doc.select("[src]");
                Elements imports = doc.select("link[href]");

                print("\nMedia: (%d)", media.size());
                for (Element src : media)
                {
                    if (src.tagName().equals("img"))
                        print(" * %s: <%s> %sx%s (%s)", src.tagName(), src.attr("abs:src"),
                                src.attr("width"), src.attr("height"), trim(src.attr("alt"), 20));
                    else
                        print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
                }

                print("\nImports: (%d)", imports.size());
                for (Element link : imports)
                {
                    print(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"),
                            link.attr("rel"));
                }

                print("\nLinks: (%d)", links.size());
                for (Element link : links)
                {
                    print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
                }
            }
        });
        thread.start();
    }

    public void test2()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Document doc = null;
                try
                {
                    doc = Jsoup.connect("http://www.haohunhun.com/top/allvisit/1.html").get();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

                // Elements elementsByClass = doc.getElementsByClass("block");
                Elements elementsByClass = doc.select("div[class=block]:contains(排)");
                Elements elementsLink = elementsByClass.select("a[href]");
                Element lElement = elementsLink.get(0);
                String href = lElement.attr("abs:href");
                String text = lElement.text();
            }
        });
        thread.start();
    }

    public void test3()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Document doc = null;
                try
                {
                    doc = Jsoup.connect(RankUrl.total).get();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                Elements elementsByClass = doc.select("div[class=nav]");
                Elements elementsLink = elementsByClass.select("a[href]");
                if (elementsLink.size() != 0)
                {
                    ArrayList<Type> aTypes = new ArrayList<Type>();
                    for (int i = 0; i < elementsLink.size(); i++)
                    {
                        Type type = new Type();
                        Element lElement = elementsLink.get(i);
                        String href = lElement.attr("abs:href");
                        String text = lElement.text();
                        type.setUrl(href);
                        type.setTypeName(text);
                        aTypes.add(type);
                    }
                }
            }
        });
        thread.start();
    }

    /**
     * 获取书籍基本信息
     */
    public void test4()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Document doc = null;
                try
                {
                    doc = Jsoup.connect(BookTypeUrl.tongren).get();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                Elements elementsByClass = doc.select("div[class=content clearfix]");
                Elements elementsLink = elementsByClass.select("span[class=list-item]");

                if (elementsLink.size() != 0)
                {
                    for (int i = 0; i < elementsLink.size(); i++)
                    {
                        Element lElement = elementsLink.get(i);
                        getBookInfo(lElement);
                    }
                }
            }
        });
        thread.start();
    }

    /**
     * 获取书籍详细信息
     */
    public void test5()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Document doc = null;
                try
                {
                    doc = Jsoup.connect(Constans.url + "b/16792/").get();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
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
                                detailIntroduce.append(((TextNode) object).text().trim() + "\n");
                            }
                        }
                    }
                }
                Elements elements2 = doc.select("div[class=zhangjie clear]");
                Elements elementsChapter = elements2.select("a[href]");

                Element chapterElement = elementsChapter.get(0);
                String text = chapterElement.text();
                String url = chapterElement.attr("abs:href");

                Message message = new Message();
                message.obj = detailIntroduce.toString();
                mHandler.sendMessage(message);
            }
        });
        thread.start();
    }

    Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            mTextView.setText(msg.obj.toString());
        };
    };

    public void getBookInfo(Element lElement)
    {
        String bookName;
        String image;
        String url;
        String introduce;
        String author;
        String newChapter;
        String state;

        Elements urlElements = lElement.select("a[class=bookImg]");
        if (urlElements.size() != 0)
        {
            url = urlElements.attr("abs:href");
            image = urlElements.select("img[onerror]").attr("abs:src");
            bookName = urlElements.select("img[onerror]").attr("alt");
        }

        Elements stateElements = lElement.select("span[class=status-text]");
        if (stateElements.size() != 0)
        {
            state = urlElements.get(0).text();
        }

        author = lElement.select("dt[class=book-item]:contains(作者)").text();
        introduce = lElement.select("dt[class=book-item]:contains(简介)").text();
        newChapter = lElement.select("dt[class=book-item]:contains(最新章节)").text();
    }

    public void getBookDetailInfo(Element lElement)
    {
        String detailIntroduce;
        String detailNewChapter;
    }

    class Type
    {
        private String url;
        private String typeName;

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public String getTypeName()
        {
            return typeName;
        }

        public void setTypeName(String typeName)
        {
            this.typeName = typeName;
        }

    }

    private static void print(String msg, Object... args)
    {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width)
    {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }
}