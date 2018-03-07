package demo.projectZero;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import eu.long1.projectZero.R;

public class EnterInfoListActivity extends AppCompatActivity {

    private ListView listView;
    private EnterInfoListAdapter mLVAdapter;
    private ArrayList<Boolean> checkList = new ArrayList<>(
            Arrays.asList(false, false, false, false, false));
    private String firstReturnLine, secondReturnLine, thirdReturnLine;

    private static final int ACTIVITY_REQUEST_CODE_ZERO = 0;
    private static final int ACTIVITY_REQUEST_CODE_ONE = 1;
    private static final int ACTIVITY_REQUEST_CODE_TWO = 2;
    private static final int ACTIVITY_REQUEST_CODE_THIRD = 3;
    private static final int ACTIVITY_REQUEST_CODE_FORTH = 4;

    public static final int REQUEST_WRITE_STORAGE = 112;

    public DatabaseHandler db;

    private String casePath = "/ProjectZeroCase/";
    private String caseID;

    private List<Bean> list = Arrays.asList(
            new Bean(R.drawable.ic_check_box_outline, "案件性质、地区、案卷号"),
            new Bean(R.drawable.ic_check_box_outline, "案由"),
            new Bean(R.drawable.ic_check_box_outline, "当事人信息"),
            new Bean(R.drawable.ic_check_box_outline, "涉案卷烟登记"),
            new Bean(R.drawable.ic_check_box_outline, "拍照"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_view);

        SharedPreferences logInPreference= getSharedPreferences("loginPrefs", MODE_PRIVATE);;
        String badgeNum = logInPreference.getString("badgeNum", null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
        String currentDateTime = sdf.format(new Date());
        casePath += badgeNum + "_" + currentDateTime;
//        Log.w("parse", badgeNum + currentDateTime);

        //too long for a integer

        caseID = badgeNum + "_" + currentDateTime;
        Log.w("caseID", caseID);
        db = new DatabaseHandler(this);

        listView = (ListView) findViewById(R.id.lv);
        mLVAdapter = new EnterInfoListAdapter(this, list, R.layout.item_list_view_1, R.layout.item_list_view_1);
        listView.setAdapter(mLVAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (list.get(position).getName().equals("案件性质、地区、案卷号")) {
                        Intent i0 = new Intent(getApplicationContext(), CharacterActivity.class);
                        startActivityForResult(i0, ACTIVITY_REQUEST_CODE_ZERO);
                    }
                    if(list.get(position).getName().equals("案由")) {
                        Intent i1 = new Intent(getApplicationContext(), ReasonListActivity.class);
                        startActivityForResult(i1, ACTIVITY_REQUEST_CODE_ONE);
                    }
                    if(list.get(position).getName().equals("当事人信息")) {
                        Intent i2 = new Intent(getApplicationContext(), TargetActivity.class);
                        startActivityForResult(i2, ACTIVITY_REQUEST_CODE_TWO);
                    }
                    if(list.get(position).getName().equals("涉案卷烟登记")) {
                        Intent i3 = new Intent(getApplicationContext(), CigaretteLogActivity.class);
                        startActivityForResult(i3, ACTIVITY_REQUEST_CODE_THIRD);
                    }
                    if(list.get(position).getName().equals("拍照")) {
                        Intent i4 = new Intent(getApplicationContext(), PhotoActivity.class);
                        startActivityForResult(i4, ACTIVITY_REQUEST_CODE_FORTH);
                    }
            }
        });
        Log.w("casePath", casePath);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_CODE_ZERO) {
            if (resultCode == RESULT_OK) {
                firstReturnLine = data.getStringExtra("firstLine");
                setImageAndCheckList(0, true);
            }
        }
        if (requestCode == ACTIVITY_REQUEST_CODE_ONE) {
            if (resultCode == RESULT_OK) {
                secondReturnLine = data.getStringExtra("secondLine");
                setImageAndCheckList(1, true);
            } else {
                secondReturnLine = null;
                setImageAndCheckList(1, false);
            }
        }
        if (requestCode == ACTIVITY_REQUEST_CODE_TWO) {
            if(resultCode == RESULT_OK) {
                thirdReturnLine = data.getStringExtra("thirdLine");
                setImageAndCheckList(2, true);
            } else {
                setImageAndCheckList(2, false);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_all_finish, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_all_finish:
//                Toast.makeText(getApplicationContext(),
//                        firstReturnLine + secondReturnLine + thirdReturnLine,
//                        Toast.LENGTH_LONG).show();
                Log.w("finish",
                        firstReturnLine + secondReturnLine + thirdReturnLine);

                Log.w("db test", "add new case");
                if (db.checkExist(caseID)) {
//                    db.addCase(new SingleCase(caseID, firstReturnLine, secondReturnLine));
                    Toast.makeText(getApplicationContext(),
                            "案件已经存在！请勿重复添加", Toast.LENGTH_LONG).show();
                } else if (checkList.get(0) && checkList.get(1)) {
                    db.addCase(new SingleCase(caseID, firstReturnLine, secondReturnLine));
                } else {
                    Toast.makeText(getApplicationContext(),
                            "请完成录入所有信息", Toast.LENGTH_LONG).show();
                }

//                SingleCase temp = db.getCase("364052_201802270854");
//                Log.w("db getCase", temp.getCharacter() + temp.getReason());
//                requestPermission(this);
//                createDir(casePath);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "The app was allowed to write to your storage!", Toast.LENGTH_LONG).show();
                    // Reload the activity with permission granted or use the features what required the permission
                } else {
                    Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void setImageAndCheckList(Integer index, Boolean state) {
        View single = listView.getChildAt(index);
        ImageView imgView = (ImageView) single.findViewById(R.id.img);
        if (state) {
            imgView.setImageResource(R.drawable.ic_check_box);
            checkList.set(index, true);
        } else {
            imgView.setImageResource(R.drawable.ic_check_box_outline);
            checkList.set(index, false);
        }
    }

    private void requestPermission(Activity context) {
        boolean hasPermission = (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        } else {
            // You are allowed to write external storage:
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + casePath;
            File storageDir = new File(path);
            Log.w("request permission", "write successful");
            if (!storageDir.exists() && !storageDir.mkdirs()) {
                // This should never happen - log handled exception!
                Log.e("request permission", "external storage request failed!");
            }
        }
    }

    private void createDir(String path) {
//        try{
//            File mydir = getApplicationContext().getDir(path, Context.MODE_PRIVATE); //Creating an internal dir;
//            File fileWithinMyDir = new File(mydir, "myTestFile"); //Getting a file within the dir.
//            FileOutputStream out = new FileOutputStream(fileWithinMyDir);
//            out.write("lalala".getBytes());
//            out.close();
//            Log.w("createDir", "Code seems fine");
//        } catch (Exception e) {
//            Log.e("createDir", e.getMessage());
//        }
        try {

            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write("lalala");
            outputWriter.close();
            Log.w("file", "write successful");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
