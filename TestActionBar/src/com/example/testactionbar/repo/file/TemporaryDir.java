package com.example.testactionbar.repo.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;

@SuppressLint("SimpleDateFormat")
public class TemporaryDir
{
    private  File tempDir;

    public  TemporaryDir(Context context)
    {
        FileRepository repository=new FileRepository(context, "temp");
        tempDir = repository.getRootDir();
        
    }
    
    public File getDir(){
        return tempDir;
    }

    public File getFile(String fileName)
    {
        return new File(tempDir, fileName);
    }

    public File getPicFile()
    {
        return new File(tempDir, getPictureName());
    }

    public void clear()
    {
        File[] files = tempDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            f.delete();
    }

    private String getPictureName()
    {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date);

    }
    
    

}
