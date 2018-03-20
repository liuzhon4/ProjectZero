package demo.projectZero;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mancj.materialsearchbar.MaterialSearchBar;

import eu.long1.projectZero.R;

public class CigaretteLogActivity extends AppCompatActivity {

    private MaterialSearchBar searchBar;
    private EditText amountInput;
    private View positiveAction;

    private static final int ACTIVITY_REQUEST_CODE_SCAN = 0;

    String barCode;
    TextView barCodeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cigarette_log);

        //stop soft keyboard pushing up the view
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        searchBar = (MaterialSearchBar) findViewById(R.id.cigaretteLog);

        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                Toast.makeText(getApplicationContext(), searchBar.getText(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                switch (buttonCode) {
                    case (MaterialSearchBar.BUTTON_SPEECH):
                        Intent i = new Intent(getApplicationContext(), SimpleScannerActivity.class);
                        startActivityForResult(i, ACTIVITY_REQUEST_CODE_SCAN);
                    case (MaterialSearchBar.BUTTON_BACK):
                        searchBar.disableSearch();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_CODE_SCAN) {
            if (resultCode == RESULT_OK) {
                barCode = data.getStringExtra("barCode");
                MaterialDialog dialog = new MaterialDialog.Builder(this)
                        .title("卷烟登记")
                        .titleColorRes(R.color.tbgreen)
                        .customView(R.layout.cigarette_log_dialog, true)
                        .positiveText("确认")
                        .negativeText("取消")
                        .onPositive(new com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull com.afollestad.materialdialogs.MaterialDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(getApplicationContext(), "数量: " + amountInput.getText().toString(),
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .build();

                positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
                //noinspection ConstantConditions
                amountInput = (EditText) dialog.getCustomView().findViewById(R.id.amount);
                amountInput.addTextChangedListener(
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
                barCodeTextView = (TextView) v.findViewById(R.id.barCodeTextView);
                barCodeTextView.setText(barCode);

                v.findViewById(R.id.amount).requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                dialog.show();
                positiveAction.setEnabled(false); // disabled by default

                searchBar.disableSearch();
            }
        }
    }

}
