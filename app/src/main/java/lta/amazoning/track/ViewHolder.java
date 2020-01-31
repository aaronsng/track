package lta.amazoning.track;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView subtitle;
    public TextView title;

    public ViewHolder(View itemView) {
        super(itemView);
        subtitle = itemView.findViewById(R.id.subtitle_viewholder);
        title = itemView.findViewById(R.id.title_viewholder);
    }
}