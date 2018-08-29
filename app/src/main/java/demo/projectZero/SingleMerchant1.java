package demo.projectZero;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import eu.long1.projectZero.R;

public class SingleMerchant1 extends AppCompatActivity {

    TextView merchant1LicenseNum, merchant1Name, merchant1Address, merchant1PeopleInCharge,
    merchant1Grid, merchant1Manager, merchant1Cycle, merchant1Level, merchant1DetermineSeason,
    merchant1CheckTime, merchant1BuildCase, merchant1CaseNum, merchant1BustedAmount,
    merchant1FakeAmount, merchant1VideoDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Merchant1Theme);
        final Merchant1 m1 = (Merchant1)getIntent().getSerializableExtra("singleMerchant1");

        setTitle(m1.getMerchant1Name());
        setContentView(R.layout.activity_single_merchant1);

        merchant1Name = (TextView) findViewById(R.id.merchant1Name);
        merchant1LicenseNum = (TextView) findViewById(R.id.merchant1LicenseNum);
        merchant1Address = (TextView) findViewById(R.id.merchant1Address);
        merchant1PeopleInCharge = (TextView) findViewById(R.id.merchant1PeopleInCharge);
        merchant1Grid = (TextView) findViewById(R.id.merchant1Grid);
        merchant1Manager = (TextView) findViewById(R.id.merchant1Manager);
        merchant1Cycle = (TextView) findViewById(R.id.merchant1Cycle);
        merchant1Level = (TextView) findViewById(R.id.merchant1Level);
        merchant1DetermineSeason = (TextView) findViewById(R.id.merchant1DetermineSeason);
        merchant1CheckTime = (TextView) findViewById(R.id.merchant1CheckTime);
        merchant1BuildCase = (TextView) findViewById(R.id.merchant1BuildCase);
        merchant1CaseNum = (TextView) findViewById(R.id.merchant1CaseNum);
        merchant1BustedAmount = (TextView) findViewById(R.id.merchant1BustedAmount);
        merchant1FakeAmount = (TextView) findViewById(R.id.merchant1FakeAmount);
        merchant1VideoDate = (TextView) findViewById(R.id.merchant1VideoDate);

        merchant1Name.setText(m1.getMerchant1Name());
        merchant1LicenseNum.setText(m1.getMerchant1LicenseNum());
        merchant1Address.setText(m1.getMerchant1Address());
        merchant1Address.setPaintFlags(merchant1Address.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        merchant1PeopleInCharge.setText(m1.getMerchant1PeopleInCharge());
        merchant1Grid.setText(m1.getMerchant1Grid());
        merchant1Manager.setText(m1.getMerchant1Manager());
        merchant1Cycle.setText(m1.getMerchant1Cycle());
        merchant1Level.setText(m1.getMerchant1Level());
        merchant1DetermineSeason.setText(m1.getMerchant1DetermineSeason());
        merchant1CheckTime.setText(m1.getMerchant1CheckTime());
        merchant1BuildCase.setText(m1.getMerchant1BuildCase());
        merchant1CaseNum.setText(m1.getMerchant1CaseNum());
        merchant1BustedAmount.setText(m1.getMerchant1BustedAmount());
        merchant1FakeAmount.setText(m1.getMerchant1FakeAmount());
        merchant1VideoDate.setText(m1.getMerchant1VideoDate());

        merchant1Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MaterialDialog.Builder(SingleMerchant1.this)
                        .title("注意！")
                        .titleColorRes(R.color.primary)
                        .content("将跳转到高德地图进行导航")
                        .positiveText("确认")
                        .positiveColorRes(R.color.mb_green500)
                        .negativeText("取消")
                        .negativeColorRes(R.color.mb_green500)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                goToGaode(m1.getMerchant1Coordinate()[1], m1.getMerchant1Coordinate()[0]);
                            }
                        })
                        .show();


            }
        });

    }
    private void goToGaode(String lat, String lon) {
        StringBuilder sb = new StringBuilder("androidamap://route?sourceApplication=").append("amap");
        sb.append("&dlat=").append(lat)
                .append("&dlon=").append(lon)
                .append("&dev=").append(0)
                .append("&t=").append(0);

        Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(sb.toString()));
        intent.setPackage("com.autonavi.minimap");
        startActivity(intent);

    }
}
