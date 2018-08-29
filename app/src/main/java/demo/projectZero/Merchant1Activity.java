package demo.projectZero;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import eu.long1.projectZero.R;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Merchant1Activity extends AppCompatActivity {

    private MaterialSearchBar searchBar;
    String filePath = "";
    ArrayList<Merchant1> allMerchants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Merchant1Theme);
        setContentView(R.layout.activity_merchant1);
        searchBar = (MaterialSearchBar) findViewById(R.id.searchBarMerchant1);

        filePath = getIntent().getExtras().getString("filePath");

        ArrayList<MerchantDisplayInfo> searchResults = getMerchantResult();
        final ListView lv = (ListView) findViewById(R.id.merchant1ListView);
        lv.setAdapter(new MyCustomBaseAdapter(this, searchResults));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv.getItemAtPosition(position);
                MerchantDisplayInfo fullObject = (MerchantDisplayInfo)o;
//                Toast.makeText(getApplicationContext(), fullObject.getMerchantName(), Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(), readExcelToList(filePath), Toast.LENGTH_LONG).show();
//                readExcelToList(filePath);
                Intent i = new Intent(getApplicationContext(), SingleMerchant1.class);
                i.putExtra("singleMerchant1", allMerchants.get(fullObject.getIndex()));
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
//                Toast.makeText(getApplicationContext(), searchBar.getText(), Toast.LENGTH_LONG).show();
                String searchInput = searchBar.getText();

                for (Merchant1 m1 : allMerchants) {
                    if (m1.getMerchant1Name().contains(searchInput) || m1.getMerchant1LicenseNum().contains(searchInput)) {

                        Intent i = new Intent(getApplicationContext(), SingleMerchant1.class);
                        i.putExtra("singleMerchant1", m1);
                        startActivity(i);
                    }
                }
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

    private ArrayList<Merchant1> readExcelToList(String pathToFile) {
        ArrayList<Merchant1> result = new ArrayList<>();

        try {
            Workbook wb = Workbook.getWorkbook(new File(pathToFile));
            Sheet sheet = wb.getSheet(0);
            Integer rowNumber = sheet.getRows();
            for (Integer row = 1; row < rowNumber; row++) {
                Merchant1 m1 = new Merchant1();
                m1.setMerchant1LicenseNum(sheet.getCell(1, row).getContents());
                m1.setMerchant1Name(sheet.getCell(2, row).getContents());
                m1.setMerchant1PeopleInCharge(sheet.getCell(3, row).getContents());
                m1.setMerchant1Address(sheet.getCell(4, row).getContents());
                String[] tempCoordinate = {sheet.getCell(5, row).getContents(), sheet.getCell(6, row).getContents()};
                m1.setMerchant1Coordinate(tempCoordinate);
                m1.setMerchant1Grid(sheet.getCell(7, row).getContents());
                m1.setMerchant1Manager(sheet.getCell(8, row).getContents());
                m1.setMerchant1Cycle(sheet.getCell(9, row).getContents());
                m1.setMerchant1Level(sheet.getCell(10, row).getContents());
                m1.setMerchant1DetermineSeason(sheet.getCell(11, row).getContents());
                m1.setMerchant1CheckTime(sheet.getCell(12, row).getContents());
                m1.setMerchant1BuildCase(sheet.getCell(13, row).getContents());
                m1.setMerchant1CaseNum(sheet.getCell(14, row).getContents());
                m1.setMerchant1BustedAmount(sheet.getCell(15, row).getContents());
                m1.setMerchant1FakeAmount(sheet.getCell(16, row).getContents());
                m1.setMerchant1VideoDate(sheet.getCell(17, row).getContents());
                result.add(m1);
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
        for (Merchant1 m1 : allMerchants) {
            MerchantDisplayInfo mi = new MerchantDisplayInfo();
            mi.setMerchantName(m1.getMerchant1Name());
            mi.setMerchantLicenseNum(m1.getMerchant1LicenseNum());
            mi.setMerchantAddress(m1.getMerchant1Address());
            mi.setIndex(allMerchants.indexOf(m1));
            results.add(mi);
        }
        return results;
    }

}
