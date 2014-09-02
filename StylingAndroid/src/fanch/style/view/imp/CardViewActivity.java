package fanch.style.view.imp;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import fanch.style.R;
import fanch.style.view.base.BaseFragmentActivity;

public class CardViewActivity extends BaseFragmentActivity
{
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);
        cardView = (CardView) findViewById(R.id.card_view);
    }
}
