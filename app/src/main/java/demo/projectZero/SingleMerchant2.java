package demo.projectZero;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import eu.long1.projectZero.R;

public class SingleMerchant2 extends AppCompatActivity {

    TextView merchant2Name, merchant2Address, merchant2PeopleInCharge, merchant2Home,
            merchant2PhoneNum, merchant2StartTime, merchant2RegisterTime, merchant2CurrentStatus,
            merchant2ChangeTime, merchant2CommercialLicense, merchant2NearSchool,
            merchant2Relocation, merchant2Willingness, merchant2LocationProperty,
            merchant2AbleToApply, merchant2Channel, merchant2Remaining, merchant2Comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Merchant2Theme);
        final Merchant2 m2 = (Merchant2)getIntent().getSerializableExtra("singleMerchant2");

        setTitle(m2.getMerchant2Name());
        setContentView(R.layout.activity_single_merchant2);

        merchant2Name = (TextView) findViewById(R.id.merchant2Name);
        merchant2Address = (TextView) findViewById(R.id.merchant2Address);
        merchant2PeopleInCharge = (TextView) findViewById(R.id.merchant2peopleInCharge);
        merchant2Home = (TextView) findViewById(R.id.merchant2Home);
        merchant2PhoneNum = (TextView) findViewById(R.id.merchant2PhoneNum);
        merchant2StartTime = (TextView) findViewById(R.id.merchant2StartTime);
        merchant2RegisterTime = (TextView) findViewById(R.id.merchant2RegisterTime);
        merchant2CurrentStatus = (TextView) findViewById(R.id.merchant2CurrentStatus);
        merchant2ChangeTime = (TextView) findViewById(R.id.merchant2ChangeTime);
        merchant2CommercialLicense = (TextView) findViewById(R.id.merchant2CommercialLicense);
        merchant2NearSchool = (TextView) findViewById(R.id.merchant2NearSchool);
        merchant2Relocation = (TextView) findViewById(R.id.merchant2Relocation);
        merchant2Willingness = (TextView) findViewById(R.id.merchant2Willingness);
        merchant2LocationProperty = (TextView) findViewById(R.id.merchant2LocationProperty);
        merchant2AbleToApply = (TextView) findViewById(R.id.merchant2AbleToApply);
        merchant2Channel = (TextView) findViewById(R.id.merchant2Channel);
        merchant2Remaining = (TextView) findViewById(R.id.merchant2Remaining);
        merchant2Comments = (TextView) findViewById(R.id.merchant2Comments);

        merchant2Name.setText(m2.getMerchant2Name());
        merchant2Address.setText(m2.getMerchant2Address());
        merchant2Address.setPaintFlags(merchant2Address.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        merchant2PeopleInCharge.setText(m2.getMerchant2PeopleInCharge());
        merchant2Home.setText(m2.getMerchant2Home());
        merchant2PhoneNum.setText(m2.getMerchant2PhoneNum());
        merchant2StartTime.setText(m2.getMerchant2StartTime());
        merchant2RegisterTime.setText(m2.getMerchant2RegisterTime());
        merchant2CurrentStatus.setText(m2.getMerchant2CurrentStatus());
        merchant2ChangeTime.setText(m2.getMerchant2ChangeTime());
        merchant2CommercialLicense.setText(m2.getMerchant2CommercialLicense());
        merchant2NearSchool.setText(m2.getMerchant2NearSchool());
        merchant2Relocation.setText(m2.getMerchant2Relocation());
        merchant2Willingness.setText(m2.getMerchant2Willingness());
        merchant2LocationProperty.setText(m2.getMerchant2LocationProperty());
        merchant2AbleToApply.setText(m2.getMerchant2AbleToApply());
        merchant2Channel.setText(m2.getMerchant2Channel());
        merchant2Remaining.setText(m2.getMerchant2Remaining());
        merchant2Comments.setText(m2.getMerchant2Comments());

        merchant2Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(SingleMerchant2.this)
                        .title("注意！")
                        .titleColorRes(R.color.primary)
                        .content("将跳转到高德地图进行导航")
                        .positiveText("确认")
                        .positiveColorRes(R.color.mb_red500)
                        .negativeText("取消")
                        .negativeColorRes(R.color.mb_red500)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                goToGaode(m2.getMerchant2Coordinate()[1], m2.getMerchant2Coordinate()[0]);
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
