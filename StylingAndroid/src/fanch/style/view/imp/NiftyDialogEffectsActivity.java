package fanch.style.view.imp;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import fanch.style.R;
import fanch.style.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import fanch.style.view.base.BaseFragmentActivity;

public class NiftyDialogEffectsActivity extends BaseFragmentActivity
{
    /**
     * 包 niftymodaldialogeffects.lib
     */

    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nifty_dialog_effects);
        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
        dialogBuilder.withTitle("Modal Dialog").withMessage("This is a modal Dialog.").show();

        edit = (EditText) findViewById(R.id.edit);
        edit.setError("输入的密码错误");
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Log.e("onBackPressed", "onBackPressed");
    }

    // configuration:

    // dialogBuilder.withTitle("Modal Dialog") // .withTitle(null) no title
    // .withTitleColor("#FFFFFF") // def
    // .withDividerColor("#11000000") // def
    // .withMessage("This is a modal Dialog.") // .withMessage(null) no Msg
    // .withMessageColor("#FFFFFF") // def
    // .withIcon(getResources().getDrawable(R.drawable.icon)).withDuration(700) // def
    // .withEffect(effect) // def Effectstype.Slidetop
    // .withButton1Text("OK") // def gone
    // .withButton2Text("Cancel") // def gone
    // .isCancelableOnTouchOutside(true) // def | isCancelable(true)
    // .setCustomView(R.layout.custom_view, v.getContext()) // .setCustomView(View or
    // // ResId,context)
    // .setButton1Click(new View.OnClickListener()
    // {
    // @Override
    // public void onClick(View v)
    // {
    // Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
    // }
    // }).setButton2Click(new View.OnClickListener()
    // {
    // @Override
    // public void onClick(View v)
    // {
    // Toast.makeText(v.getContext(), "i'm btn2", Toast.LENGTH_SHORT).show();
    // }
    // }).show();
}
