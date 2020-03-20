package lta.amazoning.track;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
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
    private boolean ADDED_LAST = false;

    List<List<String>> listDataGroup = new ArrayList<>();
    List<ImageListBinder> listDataChild = new ArrayList<>();
    List<List<String>> contentList = new ArrayList<>();
    List<ImageJSONBinder> content;

    ContentAdapter(Context context, List<ImageJSONBinder> content) {
        this.context = context;
        this.content = content;
        try {
            this.ITEM_COUNT = content.size() + 1;
            Log.i(CLASS_NAME, String.valueOf(content.size()));
        }
        catch (NullPointerException ne) {
            this.ITEM_COUNT = 0;
        }
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
        ImageListBinder list_data_child;
        List<String> content_group = new ArrayList<>();
        List<ImageListBinder> unbinded = null;
        HashMap<String, String> listConverted = null;
        List<String> childData = new ArrayList<>();

        int i = position;
        if (position == 1 && ITEM_COUNT == 2) i = 0;
        try {
            unbinded = convertListToHash(content);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(CLASS_NAME, "ITEM_COUNT: " + String.valueOf(ITEM_COUNT));

        if (ITEM_COUNT == 0) {
            return;
        }

        // Adding child data
        if (position == ITEM_COUNT - 1 && !ADDED_LAST) {
            list_data_group.add("");
            list_data_child = new ImageListBinder(new ArrayList<>(), null);
            content_group.add("e");
            content_group.add("e");
            content_group.add("e");
            content_group.add("e");
            listDataChild.add(list_data_child);
            listDataGroup.add(list_data_group);
            contentList.add(content_group);

            ADDED_LAST = true;
            holder.setContent(listDataGroup.get(position), contentList.get(position), listDataChild.get(position));
            return;
        }

        listConverted = unbinded.get(i).dataHash;
        Bitmap image = unbinded.get(i).defectImage;

        childData.add(listConverted.get("leftOrRight"));
        childData.add(listConverted.get("point"));
        childData.add(listConverted.get("tunnel"));
        childData.add(listConverted.get("dropMin"));
        childData.add(listConverted.get("newCurrent"));
        childData.add(listConverted.get("sc"));
        childData.add(listConverted.get("others"));

        content_group.add(String.valueOf(i + 1));
        content_group.add(listConverted.get("CHFr"));
        content_group.add(listConverted.get("CHTo"));
        content_group.add(listConverted.get("defect"));
        list_data_group.add(String.valueOf(i));
        list_data_child = new ImageListBinder(childData, image);

        listDataChild.add(list_data_child);
        listDataGroup.add(list_data_group);
        contentList.add(content_group);

        holder.setContent(listDataGroup.get(i), contentList.get(i), listDataChild.get(i));
    }

    private List<ImageListBinder> convertListToHash(List<ImageJSONBinder> defectDetails) throws JSONException {
        int ITEM_COUNT = defectDetails.size();
        List<ImageListBinder> localeGroup = new ArrayList<>();

        for (int i = 0; i < ITEM_COUNT; i++) {
            ImageJSONBinder defect_detail = defectDetails.get(i);
            JSONObject defectDetail = defect_detail.dataJson;
            HashMap<String, String> iter = new HashMap<>();
            iter.put("CHFr", defectDetail.get("CHFr").toString());
            iter.put("CHTo", defectDetail.get("CHTo").toString());
            iter.put("leftOrRight", defectDetail.get("leftOrRight").toString());
            iter.put("defect", defectDetail.get("defect").toString());
            iter.put("point", defectDetail.get("point").toString());
            iter.put("tunnel", defectDetail.get("tunnel").toString());
            iter.put("dropMin", defectDetail.get("dropMin").toString());
            iter.put("newCurrent", defectDetail.get("newCurrent").toString());
            iter.put("sc", defectDetail.get("sc").toString());
            iter.put("others", defectDetail.get("others").toString());
            iter.put("image", defectDetail.get("image").toString());

            ImageListBinder imageListBinder = new ImageListBinder(iter, defect_detail.defectImage);
            localeGroup.add(imageListBinder);
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