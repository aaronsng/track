package lta.amazoning.track;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InspectionOverview extends Fragment {

    // True iff the shadow view between the card header and the RecyclerView
    // is currently showing.
    private FragmentActivity mFrgAct;
    private Intent mIntent;
    private boolean mIsShowingCardHeaderShadow;
    private TextView inspectionSum;

    private String LOCATION = "PGL to SKG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.content_inspection_overview, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        inspectionSum = view.findViewById(R.id.cardo_title);

        SpannableString content = new SpannableString(LOCATION);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        inspectionSum.setText(inspectionSum.getText().toString() + " " + content);

        final RecyclerView rv = view.findViewById(R.id.card_recyclerview);
        final LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);
        rv.setAdapter(new ContentAdapter(getContext()));
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
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFrgAct = getActivity();
        mIntent = mFrgAct.getIntent(); //  Intent intent = new Intent(getActivity().getIntent());
    }
}
