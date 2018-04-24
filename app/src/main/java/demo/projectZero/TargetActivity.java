package demo.projectZero;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

import eu.long1.projectZero.R;

public class TargetActivity extends AppCompatActivity {
    MaterialSearchBar searchBar;
    TextView targetLicense;
    TextView targetCommerceName;
    TextView targetAddress;
    TextView targetHandler;
    TextView targetIDNum;
    TextView targetIDAddress;
    TextView targetContactName;
    TextView targetContactNum;
    TextView editContactNum;
    TextView originalNum;
    private EditText numberInput;
    private View positiveAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        targetLicense = (TextView) findViewById(R.id.targetLicenseID);
        targetCommerceName = (TextView) findViewById(R.id.targetCommerceNameID);
        targetAddress = (TextView) findViewById(R.id.targetAddressID);
        targetHandler = (TextView) findViewById(R.id.targetHandlerID);
        targetIDNum = (TextView) findViewById(R.id.targetIDNumID);
        targetIDAddress = (TextView) findViewById(R.id.targetIDAddress);
        targetContactName = (TextView) findViewById(R.id.targetContactNameID);
        targetContactNum = (TextView) findViewById(R.id.targetContactNumID);

        //stop soft keyboard pushing up view
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        searchBar = (MaterialSearchBar) findViewById(R.id.targetSearchBar);
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                setTempInfo(searchBar.getText());
                Log.w("searchBarB text", searchBar.getText());
                searchBar.disableSearch();
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                switch (buttonCode) {
                    case (MaterialSearchBar.BUTTON_BACK):
                        searchBar.disableSearch();
                }
            }
        });
        targetContactNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Yeah", Toast.LENGTH_SHORT).show();
                showDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_single_finish, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_single_finish:
                if (!targetContactNum.getText().equals("未知")) {
                    showConfirmDialog(targetContactNum.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "请完成录入后在确认", Toast.LENGTH_LONG).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setTempInfo(String licenseNum) {
        if (licenseNum.equals("110111102400")) {
            targetLicense.setText("110111102400");
            targetCommerceName.setText("北京德发顺副食商店");
            targetAddress.setText("北京市房山区拱辰街道体育场3号");
            targetHandler.setText("李士玲");
            targetIDNum.setText("341202198201035525");
            targetIDAddress.setText("安徽省阜阳市颍州区王店镇郭庄村观堂25户");
            targetContactName.setText("李士玲");
            targetContactNum.setText("13661319065");

        } else {
            targetLicense.setText("未知");
            targetCommerceName.setText("未知");
            targetAddress.setText("未知");
            targetHandler.setText("未知");
            targetIDNum.setText("未知");
            targetIDAddress.setText("未知");
            targetContactName.setText("未知");
            targetContactNum.setText("未知");
            new MaterialDialog.Builder(this)
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

    private void showDialog() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("更改联系人电话")
                .titleColorRes(R.color.tbgreen)
                .customView(R.layout.change_contact_num, true)
                .positiveText("确认")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        targetContactNum.setText(numberInput.getText().toString());
                        InputMethodManager inputManager = (InputMethodManager)
                                getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null :
                                getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                })
                .build();

        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        numberInput = (EditText) dialog.getCustomView().findViewById(R.id.editContactNumID);
        numberInput.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        positiveAction.setEnabled(s.toString().trim().length() > 0);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {}
                });

        //change text after custom view
        View v = dialog.getCustomView();

        editContactNum = (TextView) v.findViewById(R.id.editContactNumID);
        originalNum = (TextView) v.findViewById(R.id.originalNumID);
        originalNum.setText(targetContactNum.getText());


        v.findViewById(R.id.editContactNumID).requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        dialog.show();
        positiveAction.setEnabled(false); // disabled by default

        searchBar.disableSearch();
    }

    public void showConfirmDialog(final String contactNum) {
        new MaterialDialog.Builder(this)
                .title("注意！")
                .titleColorRes(R.color.primary)
                .content("当事人电话：" + contactNum + "\n是否为最新？")
                .positiveText("确认")
                .positiveColorRes(R.color.tbgreen)
                .negativeText("修改")
                .negativeColorRes(R.color.tbgreen)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent i = new Intent();
                        i.putExtra("thirdLine", contactNum);
                        setResult(RESULT_OK, i);
                        finish();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        showDialog();
                    }
                })
                .show();
    }

}
