package lta.amazoning.track;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InspectionOverview extends AppCompatActivity {

    // True iff the shadow view between the card header and the RecyclerView
    // is currently showing.
    private boolean mIsShowingCardHeaderShadow;
    private Toolbar tool_bar;
    private TextView inspectionSum;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private String currentInspectionString;
    private boolean isFABOpen;

    private String idFault = "123-456";
    private String LOCATION = "PGL to SKG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_overview);
        isFABOpen = false;

        inspectionSum = findViewById(R.id.card_title);
        tool_bar = findViewById(R.id.tool_bar);

        SpannableString content = new SpannableString(LOCATION);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        inspectionSum.setText(content);
        tool_bar.setTitle("Inspection #" + idFault);


        setSupportActionBar(tool_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final RecyclerView rv = findViewById(R.id.card_recyclerview);
        final LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(new ContentAdapter(this));
        rv.setActivated(true);
        rv.addItemDecoration(new DividerItemDecoration(this, lm.getOrientation()));

        final View cardHeaderShadow = findViewById(R.id.card_header_shadow);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                // Animate the shadow view in/out as the user scrolls so that it
                // looks like the RecyclerView is scrolling beneath the card header.
                final boolean isRecyclerViewScrolledToTop =
                        lm.findFirstVisibleItemPosition() == 0
                                && lm.findViewByPosition(0).getTop() == 0;
                if (!isRecyclerViewScrolledToTop && !mIsShowingCardHeaderShadow) {
                    mIsShowingCardHeaderShadow = true;
                    showOrHideView(cardHeaderShadow, true);
                } else if (isRecyclerViewScrolledToTop && mIsShowingCardHeaderShadow) {
                    mIsShowingCardHeaderShadow = false;
                    showOrHideView(cardHeaderShadow, false);
                }
            }
        });

<<<<<<< HEAD
        FloatingActionButton fab = findViewById(R.id.fab);
=======
        /*FloatingActionButton fab = findViewById(R.id.fab);
>>>>>>> 7f6f5e463955e564db16307c8581caddf5017b2b
=======
        /*FloatingActionButton fab = findViewById(R.id.fab);
>>>>>>> 49e7228c4f9c3b3b1b3e1d8637da5adce1072c58
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
<<<<<<< HEAD
<<<<<<< HEAD
        });
=======
=======
>>>>>>> 49e7228c4f9c3b3b1b3e1d8637da5adce1072c58
        });*/

        final NestedScrollView nsv = findViewById(R.id.nestedscrollview);
        nsv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(
                    NestedScrollView nsv, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == 0 && oldScrollY > 0) {
                    // Reset the RecyclerView's scroll position each time the card
                    // returns to its starting position.
                    rv.scrollToPosition(0);
                    cardHeaderShadow.setAlpha(0f);
                    mIsShowingCardHeaderShadow = true;
                }
            }
        });
    }

    private static void showOrHideView(View view, boolean shouldShow) {
        view.animate().alpha(shouldShow ? 1f : 0f)
                .setDuration(100)
                .setInterpolator(new DecelerateInterpolator());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFABMenu(){
        isFABOpen = true;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
    }

    private void closeFABMenu(){
        isFABOpen = false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
        fab3.animate().translationY(0);
    }
}
