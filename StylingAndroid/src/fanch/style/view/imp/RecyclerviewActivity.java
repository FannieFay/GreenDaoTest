package fanch.style.view.imp;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import fanch.style.R;
import fanch.style.mode.ItemData;
import fanch.style.view.base.BaseFragmentActivity;
import fanch.style.view.imp.adapter.RecyclerViewAdapter;

public class RecyclerviewActivity extends BaseFragmentActivity
{
    RecyclerView recyclerView;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);

        // this is data fro recycler view
        ItemData itemsData[] = { new ItemData("Help", R.drawable.ic_launcher),
                new ItemData("Delete", R.drawable.ic_launcher),
                new ItemData("Cloud", R.drawable.ic_launcher),
                new ItemData("Favorite", R.drawable.ic_launcher),
                new ItemData("Like", R.drawable.ic_launcher),
                new ItemData("Rating", R.drawable.ic_launcher),
                new ItemData("Delete", R.drawable.ic_launcher),
                new ItemData("Cloud", R.drawable.ic_launcher),
                new ItemData("Favorite", R.drawable.ic_launcher),
                new ItemData("Like", R.drawable.ic_launcher),
                new ItemData("Rating", R.drawable.ic_launcher) };

        recyclerView.setHasFixedSize(true);

        // 2. set layoutManger
        recyclerView.setLayoutManager(mLinearLayoutManager);

        // 3. create an adapter
        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(itemsData);

        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
