package com.example.testactionbar.modle;

import java.util.ArrayList;

import com.example.testactionbar.common.Constans.BookTypeName;
import com.example.testactionbar.common.Constans.BookTypeUrl;

public class BookCategory
{

    private String bookUrl;
    private String bookName;

    public BookCategory(String bookUrl, String bookName)
    {
        this.bookUrl = bookUrl;
        this.bookName = bookName;
    }

    public static ArrayList<BookCategory> getBookTypes()
    {
        ArrayList<BookCategory> arrayList = new ArrayList<BookCategory>();
        BookCategory bookType_xuanhuan = new BookCategory(BookTypeUrl.xuanhuan,
                BookTypeName.xuanhuan);
        BookCategory bookType_yanqing = new BookCategory(BookTypeUrl.yanqing, BookTypeName.yanqing);
        BookCategory bookType_xianxia = new BookCategory(BookTypeUrl.xianxia, BookTypeName.xianxia);
        BookCategory bookType_lishi = new BookCategory(BookTypeUrl.lishi, BookTypeName.lishi);
        BookCategory bookType_wangyou = new BookCategory(BookTypeUrl.wangyou, BookTypeName.wangyou);
        BookCategory bookType_lingyi = new BookCategory(BookTypeUrl.lingyi, BookTypeName.lingyi);
        BookCategory bookType_tongren = new BookCategory(BookTypeUrl.tongren, BookTypeName.tongren);
        BookCategory bookType_quanben = new BookCategory(BookTypeUrl.quanben, BookTypeName.quanben);

        arrayList.add(bookType_xuanhuan);
        arrayList.add(bookType_yanqing);
        arrayList.add(bookType_xianxia);
        arrayList.add(bookType_lishi);
        arrayList.add(bookType_wangyou);
        arrayList.add(bookType_lingyi);
        arrayList.add(bookType_xuanhuan);
        arrayList.add(bookType_tongren);
        arrayList.add(bookType_quanben);

        return arrayList;
    }

    public String getBookUrl()
    {
        return bookUrl;
    }

    public String getBookName()
    {
        return bookName;
    }

}
