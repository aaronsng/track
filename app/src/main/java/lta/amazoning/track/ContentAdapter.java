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

    List<JSONObject> content;

    ContentAdapter(Context context, List<JSONObject> content) {
        this.context = context;
        this.content = content;
        try {
            this.ITEM_COUNT = content.size();
            Log.i(CLASS_NAME, String.valueOf(content.size()));
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
        List<String> listDataGroup = new ArrayList<>();
        HashMap<String, List<String>> listDataChild = new HashMap<>();
        List<String> indexGroup = new ArrayList<>();

        if (ITEM_COUNT == 0) {
            listDataGroup.add("");
            listDataGroup.add("");
            listDataGroup.add("");
            indexGroup.add("empty");
            Log.i(CLASS_NAME, "Empty List");

            // Adding child data
            holder.setContent(listDataGroup, indexGroup, listDataChild);
            return;
        }

        if (position == ITEM_COUNT - 1) {
            listDataGroup.add("");
            listDataGroup.add("");
            listDataGroup.add("");
            indexGroup.add("e");

            // Adding child data
            holder.setContent(listDataGroup, indexGroup, listDataChild);
            return;
        }

        // array of strings
        String[] array;

        // list of alcohol
        List<String> alcoholList = new ArrayList<>();
        array = this.context.getResources().getStringArray(R.array.string_array_alcohol);
        for (String item : array) {
            alcoholList.add(item);
        }

        // Adding child data
        try {
            HashMap<String, String> listConverted = convertListToHash(content);
            Log.i(CLASS_NAME, "positionVal: " + String.valueOf(position));
            indexGroup.add(String.valueOf(position + 1));
            listDataGroup.add(listConverted.get("CHFr"));
            listDataGroup.add(listConverted.get("CHTo"));
            listDataGroup.add(listConverted.get("defect"));
            listDataChild.put("image", alcoholList);
            holder.setContent(listDataGroup, indexGroup, listDataChild);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, String> convertListToHash(List<JSONObject> defectDetails) throws JSONException {
        int ITEM_COUNT = defectDetails.size();
        HashMap<String, String> localeGroup = new HashMap<>();

        for (int i = 0; i < ITEM_COUNT; i++) {
            JSONObject defectDetail = defectDetails.get(i);
            localeGroup.put("leftOrRight", defectDetail.get("leftOrRight").toString());
            localeGroup.put("CHFr", defectDetail.get("CHFr").toString());
            localeGroup.put("CHTo", defectDetail.get("CHTo").toString());
            localeGroup.put("defect", defectDetail.get("defect").toString());
            localeGroup.put("point", defectDetail.get("point").toString());
            localeGroup.put("tunnel", defectDetail.get("tunnel").toString());
            localeGroup.put("dropMin", defectDetail.get("dropMin").toString());
            localeGroup.put("newCurrent", defectDetail.get("newCurrent").toString());
            localeGroup.put("sc", defectDetail.get("sc").toString());
            localeGroup.put("others", defectDetail.get("others").toString());
            localeGroup.put("image", defectDetail.get("image").toString());
            Log.i(CLASS_NAME, defectDetail.toString());
        }
        return localeGroup;
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