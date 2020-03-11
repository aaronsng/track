package lta.amazoning.track;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class InspectionOverview extends Fragment {

    private final String CLASS_NAME = "InspectionOverview";
    // True iff the shadow view between the card header and the RecyclerView
    // is currently showing.
    private FragmentActivity mFrgAct;
    private Intent mIntent;
    private UploadFragment upload_instance;

    private boolean mIsShowingCardHeaderShadow;
    public boolean onUploadCompleted;
    private boolean mIsRestoredFromBackstack;

    private ContentAdapter defectAdapter;
    private JSONObject jsonObject;
    public List<JSONObject> defectDetails;
    private List<String> defectDetail;

    private TextView inspectionSum;
    private FloatingActionButton fab;
    private Button leftButton;
    private Button middleButton;
    private Button rightButton;

    public boolean navigated_upload = false;

    public InspectionOverview(FloatingActionButton fab, Button leftButton, Button middleButton, Button rightButton, Context context) {
        this.fab = fab;
        this.leftButton = leftButton;
        this.middleButton = middleButton;
        this.rightButton = rightButton;
        defectAdapter = new ContentAdapter(context, defectDetail);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsRestoredFromBackstack = false;
    }

    public void setInspectionStatus(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.content_inspection_overview, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        defectDetails = new ArrayList<>();
        String start = "", end = "";
        try {
            start = jsonObject.get("Start").toString();
            end = jsonObject.get("End").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        inspectionSum = view.findViewById(R.id.cardo_title);
        inspectionSum.setText(inspectionSum.getText().toString() + " " + start + " to " + end);

        final RecyclerView rv = view.findViewById(R.id.card_recyclerview);
        final LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);
        rv.setAdapter(defectAdapter);
        rv.setActivated(true);
        //rv.addItemDecoration(new DividerItemDecoration(getContext(), lm.getOrientation()));

        final NestedScrollView nsv = view.findViewById(R.id.nestedscrollview);
        nsv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(
                    NestedScrollView nsv, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == 0 && oldScrollY > 0) {
                    // Reset the RecyclerView's scroll position each time the card
                    // returns to its starting position.
                    rv.scrollToPosition(0);
                    //cardHeaderShadow.setAlpha(0f);
                    mIsShowingCardHeaderShadow = true;
                }
            }
        });

        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNewInspection(fab, leftButton, middleButton, rightButton);
            }
        });

        if(mIsRestoredFromBackstack)
        {
            // Set the bottom app bar back to normal
            leftButton.setVisibility(View.VISIBLE);
            middleButton.setVisibility(View.INVISIBLE);
            rightButton.setVisibility(View.VISIBLE);
            fab.show();

            // Add defect detail to the list
            if (upload_instance.defectDetails == null) {
                Log.e(CLASS_NAME, "Defect Details NULL!");
            }
            defectDetails.add(upload_instance.defectDetails);

            // Notify ContentAdapter of the changes
            try {
                Log.i(CLASS_NAME, convertToList(defectDetails).toString());
                rv.setAdapter(new ContentAdapter(getContext(), convertToList(defectDetails)));
                rv.setActivated(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public List<String> convertToList(List<JSONObject> defectDetails) throws JSONException {
        Integer ITEM_COUNT = defectDetails.size();
//        Log.i(CLASS_NAME, "ITEM_COUNT: " + String.valueOf(ITEM_COUNT));
        List<String>  localeGroup = new ArrayList<>();

        if (ITEM_COUNT == null) {
            return null;
        }

        for (int i = 0; i < ITEM_COUNT; i++) {
            JSONObject defectDetail = defectDetails.get(i);
            String desc = defectDetail.get("others").toString();
            Log.i(CLASS_NAME, desc);

            localeGroup.add(desc);
        }
        return localeGroup;
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mIsRestoredFromBackstack = true;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFrgAct = getActivity();
        mIntent = mFrgAct.getIntent(); //  Intent intent = new Intent(getActivity().getIntent());
    }

    public void launchNewInspection(FloatingActionButton fab, Button leftButton, Button middleButton, Button rightButton) {
        upload_instance = new UploadFragment(fab, leftButton, middleButton, rightButton);
        FragmentTransaction ft = mFrgAct.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.homepage, upload_instance);
        ft.addToBackStack("Upload");
        navigated_upload = true;
        Log.i(CLASS_NAME, "Back stack count " + Integer.toString(mFrgAct.getSupportFragmentManager().getBackStackEntryCount()));
        ft.commit();
    }
}
