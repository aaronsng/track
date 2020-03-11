package lta.amazoning.track;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static int ITEM_COUNT;
    private final LayoutInflater mInflater;
    private String CLASS_NAME = "ContentAdapter";
    private Context context;

    List<String> listDataGroup;
    List<String> inputNew;
    List<String> indexGroup = new ArrayList<>();
    HashMap<String, List<String>> listDataChild = new HashMap<>();

    ContentAdapter(Context context, List<String> inputNew) {
        this.context = context;
        this.inputNew = inputNew;
        try {
            this.ITEM_COUNT = inputNew.size();
            Log.i(CLASS_NAME, inputNew.toString());
        }
        catch (NullPointerException ne) {
            this.ITEM_COUNT = 0;
        }
        Log.i(CLASS_NAME, "Initialising Content Adapter");
        Log.i(CLASS_NAME, "ITEM_COUNT: " + String.valueOf(this.ITEM_COUNT));
        mInflater = LayoutInflater.from(context);
    }

    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new RecyclerView.ViewHolder(
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                mInflater.inflate(R.layout.view_holder_item, parent, false)) {};
    }

//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (ITEM_COUNT == 0) {
            listDataGroup.add("");
            indexGroup.add("empty");
            Log.i(CLASS_NAME, "Empty List");
            // Adding child data
            holder.setContent(listDataGroup, indexGroup, listDataChild);
            return;
        }

        if (position == ITEM_COUNT - 1) {
            List<String> listDataGroup = new ArrayList<>();
            List<String> indexGroup = new ArrayList<>();
            HashMap<String, List<String>> listDataChild = new HashMap<>();
            listDataGroup.add("");
            indexGroup.add("e");

            // Adding child data
            holder.setContent(listDataGroup, indexGroup, listDataChild);
            return;
        }

        Log.i(CLASS_NAME, "Binding ViewHolder");
        // array of strings
        String[] array;

        // list of alcohol
        List<String> alcoholList = new ArrayList<>();
        array = this.context.getResources().getStringArray(R.array.string_array_alcohol);
        for (String item : array) {
            alcoholList.add(item);
        }

        // Adding child data
        indexGroup.add(String.valueOf(position));
        listDataGroup.add(inputNew.get(position));
        listDataChild.put(inputNew.get(position), alcoholList);
        holder.setContent(listDataGroup, indexGroup, listDataChild);
    }

    @Override
    public int getItemCount() {
        try {
            return ITEM_COUNT;
        }
        catch (NullPointerException ne) {
            return 0;
        }
    }
}