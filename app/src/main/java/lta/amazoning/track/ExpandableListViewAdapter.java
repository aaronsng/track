package lta.amazoning.track;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
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
    private List<String> indexGroup;

    // child data in format of header title, child title
    private HashMap<String, List<String>> listDataChild;

    public ExpandableListViewAdapter(Context context, List<String> listDataGroup,
                                     List<String> indexGroup,
                                     HashMap<String, List<String>> listChildData) {
        this.context = context;
        this.listDataGroup = listDataGroup;
        this.listDataChild = listChildData;
        this.indexGroup = indexGroup;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataGroup.get(0))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null && this.indexGroup.get(0) != "e") {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_child, null);
        }

        TextView textViewChild = convertView.findViewById(R.id.textViewChild);
        textViewChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (indexGroup.get(0) == "e") return 0;
        return this.listDataChild.get(indexGroup)
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        List<String> returner = new ArrayList<>();
        Log.i(CLASS_NAME, indexGroup.toString());
        returner.add(this.indexGroup.get(0));
        returner.add(this.indexGroup.get(1)); // CHFr
        returner.add(this.indexGroup.get(2)); // CHTo
        returner.add(this.indexGroup.get(3)); // Defect
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

        if (indexGroup.get(0) == "e") {
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