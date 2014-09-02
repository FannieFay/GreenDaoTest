package fanch.style.view.imp;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import fanch.style.R;
import fanch.style.content.Content;
import fanch.style.view.base.BaseFragmentActivity;
import fanch.style.view.imp.adapter.DirectoryAdapter;

public class DirectoryActivity extends BaseFragmentActivity implements Content
{
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        mListView = (ListView) findViewById(R.id.mListView);
        setArrayList();
        mListView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String string = (String) parent.getAdapter().getItem(position);
                if (string.equals(CONTENT_NIFTY_DIALOG_EFFECTS))
                {
                    Intent intent = new Intent(DirectoryActivity.this,
                            NiftyDialogEffectsActivity.class);
                    startActivity(intent);
                }
                if (string.equals(CONTENT_RECYCLER_VIEW))
                {
                    Intent intent = new Intent(DirectoryActivity.this, RecyclerviewActivity.class);
                    startActivity(intent);
                }
                if (string.equals(CONTENT_CARD_VIEW))
                {
                    Intent intent = new Intent(DirectoryActivity.this, CardViewActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setArrayList()
    {
        ArrayList<String> mArrayList = new ArrayList<String>();
        mArrayList.add(CONTENT_NIFTY_DIALOG_EFFECTS);
        mArrayList.add(CONTENT_RECYCLER_VIEW);
        mArrayList.add(CONTENT_CARD_VIEW);
        DirectoryAdapter mAdapter = new DirectoryAdapter(this, mArrayList);
        mListView.setAdapter(mAdapter);
    }
}
