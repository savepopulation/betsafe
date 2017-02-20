package net.betsafeapp.android.data;

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

    @SerializedName("result")
    private int result;

    public Bet() {
        // Public Empty Consructor
    }

    public Bet(String id, String event, String bankrollId, String bookMaker, int sport, double odds, double stake, int result) {
        this.id = id;
        this.event = event;
        this.bankrollId = bankrollId;
        this.bookMaker = bookMaker;
        this.sport = sport;
        this.odds = odds;
        this.stake = stake;
        this.result = result;
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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
