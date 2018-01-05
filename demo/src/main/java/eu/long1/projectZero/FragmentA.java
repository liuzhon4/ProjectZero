package eu.long1.projectZero;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentA extends Fragment {
    private View view;
    private Button mButton;
    private EditText mLast16Text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_a, container, false);
        mButton = (Button) view.findViewById(R.id.searchButton);
        mLast16Text = (EditText) view.findViewById(R.id.last16View);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), mLast16Text.getText(), Toast.LENGTH_LONG).show();
            }
        });
        return view;

    }


}
