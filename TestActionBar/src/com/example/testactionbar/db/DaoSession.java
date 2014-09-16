package com.example.testactionbar.db;

import java.util.List;
import java.util.Map;

import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession
{

    private final DaoConfig bookDaoConfig;
    private final DaoConfig chapterDaoConfig;

    private final BookDao bookDao;
    private final ChapterDao chapterDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type,
            Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> daoConfigMap)
    {
        super(db);

        bookDaoConfig = daoConfigMap.get(BookDao.class).clone();
        bookDaoConfig.initIdentityScope(type);

        chapterDaoConfig = daoConfigMap.get(ChapterDao.class).clone();
        chapterDaoConfig.initIdentityScope(type);

        bookDao = new BookDao(bookDaoConfig, this);
        chapterDao = new ChapterDao(chapterDaoConfig, this);

        registerDao(Book.class, bookDao);
        registerDao(Chapter.class, chapterDao);
    }

    public void clear()
    {
        bookDaoConfig.getIdentityScope().clear();
        chapterDaoConfig.getIdentityScope().clear();
    }

    public BookDao getBookDao()
    {
        return bookDao;
    }

    public ChapterDao getChapterDao()
    {
        return chapterDao;
    }

    /**
     * 插入Book 数据
     * 
     * @param book
     */
    public void insertBookInfo(Book book)
    {
        bookDao.insert(book);
    }

    /**
     * 插入章节书籍
     * 
     * @param chapter
     */
    public void insertChapterInfo(Chapter chapter)
    {
        chapterDao.insert(chapter);
    }

    /**
     * 获取书架 书籍
     * 
     * @return
     */
    public List<Book> getBookList()
    {
        return bookDao.loadAll();
    }

    /**
     * 获取某本书的章节 列表
     * 
     * @param bookID
     * @return
     */
    public List<Chapter> getChapterList(String bookName)
    {
        QueryBuilder<Chapter> qb = chapterDao.queryBuilder();
        qb.where(ChapterDao.Properties.BookName.eq(bookName));
        return qb.list();
    }

    /**
     * 删除某本书籍
     * 
     * @param bookID
     */
    public void deleteBook(String bookName)
    {
        QueryBuilder<Book> qb = bookDao.queryBuilder();
        DeleteQuery<Book> bd = qb.where(BookDao.Properties.BookName.eq(bookName)).buildDelete();
        bd.executeDeleteWithoutDetachingEntities();

        QueryBuilder<Chapter> chapterqb = chapterDao.queryBuilder();
        DeleteQuery<Chapter> chapterbd = chapterqb.where(
                ChapterDao.Properties.BookName.eq(bookName)).buildDelete();
        chapterbd.executeDeleteWithoutDetachingEntities();
    }

    /**
     * 删除章节
     * 
     * @param bookName
     */
    public void deleteChapterByBook(String bookName)
    {
        QueryBuilder<Chapter> chapterqb = chapterDao.queryBuilder();
        DeleteQuery<Chapter> chapterbd = chapterqb.where(
                ChapterDao.Properties.BookName.eq(bookName)).buildDelete();
        chapterbd.executeDeleteWithoutDetachingEntities();
    }

    /**
     * 插入Chapter 数据
     * 
     * @param chapter
     */
    public void insertChapter(Chapter chapter)
    {
        chapterDao.insert(chapter);
    }

    /**
     * 是否存在该书
     * 
     * @param bookID
     * @return
     */
    public boolean isBookSaved(String bookName)
    {
        QueryBuilder<Book> qb = bookDao.queryBuilder();
        qb.where(BookDao.Properties.BookName.eq(bookName));
        qb.buildCount().count();
        return qb.buildCount().count() > 0 ? true : false;
    }

}
