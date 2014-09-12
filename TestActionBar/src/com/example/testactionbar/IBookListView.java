package com.example.testactionbar;

import com.example.testactionbar.modle.BookInfoExpand;

public interface IBookListView
{
    public void getBookListSuccess(BookInfoExpand bookInfoExpand);

    public void getMoreBookListSuccess(BookInfoExpand bookInfoExpand);

    /**
     * @Title: getBookListByTypeSuccess
     * @Description: TODO(根据主题书单获取书籍列表失败)
     * @throws
     */
    public void getBookListFailure();

}
