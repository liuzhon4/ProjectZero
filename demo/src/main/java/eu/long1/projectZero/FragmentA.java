package eu.long1.projectZero;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import de.mrapp.android.dialog.ProgressDialog;
import eu.long1.spacetablayout.SpaceTabLayout;

public class FragmentA extends Fragment {
    private View view;
    private Button mButton;
    private EditText mLast16Text;
    private TextView mCompanyText;
    private TextView mHomeText;
    private TextView mLicenseText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true);
        setMenuVisibility(false);

        view = inflater.inflate(R.layout.fragment_a, container, false);
        mButton = (Button) view.findViewById(R.id.searchButton);
        mLast16Text = (EditText) view.findViewById(R.id.last16View);
        mCompanyText = (TextView) view.findViewById(R.id.companyText);
        mHomeText = (TextView) view.findViewById(R.id.homeText);
        mLicenseText = (TextView) view.findViewById(R.id.licenseText);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //hide virtual keyboard after pressing button
                InputMethodManager inputManager = (InputMethodManager)
                        getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow((null == getActivity().getCurrentFocus()) ? null :
                        getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                if (mLast16Text.length() != 16) {
                    Toast.makeText(getActivity(), "请输入卷烟后16位编码", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), mLast16Text.getText(), Toast.LENGTH_LONG).show();
                    List<String> result = getCompanyAndHome(mLast16Text.getText().toString());
                    Log.w("result1", result.get(0));
                    Log.w("result2", result.get(1));
                    mCompanyText.setText("未知".equals(result.get(0)) ? "未知" : result.get(0) + "烟草公司");
//                    mCompanyText.setText(result.get(0) + "烟草公司");
                    mHomeText.setText(result.get(1));
                    mLicenseText.setText(mLast16Text.getText().toString().substring(4, 16));
                    mLicenseText.setPaintFlags(mLicenseText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    mLicenseText.setTextColor(ContextCompat.getColor(getContext(), R.color.tbgreen));
                }
            }
        });
        mLicenseText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "License Button Clicked", Toast.LENGTH_LONG).show();

            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Add your menu entries here
        inflater.inflate(R.menu.menu_update, menu);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("卷烟归属地查询");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_update:
//                Toast.makeText(getActivity().getApplication(), "卷烟归属地信息更新成功", Toast.LENGTH_SHORT).show();
                ProgressDialog.Builder dialogBuilder = new ProgressDialog.Builder(getContext());
                dialogBuilder
                        .setTitle("注意")
                        .setMessage("卷烟归属地信息更新成功")
                        .setPositiveButton("确认", null)
                        .setProgressBarPosition(ProgressDialog.ProgressBarPosition.LEFT)
                        .create()
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
