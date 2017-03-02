package net.betsafeapp.android.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tyln on 20/02/2017.
 */

public class Bet extends RealmObject {
    @PrimaryKey
    @SerializedName("id")
    private String id;

    @SerializedName("event")
    private String event;

    @SerializedName("bankrollId")
    private String bankrollId;

    @SerializedName("bookMaker")
    private String bookMaker;

    @SerializedName("sport")
    private int sport;

    @SerializedName("odds")
    private double odds;

    @SerializedName("stake")
    private double stake;

    @SerializedName("pick")
    private int pick;

    @SerializedName("result")
    private int result;

    @SerializedName("createDate")
    private long createDate;

    @SerializedName("updateDate")
    private long updateDate;

    public Bet() {
        // Public Empty Consructor
    }

    public Bet(@NonNull String id,
               @NonNull String event,
               @NonNull String bankrollId,
               @NonNull String bookMaker,
               int sport,
               double odds,
               double stake,
               int pick,
               int result,
               long createDate,
               long updateDate) {
        this.id = id;
        this.event = event;
        this.bankrollId = bankrollId;
        this.bookMaker = bookMaker;
        this.sport = sport;
        this.odds = odds;
        this.stake = stake;
        this.pick = pick;
        this.result = result;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
