package demo.projectZero;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import eu.long1.projectZero.R;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Merchant2Activity extends AppCompatActivity {

    private MaterialSearchBar searchBar;
    String filePath = "";
    ArrayList<Merchant2> allMerchants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Merchant2Theme);
        setContentView(R.layout.activity_merchant2);
        searchBar = (MaterialSearchBar) findViewById(R.id.searchBarMerchant2);

        filePath = getIntent().getExtras().getString("filePath");

        ArrayList<MerchantDisplayInfo> searchResults = getMerchantResult();
        final ListView lv = (ListView) findViewById(R.id.merchant2ListView);
        lv.setAdapter(new MyCustomBaseAdapter(this, searchResults));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv.getItemAtPosition(position);
                MerchantDisplayInfo fullObject = (MerchantDisplayInfo)o;
//                Toast.makeText(getApplicationContext(), fullObject.getMerchantName(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), SingleMerchant2.class);
                i.putExtra("singleMerchant2", allMerchants.get(fullObject.getIndex()));
                startActivity(i);
            }
        });

        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                InputMethodManager inputManager = (InputMethodManager)
                        getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null :
                        getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                Toast.makeText(getApplicationContext(), searchBar.getText(), Toast.LENGTH_LONG).show();
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
    }

    private ArrayList<Merchant2> readExcelToList(String pathToFile) {
        ArrayList<Merchant2> result = new ArrayList<>();

        try {
            Workbook wb = Workbook.getWorkbook(new File(pathToFile));
            Sheet sheet = wb.getSheet(1);
            Integer rowNumber = sheet.getRows();
            for (Integer row = 1; row < rowNumber; row++) {
                Merchant2 m2 = new Merchant2();
                m2.setMerchant2Name(sheet.getCell(1, row).getContents());
                m2.setMerchant2Address(sheet.getCell(2, row).getContents());
                m2.setMerchant2PeopleInCharge(sheet.getCell(5, row).getContents());
                m2.setMerchant2Home(sheet.getCell(6, row).getContents());
                String[] tempCoordinate = {sheet.getCell(3, row).getContents(), sheet.getCell(4, row).getContents()};
                m2.setMerchant2Coordinate(tempCoordinate);
                m2.setMerchant2PhoneNum(sheet.getCell(7, row).getContents());
                m2.setMerchant2StartTime(sheet.getCell(8, row).getContents());
                m2.setMerchant2RegisterTime(sheet.getCell(9, row).getContents());
                m2.setMerchant2CurrentStatus(sheet.getCell(10, row).getContents());
                m2.setMerchant2ChangeTime(sheet.getCell(11, row).getContents());
                m2.setMerchant2CommercialLicense(sheet.getCell(12, row).getContents());
                m2.setMerchant2NearSchool(sheet.getCell(13, row).getContents());
                m2.setMerchant2Relocation(sheet.getCell(14, row).getContents());
                m2.setMerchant2Willingness(sheet.getCell(15, row).getContents());
                m2.setMerchant2LocationProperty(sheet.getCell(16, row).getContents());
                m2.setMerchant2AbleToApply(sheet.getCell(17, row).getContents());
                m2.setMerchant2Channel(sheet.getCell(18, row).getContents());
                m2.setMerchant2Remaining(sheet.getCell(19, row).getContents());
                m2.setMerchant2Comments(sheet.getCell(20, row).getContents());
                result.add(m2);
            }

        } catch (BiffException e) {
            Log.e("Workbook Exception", e.getMessage());
        } catch (IOException e) {
            Log.e("IOException", e.getMessage());
        }
        return result;
    }


    private ArrayList<MerchantDisplayInfo> getMerchantResult() {
        ArrayList<MerchantDisplayInfo> results = new ArrayList<>();
        allMerchants = readExcelToList(filePath);
        for (Merchant2 m2 : allMerchants) {
            MerchantDisplayInfo mi = new MerchantDisplayInfo();
            mi.setMerchantName(m2.getMerchant2Name());
            mi.setMerchantLicenseNum("无证户");
            mi.setMerchantAddress(m2.getMerchant2Address());
            mi.setIndex(allMerchants.indexOf(m2));
            results.add(mi);
        }
        return results;

    }
}
