package lta.amazoning.track;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ExpandableListView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class ViewHolder extends RecyclerView.ViewHolder {

    private CustomExpandableListView expandableListView;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private List<String> listDataGroup;
    private List<String> contentGroup;
    private ImageListBinder listDataChild;

    public ViewHolder(final View itemView) {
        super(itemView);
        expandableListView = itemView.findViewById(R.id.expandableListView);
        expandableListView.setOnChildClickListener(new CustomExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });
    }

    public void setContent(List<String> d, List<String> i, ImageListBinder c)  {
        // initializing the list of indices
        contentGroup = i;

        // initializing the list of groups
        listDataGroup = d;

        // initializing the list of child
        listDataChild = c;

        // Initializing the adapter object
        this.expandableListViewAdapter = new ExpandableListViewAdapter(itemView.getContext(), listDataGroup, contentGroup, listDataChild);

        // Setting list adapter
        expandableListView.setAdapter(this.expandableListViewAdapter);

        // Adding group data
        Context cxt = itemView.getContext();

        // Notify the adapter
        this.expandableListViewAdapter.notifyDataSetChanged();
    }

}