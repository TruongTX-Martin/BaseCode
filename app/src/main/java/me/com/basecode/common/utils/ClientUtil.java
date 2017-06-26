package me.com.basecode.common.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import me.com.basecode.base.manager.BaseManager;


/**
 * Created by xuan on 20/02/2017.
 */

public class ClientUtil {

    public static void hideKeyboard() {
        if (BaseManager.getIntance().getCurrentActivity() != null) {
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) BaseManager.getIntance().getCurrentActivity()
                        .getSystemService(BaseManager.getIntance().getCurrentActivity().INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(
                        BaseManager.getIntance().getCurrentActivity().getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
            }
        }
    }

    public static void showKeyboard() {
        System.out.println("show keyboard ======>");
        BaseManager.getIntance().getCurrentActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

    }

    public static String upperCaseString(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }


    public static void showToast(String text) {
        Toast toast = Toast.makeText(BaseManager.getIntance().getCurrentContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 150);
        toast.show();
    }


    public static String ellipseString(String input, int number) {
        if (input.length() <= number) {
            return input;
        } else {
            input = input.substring(0, number - 3) + "...";
            return input;
        }
    }


    public static String formatMoneyWithoutCountDoc(String money) {
        String result = "";
        try {
            result = NumberFormat.getNumberInstance(Locale.US).format(Double.parseDouble(money));
        } catch (Exception e) {
        }
        return "<font color=\"#b72126\">" + result + "</font>";
    }

    public static String formatString(String input, String color) {
        return "<font color=\" " + color + "\">" + input + "</font>";
    }

    public static String formatStringBold(String input, String color) {
        return "<font color=\" " + color + "\"> font-weight=\"bold\"" + input + "</font>";
    }

    public static String formatColor(String input) {
        return "<font color=\"#b72126\">" + input + "</font>";
    }

    public static String formatColor(String input, String color) {
        return "<font color=\"" + color + "\">" + input + "</font>";
    }

    public static String formatColorBold(String input, String color) {
        return "<font color=\"" + color + "\"><b>" + input + "</b></font>";

    }



    public static boolean validate(String input) {
        if (input == null || input.length() == 0 || input.equals("null")) {
            return false;
        }
        return true;
    }

    public static void underLineTextView(TextView view) {
        view.setPaintFlags(view.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    public static void toLink(String url) {
        if (!ClientUtil.validate(url)) return;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        BaseManager.getIntance().getCurrentActivity().startActivity(i);
    }

    public static String getDate(String dateString) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = format.parse(dateString);
            cal.setTime(date);
            String year = cal.get(Calendar.YEAR) + "";
            String month = cal.get(Calendar.MONTH) + "";
            String day = cal.get(Calendar.DAY_OF_MONTH) + "";
            return year + "-" + month + "-" + day;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDateFromString(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formatBack = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = (Date) format.parse(dateString);
            String dateConvert = formatBack.format(date);
            return dateConvert;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getTimeFromString(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formatBack = new SimpleDateFormat("HH:mm:ss");
        try {
            Date date = (Date) format.parse(dateString);
            String dateConvert = formatBack.format(date);
            return dateConvert;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String miliSecondsToTimer(long milliseconds) {
        String finalTimerString;
        String minutesString;
        String secondsString;

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there

        // Prepending 0 to hours if it is one digit
        if (hours < 10) {
            finalTimerString = "0" + hours;
        } else {
            finalTimerString = "" + hours;
        }

        // Prepending 0 to minutes if it is one digit
        if (minutes < 10) {
            minutesString = "0" + minutes;
        } else {
            minutesString = "" + minutes;
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + ":" + minutesString + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    public static boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) BaseManager.getIntance().getCurrentActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean validPhoneNumber(String number) {
        if (number.matches("[0-9]+") && number.length() > 8 && number.length() < 23) {
            return true;
        }
        return false;
    }

    public static boolean validBankAccountNumber(String number) {
        if (number.length() > 8 && number.length() < 30) {
            return true;
        }
        return false;
    }


    public static int getValueDp(int value) {
        float unit = BaseManager.getIntance().getCurrentContext()
                .getResources().getDisplayMetrics().density;
        return (int) (value * unit + 0.5f);
    }


    public static String revertNumberEdittext(String s) {
        String revert = ",";
        if (!ClientUtil.validate(s)) return "";
        s = s.replace(revert, "");

        return s;
    }

    public static String formatIntegerSeparator(int number) {
        String format = "";
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        DecimalFormat dfDecimal = new DecimalFormat("##############");
        dfDecimal.setDecimalFormatSymbols(symbols);
        dfDecimal.setGroupingSize(3);
        dfDecimal.setGroupingUsed(true);
        try {
            format = dfDecimal.format(number);
        } catch (Exception e) {
        }
        return format;
    }


}
