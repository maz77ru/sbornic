package kz.mazur.servic;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import static android.content.ContentValues.TAG;

public class ObzorServis extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        Log.v(TAG, String.format(
                "onAccessibilityEvent: [type] %s [class] %s [package] %s [focus] %s [text] %s",
                getEventType(event), event.getClassName(), event.getPackageName(),
                event.getAction(), getEventText(event)));
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.v(TAG, "onServiceConnected");
        AccessibilityServiceInfo info=new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.DEFAULT;
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        info.packageNames = new String[]{"com.android.chrome"};//За филь тра цию при ложе ний,
        //с которыми работает сервис, отвечает строковый массив . packageNames
        setServiceInfo(info);

    }

    private String getEventText(AccessibilityEvent event) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence s : event.getText()) {
            sb.append(s);
        }
        return sb.toString();
    }

    private String getEventType(AccessibilityEvent event) {
        switch (event.getEventType()) {
            case  AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
            return "TYPE_NOTIFICATION_STATE_CHANGED";
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                return "TYPE_VIEW_CLICKED";
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                return "TYPE_VIEW_FOCUSED";
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                return "TYPE_VIEW_LONG_CLICKED";
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                return "TYPE_VIEW_SELECTED";
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                return "TYPE_WINDOW_STATE_CHANGED";
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                return "TYPE_VIEW_TEXT_CHANGED";
        }
        return "default";
    }

   

}
