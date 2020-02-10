package lta.amazoning.track;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomepageFragment extends Fragment {

    // True iff the shadow view between the card header and the RecyclerView
    // is currently showing.
    public boolean navigated_inspection = false;
    private FragmentActivity mFrgAct;
    private Button button;
    private Intent mIntent;
    private FloatingActionButton fab;
    private Button left_button;
    private Button middle_button;
    private Button right_button;

    public HomepageFragment(FloatingActionButton fab, Button left_button, Button middle_button, Button right_button) {
        this.fab = fab;
        this.left_button = left_button;
        this.middle_button = middle_button;
        this.right_button = right_button;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_homepage3, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        button = view.findViewById(R.id.inspection_button);

        left_button.setText("Settings");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewInspectionFragment();
            }
        });
        button = view.findViewById(R.id.logout_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View cv){
                openMainActivity();
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFrgAct = getActivity();
        mIntent = mFrgAct.getIntent(); //  Intent intent = new Intent(getActivity().getIntent());
    }

    public void openNewInspectionFragment(){
        FragmentTransaction ft = mFrgAct.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.homepage, new NewInspectionFragment());
        ft.commit();
        navigated_inspection = true;
    }

    public void openInspectionOverview() {
        FragmentTransaction ft = mFrgAct.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.homepage, new InspectionOverview(this.fab, this.left_button, this.middle_button, this.right_button));
        ft.commit();
        navigated_inspection = true;
    }

    public void openMainActivity(){
        Intent intent = new Intent(mFrgAct.getApplicationContext(), MainActivity.class);
        mFrgAct.startActivity(intent);
    }

}