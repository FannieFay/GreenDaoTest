package fanch.style;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.ViewConfiguration;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements OnRefreshListener
{

    SwipeRefreshLayout swipeLayout;
    ActionBar actionBar;
    TestAdapter mAdapter;
    ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("test");
        setOverflowShowingAlways();
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeLayout.setRefreshing(true);
        mList = (ListView) findViewById(R.id.list);
        ArrayList<String> arrayList = new ArrayList<String>(30);
        arrayList.add("11");
        arrayList.add("22");
        arrayList.add("11");
        arrayList.add("22");
        arrayList.add("11");
        arrayList.add("22");
        arrayList.add("11");
        arrayList.add("22");
        arrayList.add("11");
        arrayList.add("22");
        arrayList.add("11");
        arrayList.add("22");
        arrayList.add("11");
        arrayList.add("22");
        arrayList.add("11");
        arrayList.add("22");
        mAdapter = new TestAdapter(this, arrayList);
        mList.setAdapter(mAdapter);

    }

    @Override
    public void onRefresh()
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 一直显示在顶部
     */
    private void setOverflowShowingAlways()
    {
        try
        {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null)
            {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex)
        {
            // Ignore
        }
    }

}
