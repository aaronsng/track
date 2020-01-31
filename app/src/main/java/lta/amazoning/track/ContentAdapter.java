package lta.amazoning.track;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * A dummy {@link RecyclerView.Adapter} that displays a list of placeholder
 * list items to the user.
 */
<<<<<<< HEAD
class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_COUNT = 5;
=======
class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int ITEM_COUNT = 10;
>>>>>>> 7f6f5e463955e564db16307c8581caddf5017b2b

    private final LayoutInflater mInflater;

    ContentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
<<<<<<< HEAD
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
=======
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
>>>>>>> 7f6f5e463955e564db16307c8581caddf5017b2b
                mInflater.inflate(R.layout.view_holder_item, parent, false)) {};
    }

    @Override
<<<<<<< HEAD
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

=======
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.subtitle.setText(String.valueOf(position));
>>>>>>> 7f6f5e463955e564db16307c8581caddf5017b2b
    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }
}