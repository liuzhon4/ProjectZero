package eu.long1.projectZero;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import de.mrapp.android.dialog.MaterialDialog;


public class FragmentC extends Fragment {
    public DatabaseHandler db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        db = new DatabaseHandler(getActivity().getApplicationContext());

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Add your menu entries here
        inflater.inflate(R.menu.menu_add_new, menu);
        inflater.inflate(R.menu.menu_print, menu);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("现场执法文书办理");
//        getActionBar().setTitle("现场执法文书办理");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_print:
                Toast.makeText(getActivity().getApplication(), "print button is pressed", Toast.LENGTH_SHORT).show();
//                SingleCase temp = db.getCase("364052_201802270910");
//                Log.w("SQLite", temp.getCharacter() + temp.getReason());
                return true;
            case R.id.action_add_new:
//                Toast.makeText(getActivity().getApplication(), "add button is pressed", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), EnterInfoListActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

