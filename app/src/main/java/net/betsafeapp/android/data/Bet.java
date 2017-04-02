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
    private Pick pick;

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
               Pick pick,
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bet)) return false;

        Bet bet = (Bet) o;

        return id != null ? id.equals(bet.id) : bet.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getBankrollId() {
        return bankrollId;
    }

    public void setBankrollId(String bankrollId) {
        this.bankrollId = bankrollId;
    }

    public String getBookMaker() {
        return bookMaker;
    }

    public void setBookMaker(String bookMaker) {
        this.bookMaker = bookMaker;
    }

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public Pick getPick() {
        return pick;
    }

    public void setPick(Pick pick) {
        this.pick = pick;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
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
