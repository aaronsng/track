package lta.amazoning.track;

import android.graphics.Bitmap;
import org.json.JSONObject;

public class ImageJSONBinder {
    public JSONObject dataJson;
    public Bitmap defectImage;

    public ImageJSONBinder(JSONObject jobject, Bitmap bitmapDrawable) {
        this.dataJson = jobject;
        this.defectImage = bitmapDrawable;
    }

}
