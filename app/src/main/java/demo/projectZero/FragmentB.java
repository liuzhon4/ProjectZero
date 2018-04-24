package demo.projectZero;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

import eu.long1.projectZero.R;

public class FragmentB extends Fragment {
    private View view;
    private List<String> lastSearches;
    private MaterialSearchBar searchBar;

    TextView license;
    TextView commerceName;
    TextView address;
    TextView handler;
    TextView contactName;
    TextView contactNum;
    TextView IDNum;
    TextView commerceNum;
    TextView startDate;
    TextView endDate;
    TextView belongsTo;
    TextView status;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        setMenuVisibility(false);
        view = inflater.inflate(R.layout.fragment_b, container, false);
        searchBar = (MaterialSearchBar) view.findViewById(R.id.searchBarB);
        license = (TextView) view.findViewById(R.id.licenseID);
        commerceName = (TextView) view.findViewById(R.id.commerceNameID);
        address = (TextView) view.findViewById(R.id.addressID);
        handler = (TextView) view.findViewById(R.id.handlerID);
        contactName = (TextView) view.findViewById(R.id.contactNameID);
        contactNum = (TextView) view.findViewById(R.id.contactNumID);
        IDNum = (TextView) view.findViewById(R.id.IDNumID);
        commerceNum = (TextView) view.findViewById(R.id.commerceNumID);
        startDate = (TextView) view.findViewById(R.id.startDateID);
        endDate = (TextView) view.findViewById(R.id.endDateID);
        belongsTo = (TextView) view.findViewById(R.id.belongsToID);
        status = (TextView) view.findViewById(R.id.statusID);

        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                InputMethodManager inputManager = (InputMethodManager)
                        getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow((null == getActivity().getCurrentFocus()) ? null :
                        getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                setTempInfo(searchBar.getText());
                searchBar.disableSearch();
                Log.w("searchBarB text", searchBar.getText());
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                switch (buttonCode) {
                    case (MaterialSearchBar.BUTTON_BACK):
                        searchBar.disableSearch();
                }
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_update, menu);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("零售户信息查询");
        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    public void setTempInfo(String licenseNum) {
        if (licenseNum.equals("110111102400")) {
            license.setText("110111102400");
            commerceName.setText("北京德发顺副食商店");
            address.setText("北京市房山区拱辰街道体育场3号");
            handler.setText("李士玲");
            contactName.setText("李士玲");
            contactNum.setText("13661319065");
            IDNum.setText("341202198201035525");
            commerceNum.setText("92110111MA00H77T0A");
            startDate.setText("2018-04-08");
            endDate.setText("2019-04-17");
            belongsTo.setText("租赁");
            status.setText("正常经营");
        } else {
            license.setText("未知");
            commerceName.setText("未知");
            address.setText("未知");
            handler.setText("未知");
            contactName.setText("未知");
            contactNum.setText("未知");
            IDNum.setText("未知");
            commerceNum.setText("未知");
            startDate.setText("未知");
            endDate.setText("未知");
            belongsTo.setText("未知");
            status.setText("未知");
            new MaterialDialog.Builder(getActivity())
                    .title("注意！")
                    .titleColorRes(R.color.primary)
                    .content("零售户 " + licenseNum + " 不存在！")
                    .positiveText("确认")
                    .positiveColorRes(R.color.tbgreen)
                    .negativeText("取消")
                    .negativeColorRes(R.color.tbgreen)
                    .show();
        }

    }

}
