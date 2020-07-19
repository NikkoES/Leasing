package com.luckynineapps.indoleasing.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

/**
 * Created by knalb on 19/07/17.
 */

public class NumberUtil {

    public static String toIndoCurrency(Double bills) {
        return "%s%s".format("Rp", new DecimalFormat("#,###.##").format(bills));
    }

    public static String toIndoPriceFormatted(Double price) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(price);
    }

    public <V> int getListIndex(List<V> list, V item) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == item) {
                return i;
            }
        }
        return -1;
    }
}
