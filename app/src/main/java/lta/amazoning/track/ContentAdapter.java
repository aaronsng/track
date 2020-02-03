package lta.amazoning.track;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A dummy {@link RecyclerView.Adapter} that displays a list of placeholder
 * list items to the user.
 */

//class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    private static final int ITEM_COUNT = 5;
class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int ITEM_COUNT = 6;
    private final LayoutInflater mInflater;
    private Context context;

    ContentAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new RecyclerView.ViewHolder(
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                mInflater.inflate(R.layout.view_holder_item, parent, false)) {};
    }

    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == ITEM_COUNT - 1) {
            List<String> listDataGroup = new ArrayList<>();
            List<String> indexGroup = new ArrayList<>();
            HashMap<String, List<String>> listDataChild = new HashMap<>();
            listDataGroup.add("");
            indexGroup.add("e");

            // array of strings
            String[] array;

            // Adding child data
            holder.setContent(listDataGroup, indexGroup, listDataChild);
            return;
        }

        List<String> listDataGroup = new ArrayList<>();
        List<String> indexGroup = new ArrayList<>();
        HashMap<String, List<String>> listDataChild = new HashMap<>();
        listDataGroup.add(this.context.getResources().getString(R.string.text_alcohol));
        indexGroup.add(String.valueOf(position + 1));

        // array of strings
        String[] array;

        // list of alcohol
        List<String> alcoholList = new ArrayList<>();
        array = this.context.getResources().getStringArray(R.array.string_array_alcohol);
        for (String item : array) {
            alcoholList.add(item);
        }

        // Adding child data
        listDataChild.put(listDataGroup.get(0), alcoholList);
        holder.setContent(listDataGroup, indexGroup, listDataChild);

    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }
}