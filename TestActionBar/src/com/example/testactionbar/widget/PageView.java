package com.example.testactionbar.widget;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.testactionbar.R;
import com.example.testactionbar.util.BookPageFactory;

public class PageView extends View
{
    ArrayList<String> arrayList;
    BookPageFactory mBookPageFactory;
    int margin;
    int lineSpace;
    int perHeight;
    int marginTop;
    int start, end;

    Context context;

    public PageView(Context context, ArrayList<String> arrayList, int start, int end)
    {
        super(context);
        this.arrayList = arrayList;
        this.start = start;
        this.end = end;
        mBookPageFactory = BookPageFactory.getInstance();
        lineSpace = mBookPageFactory.getLine_spacing();
        perHeight = mBookPageFactory.getWordPerHeight();
        margin = mBookPageFactory.getMargin();
        marginTop = mBookPageFactory.getMarginTop();
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint sPaint = new Paint();
        sPaint.setColor(context.getResources().getColor(R.color.black_900));
        sPaint.setTextSize(mBookPageFactory.getSize());

        int currentHeight = 0;
        for (int i = start; i <= end; i++)
        {
            if (i == start)
            {
                canvas.drawText(arrayList.get(i), margin, currentHeight + marginTop, sPaint);
                currentHeight = currentHeight + lineSpace + perHeight + marginTop;
            }
            else
            {
                canvas.drawText(arrayList.get(i), margin, currentHeight, sPaint);
                currentHeight = currentHeight + lineSpace + perHeight;
            }
        }
    }
}
