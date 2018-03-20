package demo.projectZero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.mancj.materialsearchbar.MaterialSearchBar;

import eu.long1.projectZero.R;

public class TargetActivity extends AppCompatActivity {
    MaterialSearchBar searchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        //stop soft keyboard pushing up view
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        searchBar = (MaterialSearchBar) findViewById(R.id.targetSearchBar);
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {

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
    }
}
