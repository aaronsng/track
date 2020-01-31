package lta.amazoning.track;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * A dummy {@link RecyclerView.Adapter} that displays a list of placeholder
 * list items to the user.
 */
class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int ITEM_COUNT = 10;

    private final LayoutInflater mInflater;

    ContentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                mInflater.inflate(R.layout.view_holder_item, parent, false)) {};
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.subtitle.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }
}