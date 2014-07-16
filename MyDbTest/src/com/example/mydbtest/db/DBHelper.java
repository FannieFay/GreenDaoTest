package com.example.mydbtest.db;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mydbtest.db.DaoMaster.DevOpenHelper;

public class DBHelper
{

    private static final String TAG = DBHelper.class.getSimpleName();
    private static DBHelper instance;
    private static Context mContext;
    private DaoSession mDaoSession;
    private BookDao bookDao;
    private ChapterDao chapterDao;

    private DBHelper()
    {
    }

    public static DBHelper getInstance(Context context)
    {
        mContext = context;
        if (instance == null)
        {
            instance = new DBHelper();
        }
        DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, "xx", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        instance.mDaoSession = daoMaster.newSession();
        instance.bookDao = instance.mDaoSession.getBookDao();
        instance.chapterDao = instance.mDaoSession.getChapterDao();
        // helper.onUpgrade(db, 0, 1);
        daoMaster.createAllTables(db, true);

        return instance;
    }

    public Book loadBook(String id)
    {
        Book book = bookDao.load(id);
        book.getChapterList();
        return book;
    }

    public List<Book> loadAllBook()
    {
        return bookDao.loadAll();
    }

    public List<Chapter> loadAllChapter()
    {
        return chapterDao.loadAll();
    }

    public List<Chapter> loadAllChapter(String id)
    {
        return chapterDao._queryBook_ChapterList(id);
    }

    /**
     * query list with where clause ex: begin_date_time >= ? AND end_date_time <= ?
     * 
     * @param where
     *            where clause, include 'where' word
     * @param params
     *            query parameters
     * @return
     */

    public List<Book> queryNote(String where, String... params)
    {
        return bookDao.queryRaw(where, params);
    }

    /**
     * insert or update note
     * 
     * @param note
     * @return insert or update note id
     */
    public long saveBook(Book book)
    {
        return bookDao.insertOrReplace(book);
    }

    public long saveChapter(Chapter chapter)
    {
        return chapterDao.insertOrReplace(chapter);
    }

    /**
     * insert or update noteList use transaction
     * 
     * @param list
     */
    public void saveBookLists(final List<Book> list)
    {
        if (list == null || list.isEmpty())
        {
            return;
        }
        bookDao.getSession().runInTx(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < list.size(); i++)
                {
                    Book book = list.get(i);
                    bookDao.insertOrReplace(book);
                }
            }
        });

    }

    /**
     * delete all note
     */
    public void deleteAllBook()
    {
        bookDao.deleteAll();
    }

    /**
     * delete note by id
     * 
     * @param id
     */
    public void deleteBook(String id)
    {
        bookDao.deleteByKey(id);
    }

    public void deleteBook(Book book)
    {
        bookDao.delete(book);
    }

}
