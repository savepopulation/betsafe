package net.betsafeapp.android.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tyln on 16/01/2017.
 */

public class BankRoll extends RealmObject {
    @PrimaryKey
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("initialCapital")
    private double initialCapital;
    @SerializedName("currentCapital")
    private double currentCapital;
    @SerializedName("status")
    private int status;
    @SerializedName("privacy")
    private int privacy;
    @SerializedName("createDate")
    private long createDate;
    @SerializedName("updateDate")
    private long updateDate;

    public BankRoll() {
        // Empty
    }

    public BankRoll(String id,
                    String name,
                    double initialCapital,
                    double currentCapital,
                    int status,
                    int privacy,
                    long createDate,
                    long updateDate) {
        this.id = id;
        this.name = name;
        this.initialCapital = initialCapital;
        this.currentCapital = currentCapital;
        this.status = status;
        this.privacy = privacy;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public BankRoll(String name,
                    double initialCapital,
                    double currentCapital,
                    int status,
                    int privacy,
                    long createDate,
                    long updateDate) {
        this.name = name;
        this.initialCapital = initialCapital;
        this.currentCapital = currentCapital;
        this.status = status;
        this.privacy = privacy;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInitialCapital() {
        return initialCapital;
    }

    public void setInitialCapital(double initialCapital) {
        this.initialCapital = initialCapital;
    }

    public double getCurrentCapital() {
        return currentCapital;
    }

    public void setCurrentCapital(double currentCapital) {
        this.currentCapital = currentCapital;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}
