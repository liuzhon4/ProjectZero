package eu.long1.projectZero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
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
import de.mrapp.android.dialog.ProgressDialog;

public class FragmentB extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        getActionBar().setTitle("零售户信息查询");
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Add your menu entries here
        inflater.inflate(R.menu.menu_update, menu);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("零售户信息查询");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_update:
//                Toast.makeText(getActivity().getApplication(), "零售户信息更新成功", Toast.LENGTH_SHORT).show();
                ProgressDialog.Builder dialogBuilder = new ProgressDialog.Builder(getContext());
                dialogBuilder
                        .setTitle("注意")
                        .setMessage("零售户信息更新成功")
                        .setPositiveButton("确认", null)
                        .setProgressBarPosition(ProgressDialog.ProgressBarPosition.LEFT)
                        .create()
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
