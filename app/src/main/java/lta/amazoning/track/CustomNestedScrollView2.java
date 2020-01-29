package lta.amazoning.track;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomNestedScrollView2 extends NestedScrollView2 {

    public CustomNestedScrollView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed, int type) {
        final RecyclerView rv = (RecyclerView) target;
        if ((dy < 0 && isRvScrolledToTop(rv)) || (dy > 0 && !isNsvScrolledToBottom(this))) {
            scrollBy(0, dy);
            consumed[1] = dy;
            return;
        }
        super.onNestedPreScroll(target, dx, dy, consumed, type);
    }

    // Note that we no longer need to override onNestedPreFling() here; the
    // new-and-improved nested scrolling APIs give us the nested flinging
    // behavior we want already by default!

    private static boolean isNsvScrolledToBottom(NestedScrollView nsv) {
        return !nsv.canScrollVertically(1);
    }

    private static boolean isRvScrolledToTop(RecyclerView rv) {
        final LinearLayoutManager lm = (LinearLayoutManager) rv.getLayoutManager();
        return lm.findFirstVisibleItemPosition() == 0
                && lm.findViewByPosition(0).getTop() == 0;
    }
}
