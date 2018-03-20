package demo.projectZero;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import eu.long1.projectZero.R;

public class FragmentA extends Fragment {
    private View view;
    private String mLast16Text;
    private TextView mCompanyText;
    private TextView mHomeText;
    private TextView mLicenseText;
    private MaterialSearchBar searchBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true);
        setMenuVisibility(false);

        view = inflater.inflate(R.layout.fragment_a, container, false);
        mCompanyText = (TextView) view.findViewById(R.id.companyText);
        mHomeText = (TextView) view.findViewById(R.id.homeText);
        mLicenseText = (TextView) view.findViewById(R.id.licenseText);

        searchBar = (MaterialSearchBar) view.findViewById(R.id.searchBarA);

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
                mLast16Text = searchBar.getText();
                if (mLast16Text.length() != 16) {
                    clearField();
                    Toast.makeText(getActivity(), "请输入正确的卷烟后16位编码", Toast.LENGTH_LONG).show();
                } else {
                    List<String> result = getCompanyAndHome(mLast16Text);
                    mCompanyText.setText("未知".equals(result.get(0)) ? "未知" : result.get(0) + "烟草公司");
                    mHomeText.setText(result.get(1));
                    mLicenseText.setText(mLast16Text.substring(4, 16));
                    mLicenseText.setPaintFlags(mLicenseText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    mLicenseText.setTextColor(ContextCompat.getColor(getContext(), R.color.tbgreen));
                }
                Log.w("searchBarA text", searchBar.getText());
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                switch (buttonCode) {
                    case (MaterialSearchBar.BUTTON_BACK):
                        searchBar.disableSearch();
                }
            }
        });

        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Log.w("searchStateChanged", searchBar.getText());
                if(searchBar.getText().length() > 16) {
                    Toast.makeText(getActivity(), "长度已超过16位", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        mLicenseText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("license", mLicenseText.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getActivity(), "许可证号已复制到剪贴板", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Add your menu entries here
//        inflater.inflate(R.menu.menu_update, menu);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("卷烟归属地查询");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onStart() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("卷烟归属地查询");
        super.onStart();
    }

    public void clearField() {
        mHomeText.setText("未知");
        mCompanyText.setText("未知");
        mLicenseText.setText("未知");
        mLicenseText.setClickable(false);
        mLicenseText.setTextColor(ContextCompat.getColor(getContext(), R.color.defaultTextView));
        mLicenseText.setPaintFlags(mLicenseText.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));
    }


    

    public List<String> getCompanyAndHome(String last16) {
        String locationCode = last16.substring(4, 10);
        AssetManager am = getActivity().getAssets();
        String line;
        List<String> result = Arrays.asList("未知", "未知");
        try{
            InputStream is = am.open("homeDB/" + locationCode.substring(0, 1) + ".txt");
            BufferedReader reader = new BufferedReader((new InputStreamReader(is)));
            while ((line = reader.readLine()) != null) {
                List<String> lineList = Arrays.asList(line.split("--"));
                if(line.startsWith(locationCode)) {
                    result.set(0, lineList.get(3));
                    result.set(1, lineList.get(1));
                } else if (line.startsWith(locationCode.substring(0, 4))) {
                    result.set(0, lineList.get(3));
                }
            }
        } catch (IOException e) {
            Log.e("assetFile", e.getMessage());
        }
        return result;
    }


}
