package lta.amazoning.track;

import org.json.JSONException;
import org.json.JSONObject;

public class DataJSON extends JSONObject {
    String leftOrRight = "", CHFr = "", CHTo = "", defect = "", point = "", tunnel = "", dropMin = "", newCurrent = "", sc = "", others = "", image = "";
    public DataJSON(String leftOrRight, String CHFr, String CHTo,
                     String defect, String point, String tunnel,
                     String dropMin, String newCurrent, String sc,
                     String others, String image) throws JSONException {
        this.leftOrRight = leftOrRight;
        this.CHFr = CHFr;
        this.CHTo = CHTo;
        this.defect = defect;
        this.point = point;
        this.tunnel = tunnel;
        this.dropMin = dropMin;
        this.newCurrent = newCurrent;
        this.sc = sc;
        this.others = others;
        this.image = image;
        putVariables();
    }

    void putVariables() throws JSONException {
        put("leftOrRight", leftOrRight);
        put("CHFr", CHFr);
        put("CHTo", CHTo);
        put("defect", defect);
        put("point", point);
        put("newCurrent", newCurrent);
        put("tunnel", tunnel);
        put("dropMin", dropMin);
        put("sc", sc);
        put("others", others);
        put("image", image);
    }
}

class DataJSONBuilder {
    String leftOrRight = "", CHFr = "", CHTo = "", defect = "", point = "", tunnel = "", dropMin = "", newCurrent = "", sc = "", others = "", image = "";

    DataJSONBuilder setOthers(String others) {
        this.others = others;
        return this;
    }

    DataJSONBuilder setDefects(String defect) {
        this.defect = defect;
        return this;
    }

    DataJSONBuilder setCHFr(String chFr) {
        this.CHFr = chFr;
        return this;
    }

    DataJSONBuilder setCHTo(String chTo) {
        this.CHTo = chTo;
        return this;
    }

    DataJSON build() throws JSONException {
        return new DataJSON(leftOrRight, CHFr, CHTo, defect, point, tunnel, dropMin, newCurrent, sc, others, image);
    }

}