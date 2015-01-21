package views;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewHelpers {
    final static int PREVIEW_STRING_CHARS = 80;

    public static String answerFormatString(java.lang.Integer answerFormat) {
        switch (answerFormat) {
            case 0:
                return "Multiple choice - single selection";
            case 1:
                return "Multiple choice - multiple selection";
            case 2:
                return "Text";
            default:
                return answerFormat + "";
        }
    }

    public static String formatDate(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd/MM/yyyy HH:mm");
        return sdf.format( new Date(timestamp.getTime()) );
    }

    public static String previewString(String string) {
        if (string.length() > PREVIEW_STRING_CHARS) {
            return string.substring(0, PREVIEW_STRING_CHARS - 1) + "...";
        }
        return string;
    }
}