package lta.amazoning.track;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private static final String CLASS_NAME = "ExpandableListViewAdapter";
    private Context context;

    // group titles
    private List<String> listDataGroup;

    // index
    private List<String> contentGroup;

    // child data in format of header title, child title
    private ImageListBinder listDataChild;

    public ExpandableListViewAdapter(Context context, List<String> listDataGroup,
                                     List<String> contentGroup,
                                     ImageListBinder listChildData) {
        this.context = context;
        this.listDataGroup = listDataGroup;
        this.listDataChild = listChildData;
        this.contentGroup = contentGroup;
        Log.i(CLASS_NAME, "child_data_point: " + listDataChild.toString());
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listDataChild;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final ImageListBinder child_data_point = (ImageListBinder) getChild(groupPosition, childPosition);
        Log.i(CLASS_NAME, listDataChild.toString());
        if (convertView == null && this.contentGroup.get(0) != "e") {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_child, null);
        }

        TextView leftOrRight, point, tunnel, dropMin, newCurrent, sc, others;
        ImageView defectView;

        leftOrRight = convertView.findViewById(R.id.left_or_right_statement);
        point = convertView.findViewById(R.id.point_statement);
        tunnel = convertView.findViewById(R.id.tunnel_statement);
        dropMin = convertView.findViewById(R.id.drop_min_statement);
        newCurrent = convertView.findViewById(R.id.new_current_statement);
        sc = convertView.findViewById(R.id.sc_statement);
        others = convertView.findViewById(R.id.others_statement);
        defectView = convertView.findViewById(R.id.defectView);

        leftOrRight.setText(child_data_point.dataList.get(0));
        point.setText(child_data_point.dataList.get(1));
        tunnel.setText(child_data_point.dataList.get(2));
        dropMin.setText(child_data_point.dataList.get(3));
        newCurrent.setText(child_data_point.dataList.get(4));
        sc.setText(child_data_point.dataList.get(5));
        others.setText(child_data_point.dataList.get(6));
        defectView.setImageBitmap(child_data_point.defectImage);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (contentGroup.get(0) == "e") return 0;
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        List<String> returner = new ArrayList<>();
        Log.i(CLASS_NAME, contentGroup.toString());
        returner.add(this.contentGroup.get(0));
        returner.add(this.contentGroup.get(1)); // CHFr
        returner.add(this.contentGroup.get(2)); // CHTo
        returner.add(this.contentGroup.get(3)); // Defect
        return returner;
    }

    @Override
    public int getGroupCount() {
        return this.listDataGroup.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        List<String> output = (List<String>) getGroup(groupPosition);
        // Get index, as defined above is located in index 0
        String index = output.get(0);
        String chFr = output.get(1);
        String chTo = output.get(2);
        String defect_info = output.get(3);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_group, null);
        }

        //TextView textViewGroup = convertView
        //        .findViewById(R.id.textViewGroup);
        //textViewGroup.setTypeface(null, Typeface.BOLD);
        //textViewGroup.setText(headerTitle);
        ConstraintLayout r = convertView.findViewById(R.id.list_row_group);
        TextView number = convertView.findViewById(R.id.number);
        TextView defectLocation = convertView.findViewById(R.id.defect_location);
        TextView defectInfo = convertView.findViewById(R.id.defect_info);

        if (contentGroup.get(0) == "e") {
            int color = convertView.getContext().getColor(android.R.color.white);
            r.setBackgroundColor(color);
            number.setBackgroundColor(color);
            defectLocation.setBackgroundColor(color);
            defectLocation.setTextColor(color);
            defectInfo.setBackgroundColor(color);
            defectInfo.setTextColor(color);
            return convertView;
        }

        number.setText(index);
        defectLocation.setText("Fr " + chFr + " to " + chTo);
        defectInfo.setText(defect_info);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}