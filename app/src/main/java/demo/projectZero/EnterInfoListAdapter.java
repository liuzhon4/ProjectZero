package demo.projectZero;

import android.content.Context;

import com.yuyh.easyadapter.abslistview.EasyLVAdapter;
import com.yuyh.easyadapter.abslistview.EasyLVHolder;

import java.util.List;

import eu.long1.projectZero.R;

/**
 * @author yuyh.
 * @date 2016/7/21.
 */
public class EnterInfoListAdapter extends EasyLVAdapter<Bean> {


    public EnterInfoListAdapter(Context context, List<Bean> list, int... layoutIds) {
        super(context, list, layoutIds);
    }

    @Override
    public void convert(EasyLVHolder holder, int position, final Bean bean) {
        holder.setText(R.id.tv, bean.name);
    }

    @Override
    public int getLayoutIndex(int position, Bean item) {
        if (position % 2 == 0)
            return 1;
        else return 0;
    }
}
