package fanch.style.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;
import fanch.style.constant.Constant;

public class MyLog
{

    class Configs
    {
        // To define if the programme is loggable
        public final static boolean IsLoggable = Constant.IS_DEBUG;
        // To define if the programme is loggable in SDCard
        public final static boolean IsLogInFile = false;
        // To decide if the record storage in sdcard
        public final static boolean IsRecordInExternalStorage = false;

        public final static String MyLogFile = "MyLogFile.txt";
    }

    private static boolean loggable = Configs.IsLoggable;
    private final static String tag = "mylog";

    private static String logfilename = null;;

    /**
     * 
     * @param lvl
     * @param action
     */
    static public void record(String s)
    {
        try
        {
            File root;
            if (logfilename == null)
                logfilename = Configs.MyLogFile;
            if (Configs.IsRecordInExternalStorage)
            {
                root = Environment.getExternalStorageDirectory();
            }
            else
            {
                root = new File("/");
            }
            if (root.canWrite())
            {
                File gpxfile = new File(root, logfilename);
                if (!gpxfile.exists())
                    gpxfile.createNewFile();
                FileWriter gpxwriter = new FileWriter(gpxfile, true);
                BufferedWriter out = new BufferedWriter(gpxwriter);

                out.write(s + "\n");
                out.close();
            }
        } catch (IOException e)
        {
            MyLog.e("Could not write file " + e.getMessage());
        }
    }

    public static void i(String msg)
    {
        if (loggable == false)
            return;
        Log.i(tag, msg);
        if (Configs.IsLogInFile)
            record(msg);

    }

    public static void withTime(String msg)
    {
        i(msg + " Time: " + (int) Math.floor(System.currentTimeMillis() / 1000));
    }

    public static void d(String msg)
    {
        if (loggable == false)
            return;
        Log.d(tag, msg);
        if (Configs.IsLogInFile)
            record(msg);

    }

    public static void e(String msg)
    {
        if (loggable == false)
            return;
        Log.e(tag, msg);
        if (Configs.IsLogInFile)
            record(msg);

    }

    public static void v(String msg)
    {
        if (loggable == false)
            return;
        Log.v(tag, msg);
        if (Configs.IsLogInFile)
            record(msg);
    }

    public static void w(String msg)
    {
        if (loggable == false)
            return;
        Log.w(tag, msg);
        if (Configs.IsLogInFile)
            record(msg);
    }

    public static void i(String tag1, String msg)
    {
        if (loggable == false)
            return;
        Log.i(tag + tag1, msg);
        if (Configs.IsLogInFile)
            record(msg);
    }

    public static void d(String tag1, String msg)
    {
        if (loggable == false)
            return;
        Log.d(tag + tag1, msg);
        if (Configs.IsLogInFile)
            record(msg);
    }

    public static void e(String tag1, String msg)
    {
        if (loggable == false)
            return;
        Log.e(tag + tag1, msg);
        if (Configs.IsLogInFile)
            record(msg);
    }

    public static void v(String tag1, String msg)
    {
        if (loggable == false)
            return;
        Log.v(tag + tag1, msg);
        if (Configs.IsLogInFile)
            record(msg);
    }

    public static void w(String tag1, String msg)
    {
        if (loggable == false)
            return;
        Log.w(tag + tag1, msg);
        if (Configs.IsLogInFile)
            record(msg);
    }
}
