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

    List<List<String>> listDataGroup = new ArrayList<>();
    List<HashMap<String, List<String>>> listDataChild = new ArrayList<>();
    List<List<String>> contentList = new ArrayList<>();
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
        List<String> list_data_group = new ArrayList<>();
        HashMap<String, List<String>> list_data_child = new HashMap<>();
        List<String> content_group = new ArrayList<>();

        if (ITEM_COUNT == 0) {
            return;
        }

//        if (position == ITEM_COUNT - 1) {
//            list_data_group.add("");
//            list_data_child.put("empty", list_data_group);
//            content_group.add("e");
//            content_group.add("e");
//            content_group.add("e");
//            content_group.add("e");
//            listDataChild.add(list_data_child);
//            listDataGroup.add(list_data_group);
//            contentList.add(content_group);
//
//            // Adding child data
//            holder.setContent(listDataGroup.get(position), contentList.get(position), listDataChild.get(position));
//            return;
//        }

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
            List<HashMap<String, String>> listConverted = convertListToHash(content);
            int l = listConverted.size();
            Log.i(CLASS_NAME, "positionVal: " + String.valueOf(position));

            content_group.add(String.valueOf(position + 1));
            content_group.add(listConverted.get(position).get("CHFr"));
            content_group.add(listConverted.get(position).get("CHTo"));
            content_group.add(listConverted.get(position).get("defect"));
            list_data_group.add(String.valueOf(position));
            list_data_child.put("image", alcoholList);

            Log.i(CLASS_NAME, "contentList: " + contentList.toString());
            listDataChild.add(list_data_child);
            listDataGroup.add(list_data_group);
            contentList.add(content_group);

            holder.setContent(listDataGroup.get(position), contentList.get(position), listDataChild.get(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private List<HashMap<String, String>> convertListToHash(List<JSONObject> defectDetails) throws JSONException {
        int ITEM_COUNT = defectDetails.size();
        List<HashMap<String, String>> localeGroup = new ArrayList<>();

        for (int i = 0; i < ITEM_COUNT; i++) {
            JSONObject defectDetail = defectDetails.get(i);
            HashMap<String, String> iter = new HashMap<>();
            iter.put("leftOrRight", defectDetail.get("leftOrRight").toString());
            iter.put("CHFr", defectDetail.get("CHFr").toString());
            iter.put("CHTo", defectDetail.get("CHTo").toString());
            iter.put("defect", defectDetail.get("defect").toString());
            iter.put("point", defectDetail.get("point").toString());
            iter.put("tunnel", defectDetail.get("tunnel").toString());
            iter.put("dropMin", defectDetail.get("dropMin").toString());
            iter.put("newCurrent", defectDetail.get("newCurrent").toString());
            iter.put("sc", defectDetail.get("sc").toString());
            iter.put("others", defectDetail.get("others").toString());
            iter.put("image", defectDetail.get("image").toString());
            Log.i(CLASS_NAME, defectDetail.toString());

            localeGroup.add(iter);
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