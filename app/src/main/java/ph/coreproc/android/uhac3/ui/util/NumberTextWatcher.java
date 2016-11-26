package ph.coreproc.android.uhac3.ui.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

/**
 * Created by johneris on 11/27/16.
 */
public class NumberTextWatcher implements TextWatcher {

    private DecimalFormat dfnd;
    private EditText et;

    public NumberTextWatcher(EditText et) {
        dfnd = new DecimalFormat("#,###");
        this.et = et;
    }

    @SuppressWarnings("unused")
    private static final String TAG = "NumberTextWatcher";

    public void afterTextChanged(Editable s) {
        et.removeTextChangedListener(this);

        try {
            int inilen, endlen;
            inilen = et.getText().length();

            String v = s.toString().replace(",", "");
            int cp = et.getSelectionStart();
            if (v.contains(".")) {
                String[] number = v.split("\\.");
                String whole = number[0];
                String fraction = number[1];
                if (!whole.isEmpty()) {
                    whole = dfnd.format(Double.parseDouble(whole));
                }
                if (fraction.length() > 2) {
                    fraction = fraction.substring(0, 2);
                }
                et.setText(whole + "." + fraction);
            } else {
                et.setText(dfnd.format(Double.parseDouble(v)));
            }
            endlen = et.getText().length();
            int sel = (cp + (endlen - inilen));
            if (sel > 0 && sel <= et.getText().length()) {
                et.setSelection(sel);
            } else {
                // place cursor at the end?
                et.setSelection(et.getText().length() - 1);
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        et.addTextChangedListener(this);
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}
