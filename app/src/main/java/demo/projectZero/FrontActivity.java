package demo.projectZero;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import eu.long1.projectZero.R;
import eu.long1.spacetablayout.SpaceTabLayout;

public class FrontActivity extends AppCompatActivity {
    public SpaceTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //add the fragments you want to display in a List
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(new FragmentA());
        fragmentList.add(new FragmentB());
        fragmentList.add(new FragmentC());
        fragmentList.add(new FragmentD());

        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_front);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (SpaceTabLayout) findViewById(R.id.spaceTabLayout);

        tabLayout.initialize(viewPager, getSupportFragmentManager(), fragmentList);
//        tabLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "" + tabLayout.getCurrentPosition(), Toast.LENGTH_LONG).show();
//            }
//        });

//        tabLayout.setTabFourIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_settings, null));
    }



}
