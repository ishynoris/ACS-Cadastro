package tcc.acs_cadastro_mobile.alerts;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import tcc.acs_cadastro_mobile.R;

public class DefaultAlert {

    private AlertDialog.Builder alert;
    private DialogInterface.OnClickListener positiveListener, negativeListener;
    private String title, message;
    private Context context;

    public DefaultAlert(Context context) {
        this.context = context;
        this.alert = new AlertDialog.Builder(context)
                .setPositiveButton("", positiveListener)
                .setNegativeButton("", negativeListener);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitle(int resource) {
        setTitle(context.getString(resource));
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessage(int resource) {
        setMessage(context.getString(resource));
    }


    public void setPositiveListener(DialogInterface.OnClickListener listener) {
        setPositiveListener(R.string.btn_ok, listener);
    }
    public void setPositiveListener(String text, DialogInterface.OnClickListener listener) {
        alert.setPositiveButton(text, listener);
    }

    public void setPositiveListener(int resource, DialogInterface.OnClickListener listener) {
        setPositiveListener(context.getString(resource), listener);
    }

    public void setNegativeListener(String text, DialogInterface.OnClickListener listener) {
        alert.setNegativeButton(text, listener);
    }

    public void setNegativeListener(int resource, DialogInterface.OnClickListener listener) {
        setNegativeListener(context.getString(resource), listener);
    }

    public void setListeners(final DefaultClickListener listener){

        positiveListener = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.positive(dialog, which);
            }
        };

        negativeListener = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.negative(dialog, which);
            }
        };
        setPositiveListener(context.getString(R.string.btn_ok), positiveListener);
        setNegativeListener(context.getString(R.string.btn_cancel), negativeListener);
    }

    public void show() {
        alert.setIcon(android.R.drawable.ic_dialog_alert);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.show();
    }

    public interface DefaultClickListener {

        void positive(DialogInterface dialog, int which);

        void negative(DialogInterface dialog, int which);
    }
}
