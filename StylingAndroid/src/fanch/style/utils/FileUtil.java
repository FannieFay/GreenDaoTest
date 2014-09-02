package fanch.style.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;

public class FileUtil
{
    public interface ImageLoad
    {
        void loadSuccess();

        void loadFailure();
    }

    /**
     * Save Bitmap to a file.保存图片到SD卡。
     * 
     * @param bitmap
     * @param file
     * @return error message if the saving is failed. null if the saving is successful.
     * @throws IOException
     */
    public static void saveBitmapToFile(Bitmap bitmap, String _file, ImageLoad mImageLoad)
            throws IOException
    {
        BufferedOutputStream os = null;
        try
        {
            File file = new File(_file);
            // String _filePath_file.replace(File.separatorChar +
            // file.getName(), "");
            int end = _file.lastIndexOf(File.separator);
            String _filePath = _file.substring(0, end);
            File filePath = new File(_filePath);
            if (!filePath.exists())
            {
                filePath.mkdirs();
            }
            if (file.exists())
            {
                mImageLoad.loadSuccess();
                return;
            }
            file.createNewFile();
            os = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            mImageLoad.loadSuccess();
        } finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                } catch (IOException e)
                {
                    mImageLoad.loadFailure();
                }
            }
        }
    }
}
