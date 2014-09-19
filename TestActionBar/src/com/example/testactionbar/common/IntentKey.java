package com.example.testactionbar.common;

public interface IntentKey
{
    /**
     * 传递List
     */
    public final String INTENT_LIST_KEY = "intent_list_key";

    /**
     * 开始position
     */
    public final String INTENT_START_KEY = "intent_start_key";

    /**
     * 结束position
     */
    public final String INTENT_END_KEY = "intent_end_key";

    /**
     * url
     */
    public final String INTENT_URL_KEY = "intent_url_key";

    /**
     * BookInfo class 传递
     */
    public final String INTENT_BOOKINFO_KEY = "intent_bookinfo_key";

    /**
     * ArrayList<modle.Chapter> 传递
     */
    public final String INTENT_CHAPTER_LIST_KEY = "intent_chapter_list_key";

    /**
     * ChapterInfo class
     */
    public final String INTENT_CHAPTER_INFO_KEY = "intent_chapter_info_key";

    /**
     * position
     */
    public final String INTENT_POSITION_KEY = "intent_position_key";

    /**
     * StartAndEnd class 传递
     */
    public final String INTENT_START_END_KEY = "intent_start_end_key";

    /**
     * 标题
     */
    public final String INTENT_TITLE_KEY = "intent_title_key";

    /**
     * where
     */
    public final String INTENT_FROM_KEY = "intent_form_key";

    // DB
    public final String INTENT_DB_BOOK_KEY = "intent_db_book_key"; // 传递数据库Book
    public final String INTENT_DB_CHAPTER_KEY = "intent_db_chapter_key"; // 传递数据库Chapter
    public final String INTENT_DB_CHAPTER_LIST_KEY = "intent_db_chapter_list_key"; // 传递数据库ChapterList

}
