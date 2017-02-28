package net.betsafeapp.android.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tyln on 28/02/2017.
 */

public final class Pick {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public Pick(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
