package lta.amazoning.track;

import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.List;

public class ImageListBinder {
    public HashMap<String, String> dataHash;
    public List<String> dataList;
    public Bitmap defectImage;

    public ImageListBinder(HashMap<String, String> jobject, Bitmap bitmapDrawable) {
        this.dataHash = jobject;
        this.defectImage = bitmapDrawable;
    }

    public ImageListBinder(List<String> jobject, Bitmap bitmapDrawable) {
        this.dataList = jobject;
        this.defectImage = bitmapDrawable;
    }

}
