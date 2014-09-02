package com.example.testactionbar.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testactionbar.R;
import com.example.testactionbar.common.IntentKey;
import com.example.testactionbar.util.BookPageConfiguration;
import com.example.testactionbar.util.BookPageFactory;

public class Test extends Activity
{
    int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        setContentView(button);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;// 宽度
        height = dm.heightPixels;// 高度

        button.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList
                        .add("        几个堂主也都过来见过陈云生,大家又客气了几句,逐个散去。最后薛羽留了下来,上前抱拳,说道:“云生兄弟,在下听说你道法高明,我有件小事想请你帮忙。");
                arrayList.add("        这人平时为人耿直,对于求人之事难以启齿,所以这句话说的很不自然。");
                arrayList
                        .add("        薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。");
                arrayList
                        .add("        伴随着薛妻撕裂人心的喊叫,一道红色霞光闪过,一个浑身红光的婴儿从她下体蹦了出来。这小孩约有三岁大小,生的虎头虎脑,天灵之上一撮红毛格外引人注目,双眼泛着红光。小孩下地就会行走,只见他蹦蹦跳跳的在屋内转圈,很是惹人喜爱。");
                arrayList
                        .add("        黑骨对二人说道:“此子激ng纯的火灵根,离火之体,百万中无一,是天造之才。只可惜你们夫妻二人灵根薄弱无法正常产下,所以才怀胎三年之久。若非老夫用了冰寒的水灵气逼他出来,恐怕他真要在你肚子里赖上个十年八载的。");
                arrayList
                        .add("        “赐名好说,既然离火之体,那就取个离字吧。此子若留在你们身边恐怕耽误了他的前程,不如等他十岁之后交给陈老弟,让他带去天穹山修道,你老薛家从此也能多一个修仙之人。");
                arrayList
                        .add("        陈云生在咸州城一住就是三个月,这段时间除了修炼奇灵引就是与黑骨印证修行过程中遇到的种种体验,黑骨见多识广,有问必答,让陈云生获益匪浅。这天他梳洗完,正准备找黑骨,聊聊昨晚修行心得,突然听到有人轻叩房门。");
                arrayList
                        .add("        “我本出身官宦,家父在朝中为官,得罪权贵,惨遭清洗。抄家当日一名兵丁看我年幼,不忍拿去问罪,就放我一条生路。本应饿死于山野,但是正逢家师骊山老母访友归来,见我可怜,收为弟子。");
                arrayList
                        .add("        “本来以为从此有了依靠,谁知恩师寿元已尽,偏生一群宵小之徒妄图家师法宝。恩师无奈之下,遣我下山,到她凡间一个晚辈家里避祸,此人也是世外高人,但是年事已高,传我武艺六载,老人就仙去了。");
                arrayList.add("        陈云生暗想,自己平日对着凌云山的白云、松柏、山涧、鸟兽,虽然孤寂但也少了这人世间的生离死别。比这女子,自己还算幸运。");
                arrayList
                        .add("        言如诗接着说道:“小妹听闻陈兄师出名门,师伯就是天穹上人,所以想请代为引荐。让小女子能入得仙派,即便做一名杂役也好过在风尘中沦落。");
                arrayList
                        .add("        天地之间,男人和女人本就相互吸引。陈云生心思纯净,却也不能跳出人的正常情感。面对泪水涟涟的妙龄女子,就算坚如铁石的男人,恐怕也成绕指柔。");
                arrayList
                        .add("        “好,我答应你。想我师伯也不会不近人情的。”嘴上虽然这么说,但陈云生想到飞云子的种种怪异行为,不由得捏了一把冷汗。天穹上人若知道陈云生的腹诽,鼻子也会气歪了。");
                arrayList
                        .add("        言如诗盈盈一笑,站起对陈云生飘飘万福,口中言谢。又说了一会,女子起身道别,望着她离去的背影,陈云生心头升起一种异样的感觉");
                arrayList
                        .add("        言如诗见他说的至诚,心中的又放下了一层顾虑,叹了口气说道:“对于凡间诸事了解的少也未必是坏事,浪迹江湖未见得就是好事。你虽然自幼清修,仍有师父挂念,冷热有人知,小女子却如同水中的浮萍,四处飘零,冷暖自知。");
                arrayList
                        .add("        几个堂主也都过来见过陈云生,大家又客气了几句,逐个散去。最后薛羽留了下来,上前抱拳,说道:“云生兄弟,在下听说你道法高明,我有件小事想请你帮忙。");
                arrayList.add("        这人平时为人耿直,对于求人之事难以启齿,所以这句话说的很不自然。");
                arrayList
                        .add("        薛羽叹了口气,“这话要从我妻子身上说起,我妻前年正月被郎中诊断出有喜,本来是好事,谁知怀胎三年未产。现在家中的仆人、丫鬟都风传说怀的是妖胎。为这事情在下甚是忧愁。所以想请云生兄弟帮在下内子诊断一二。");
                arrayList
                        .add("        伴随着薛妻撕裂人心的喊叫,一道红色霞光闪过,一个浑身红光的婴儿从她下体蹦了出来。这小孩约有三岁大小,生的虎头虎脑,天灵之上一撮红毛格外引人注目,双眼泛着红光。小孩下地就会行走,只见他蹦蹦跳跳的在屋内转圈,很是惹人喜爱。");
                arrayList
                        .add("        黑骨对二人说道:“此子激ng纯的火灵根,离火之体,百万中无一,是天造之才。只可惜你们夫妻二人灵根薄弱无法正常产下,所以才怀胎三年之久。若非老夫用了冰寒的水灵气逼他出来,恐怕他真要在你肚子里赖上个十年八载的。");
                arrayList
                        .add("        “赐名好说,既然离火之体,那就取个离字吧。此子若留在你们身边恐怕耽误了他的前程,不如等他十岁之后交给陈老弟,让他带去天穹山修道,你老薛家从此也能多一个修仙之人。");
                arrayList
                        .add("        陈云生在咸州城一住就是三个月,这段时间除了修炼奇灵引就是与黑骨印证修行过程中遇到的种种体验,黑骨见多识广,有问必答,让陈云生获益匪浅。这天他梳洗完,正准备找黑骨,聊聊昨晚修行心得,突然听到有人轻叩房门。");
                arrayList
                        .add("        “我本出身官宦,家父在朝中为官,得罪权贵,惨遭清洗。抄家当日一名兵丁看我年幼,不忍拿去问罪,就放我一条生路。本应饿死于山野,但是正逢家师骊山老母访友归来,见我可怜,收为弟子。");
                arrayList
                        .add("        “本来以为从此有了依靠,谁知恩师寿元已尽,偏生一群宵小之徒妄图家师法宝。恩师无奈之下,遣我下山,到她凡间一个晚辈家里避祸,此人也是世外高人,但是年事已高,传我武艺六载,老人就仙去了。");
                arrayList.add("        陈云生暗想,自己平日对着凌云山的白云、松柏、山涧、鸟兽,虽然孤寂但也少了这人世间的生离死别。比这女子,自己还算幸运。");
                arrayList
                        .add("        言如诗接着说道:“小妹听闻陈兄师出名门,师伯就是天穹上人,所以想请代为引荐。让小女子能入得仙派,即便做一名杂役也好过在风尘中沦落。");
                arrayList
                        .add("        天地之间,男人和女人本就相互吸引。陈云生心思纯净,却也不能跳出人的正常情感。面对泪水涟涟的妙龄女子,就算坚如铁石的男人,恐怕也成绕指柔。");
                arrayList
                        .add("        “好,我答应你。想我师伯也不会不近人情的。”嘴上虽然这么说,但陈云生想到飞云子的种种怪异行为,不由得捏了一把冷汗。天穹上人若知道陈云生的腹诽,鼻子也会气歪了。");
                arrayList
                        .add("        言如诗盈盈一笑,站起对陈云生飘飘万福,口中言谢。又说了一会,女子起身道别,望着她离去的背影,陈云生心头升起一种异样的感觉");
                arrayList
                        .add("        言如诗见他说的至诚,心中的又放下了一层顾虑,叹了口气说道:“对于凡间诸事了解的少也未必是坏事,浪迹江湖未见得就是好事。你虽然自幼清修,仍有师父挂念,冷热有人知,小女子却如同水中的浮萍,四处飘零,冷暖自知。");

                /**
                 * 初始化数据
                 */
                BookPageFactory bookPageFactory = BookPageFactory.getInstance();
                bookPageFactory.setBookPageConfiguration(new BookPageConfiguration()
                        .setHeight(height).setWidth(width)
                        .setLine_spacing((int) getResources().getDimension(R.dimen.line_spacing))
                        .setMargin((int) getResources().getDimension(R.dimen.margin_left))
                        .setMarginTop((int) getResources().getDimension(R.dimen.marginTop))
                        .setSize((int) getResources().getDimension(R.dimen.word_size)));

                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < arrayList.size(); i++)
                {
                    stringBuffer.append(arrayList.get(i));
                    stringBuffer.append("\n");
                }
                ArrayList<String> aList = bookPageFactory.getArrayList(stringBuffer.toString());
                Intent intent = new Intent(Test.this, BookContent.class);
                intent.putExtra(IntentKey.INTENT_LIST_KEY, aList);
                startActivity(intent);
            }
        });

    }
}
