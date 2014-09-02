package fanch.style.niftymodaldialogeffects.lib;

import fanch.style.niftymodaldialogeffects.lib.effects.BaseEffects;
import fanch.style.niftymodaldialogeffects.lib.effects.FadeIn;
import fanch.style.niftymodaldialogeffects.lib.effects.Fall;
import fanch.style.niftymodaldialogeffects.lib.effects.FlipH;
import fanch.style.niftymodaldialogeffects.lib.effects.FlipV;
import fanch.style.niftymodaldialogeffects.lib.effects.NewsPaper;
import fanch.style.niftymodaldialogeffects.lib.effects.RotateBottom;
import fanch.style.niftymodaldialogeffects.lib.effects.RotateLeft;
import fanch.style.niftymodaldialogeffects.lib.effects.Shake;
import fanch.style.niftymodaldialogeffects.lib.effects.SideFall;
import fanch.style.niftymodaldialogeffects.lib.effects.SlideBottom;
import fanch.style.niftymodaldialogeffects.lib.effects.SlideLeft;
import fanch.style.niftymodaldialogeffects.lib.effects.SlideRight;
import fanch.style.niftymodaldialogeffects.lib.effects.SlideTop;
import fanch.style.niftymodaldialogeffects.lib.effects.Slit;

/**
 * Created by lee on 2014/7/30.
 */
public enum Effectstype
{

    Fadein(FadeIn.class), Slideleft(SlideLeft.class), Slidetop(SlideTop.class), SlideBottom(
            SlideBottom.class), Slideright(SlideRight.class), Fall(Fall.class), Newspager(
            NewsPaper.class), Fliph(FlipH.class), Flipv(FlipV.class), RotateBottom(
            RotateBottom.class), RotateLeft(RotateLeft.class), Slit(Slit.class), Shake(Shake.class), Sidefill(
            SideFall.class);
    private Class<? extends BaseEffects> effectsClazz;

    private Effectstype(Class<? extends BaseEffects> mclass)
    {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator()
    {
        BaseEffects bEffects = null;
        try
        {
            bEffects = effectsClazz.newInstance();
        } catch (ClassCastException e)
        {
            throw new Error("Can not init animatorClazz instance");
        } catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        } catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        }
        return bEffects;
    }
}
