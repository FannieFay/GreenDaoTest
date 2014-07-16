package com.example.testactionbar;

import java.util.ArrayList;

import com.example.testactionbar.presenter.modle.BookInfo;

public interface IBookListView
{
    /**
     * @Title: searchBookListSuccess
     * @Description: TODO(搜索书籍列表成功)
     * @throws
     */
    public void searchBookListSuccess();

    /**
     * @Title: searchBookListFailure
     * @Description: TODO(搜索书籍列表失败)
     * @throws
     */
    public void searchBookListFailure();

    /**
     * @Title: getBookListByTypeSuccess
     * @Description: TODO(根据主题书单获取书籍列表成功)
     * @throws
     */
    public void getBookListByTypeSuccess(ArrayList<BookInfo> arrayList);

    /**
     * @Title: getBookListByTypeSuccess
     * @Description: TODO(根据主题书单获取书籍列表失败)
     * @throws
     */
    public void getBookListByTypeFailure();

    /**
     * @Title: getBookListByTypeSuccess
     * @Description: TODO(根据排行榜获取书籍列表成功)
     * @throws
     */
    public void getBookListByRankSuccess();

    /**
     * @Title: getBookListByTypeSuccess
     * @Description: TODO(根据排行榜获取书籍列表)
     * @throws
     */
    public void getBookListByRankFailure();
}
