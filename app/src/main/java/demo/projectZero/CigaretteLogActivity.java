package demo.projectZero;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;

import eu.long1.projectZero.R;

public class CigaretteLogActivity extends AppCompatActivity
        implements MaterialSearchBar.OnSearchActionListener {

    private MaterialSearchBar searchBar;
    private ImageButton scan;
    private static final int ACTIVITY_REQUEST_CODE_SCAN = 0;
    private String barCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cigarette_log);

        scan = (ImageButton) findViewById(R.id.scan);

        searchBar = (MaterialSearchBar) findViewById(R.id.cigaretteLog);

        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null :
                        getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                Toast.makeText(getApplicationContext(), searchBar.getText(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SimpleScannerActivity.class);
                startActivityForResult(i, ACTIVITY_REQUEST_CODE_SCAN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_CODE_SCAN) {
            if (resultCode == RESULT_OK) {
                barCode = data.getStringExtra("barCode");
                Toast.makeText(getApplicationContext(), "barCode: " + barCode, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode){
            case MaterialSearchBar.BUTTON_SPEECH:
                Toast.makeText(this, "lalala", Toast.LENGTH_SHORT).show();
        }
    }



}
