package orgs.androidtown.tablayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Jisang on 2017-09-28.
 */

public class Three extends FrameLayout{

    public Three(Context context) {
        super(context);
        initView();
    }

    public Three(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    // 여기서 내가 만든 레이아웃을 inflate하고
    // 나 자신에게 add한다.

    private void initView(){
        // 1. 레이아웃 파일로 뷰를 만들고
        View view =LayoutInflater.from(getContext()).inflate(R.layout.fragment_three, null);

        addView(view);
    }
}
