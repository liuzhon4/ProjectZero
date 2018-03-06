package eu.long1.projectZero;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import de.mrapp.android.dialog.MaterialDialog;
import de.mrapp.android.dialog.ProgressDialog;

public class FragmentB extends Fragment implements MaterialSearchBar.OnSearchActionListener{
    private View view;
    private List<String> lastSearches;
    private MaterialSearchBar searchBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        setMenuVisibility(false);
        view = inflater.inflate(R.layout.fragment_b, container, false);
        searchBar = (MaterialSearchBar) view.findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                Log.w("LOG_TAG", getClass().getSimpleName() + " text changed " + searchBar.getText());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

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

    @Override
    public void onSearchStateChanged(boolean enabled) {
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }

}
