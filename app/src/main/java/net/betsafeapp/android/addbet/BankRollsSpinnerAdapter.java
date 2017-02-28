package net.betsafeapp.android.addbet;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.betsafeapp.android.data.BankRoll;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by tyln on 28/02/2017.
 */

final class BankRollsSpinnerAdapter extends ArrayAdapter<BankRoll> {

    BankRollsSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<BankRoll> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(((TextView) super.getView(position, convertView, parent)), getItem(position));
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(((TextView) super.getDropDownView(position, convertView, parent)), getItem(position));
    }

    private View initView(@NonNull TextView textView, @NonNull BankRoll bankRoll) {
        textView.setText(bankRoll.getName());
        return textView;
    }
}
