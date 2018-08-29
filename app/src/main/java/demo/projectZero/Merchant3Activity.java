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

public class Merchant3Activity extends AppCompatActivity {

    private MaterialSearchBar searchBar;
    String filePath = "";
    ArrayList<Merchant3> allMerchants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Merchant3Theme);
        setContentView(R.layout.activity_merchant3);
        searchBar = (MaterialSearchBar) findViewById(R.id.searchBarMerchant3);

        filePath = getIntent().getExtras().getString("filePath");

        ArrayList<MerchantDisplayInfo> searchResults = getMerchantResult();
        final ListView lv = (ListView) findViewById(R.id.merchant3ListView);
        lv.setAdapter(new MyCustomBaseAdapter(this, searchResults));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv.getItemAtPosition(position);
                MerchantDisplayInfo fullObject = (MerchantDisplayInfo)o;

//                Toast.makeText(getApplicationContext(), fullObject.getMerchantName(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), SingleMerchant3.class);
                i.putExtra("singleMerchant3", allMerchants.get(fullObject.getIndex()));
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
                for (Merchant3 m3 : allMerchants) {
                    if (m3.getMerchant3Name().contains(searchInput) || m3.getMerchant3LicenseNum().contains(searchInput)) {
                        Intent i = new Intent(getApplicationContext(), SingleMerchant1.class);
                        i.putExtra("singleMerchant1", m3);
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

    private ArrayList<Merchant3> readExcelToList(String pathToFile) {
        ArrayList<Merchant3> result = new ArrayList<>();

        try {
            Workbook wb = Workbook.getWorkbook(new File(pathToFile));
            Sheet sheet = wb.getSheet(2);
            Integer rowNumber = sheet.getRows();
            for (Integer row = 1; row < rowNumber; row++) {
                Merchant3 m3 = new Merchant3();
                m3.setmerchant3LicenseNum(sheet.getCell(1, row).getContents());
                m3.setmerchant3Name(sheet.getCell(2, row).getContents());
                m3.setmerchant3PeopleInCharge(sheet.getCell(3, row).getContents());
                m3.setmerchant3Address(sheet.getCell(4, row).getContents());
                String[] tempCoordinate = {sheet.getCell(5, row).getContents(), sheet.getCell(6, row).getContents()};
                m3.setmerchant3Coordinate(tempCoordinate);
                m3.setmerchant3Grid(sheet.getCell(7, row).getContents());
                m3.setmerchant3Manager(sheet.getCell(8, row).getContents());
                m3.setmerchant3Cycle(sheet.getCell(9, row).getContents());
                m3.setmerchant3Level(sheet.getCell(10, row).getContents());
                m3.setMerchant3Situation(sheet.getCell(11, row).getContents());
                m3.setMerchant3Operation(sheet.getCell(12, row).getContents());
                m3.setMerchant3Closure(sheet.getCell(13, row).getContents());
                m3.setMerchant3Order(sheet.getCell(14, row).getContents());
                m3.setMerchant3licenseStatus(sheet.getCell(15, row).getContents());
                result.add(m3);
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
        for (Merchant3 m3 : allMerchants) {
            MerchantDisplayInfo mi = new MerchantDisplayInfo();

            mi.setMerchantName(m3.getMerchant3Name());
            mi.setMerchantLicenseNum(m3.getMerchant3LicenseNum());
            mi.setMerchantAddress(m3.getMerchant3Address());
            mi.setIndex(allMerchants.indexOf(m3));
//            Log.e("coordinate: ", m3.getMerchant3Coordinate()[0] + ", " + m3.getMerchant3Coordinate()[1]);
            results.add(mi);
        }
        return results;
    }
}
