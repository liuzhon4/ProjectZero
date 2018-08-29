package demo.projectZero;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import eu.long1.projectZero.R;

import java.util.ArrayList;

public class MyCustomBaseAdapter extends BaseAdapter {
    private static ArrayList<MerchantDisplayInfo> searchArrayList;

    private LayoutInflater mInflater;

    public MyCustomBaseAdapter(Context context, ArrayList<MerchantDisplayInfo> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return searchArrayList.size();
    }

    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_row_view, null);
            holder = new ViewHolder();
            holder.txtMerchantName = (TextView) convertView.findViewById(R.id.merchantName);
            holder.txtMerchantLinceseNum = (TextView) convertView
                    .findViewById(R.id.merchantLicenseNum);
            holder.txtMerchantAddress = (TextView) convertView.findViewById(R.id.merchantAddress);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtMerchantName.setText(searchArrayList.get(position).getMerchantName());
        holder.txtMerchantLinceseNum.setText(searchArrayList.get(position)
                .getMerchantLicenseNum());
        holder.txtMerchantAddress.setText(searchArrayList.get(position).getMerchantAddress());

        return convertView;
    }

    static class ViewHolder {
        TextView txtMerchantName;
        TextView txtMerchantLinceseNum;
        TextView txtMerchantAddress;
    }
}
