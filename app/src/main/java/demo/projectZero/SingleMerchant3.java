package demo.projectZero;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import eu.long1.projectZero.R;

public class SingleMerchant3 extends AppCompatActivity {

    private TextView merchant3LicenseNum, merchant3Name, merchant3Address, merchant3PeopleInCharge,
            merchant3Grid, merchant3Manager, merchant3Cycle, merchant3Level, merchant3Situation,
            merchant3Operation, merchant3Closure, merchant3Order, merchant3LicenseStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Merchant3Theme);
        final Merchant3 m3 = (Merchant3)getIntent().getSerializableExtra("singleMerchant3");

        setTitle(m3.getMerchant3Name());
        setContentView(R.layout.activity_single_merchant3);

        merchant3LicenseNum = (TextView) findViewById(R.id.merchant3LicenseNum);
        merchant3Name = (TextView) findViewById(R.id.merchant3Name);
        merchant3Address = (TextView) findViewById(R.id.merchant3Address);
        merchant3PeopleInCharge = (TextView) findViewById(R.id.merchant3PeopleInCharge);
        merchant3Grid = (TextView) findViewById(R.id.merchant3Grid);
        merchant3Manager = (TextView) findViewById(R.id.merchant3Manager);
        merchant3Cycle = (TextView) findViewById(R.id.merchant3Cycle);
        merchant3Level = (TextView) findViewById(R.id.merchant3Level);
        merchant3Situation = (TextView) findViewById(R.id.merchant3Situation);
        merchant3Operation = (TextView) findViewById(R.id.merchant3Operation);
        merchant3Closure = (TextView) findViewById(R.id.merchant3Closure);
        merchant3Order = (TextView) findViewById(R.id.merchant3Order);
        merchant3LicenseStatus = (TextView) findViewById(R.id.merchant3LicenseStatus);

        merchant3LicenseNum.setText(m3.getMerchant3LicenseNum());
        merchant3Name.setText(m3.getMerchant3Name());
        merchant3Address.setText(m3.getMerchant3Address());
        merchant3Address.setPaintFlags(merchant3Address.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        merchant3PeopleInCharge.setText(m3.getMerchant3PeopleInCharge());
        merchant3Grid.setText(m3.getMerchant3Grid());
        merchant3Manager.setText(m3.getMerchant3Manager());
        merchant3Cycle.setText(m3.getMerchant3Cycle());
        merchant3Level.setText(m3.getMerchant3Level());
        merchant3Situation.setText(m3.getMerchant3Situation());
        merchant3Operation.setText(m3.getMerchant3Operation());
        merchant3Closure.setText(m3.getMerchant3Closure());
        merchant3Order.setText(m3.getMerchant3Order());
        merchant3LicenseStatus.setText(m3.getMerchant3LicenseNum());

        merchant3Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MaterialDialog.Builder(SingleMerchant3.this)
                        .title("注意！")
                        .titleColorRes(R.color.primary)
                        .content("将跳转到高德地图进行导航")
                        .positiveText("确认")
                        .positiveColorRes(R.color.mb_cyan500)
                        .negativeText("取消")
                        .negativeColorRes(R.color.mb_cyan500)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                goToGaode(m3.getMerchant3Coordinate()[1], m3.getMerchant3Coordinate()[0]);
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
