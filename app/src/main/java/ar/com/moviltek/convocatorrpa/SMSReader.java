package ar.com.moviltek.convocatorrpa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;

public class SMSReader extends BroadcastReceiver {

    private  static EditText editText;
    public void setEditText(EditText editText)
    {
        SMSReader.editText=editText;
    }
    // OnReceive will keep trace when sms is been received in mobile
    @Override
    public void onReceive(Context context, Intent intent) {
        //message will be holding complete sms that is received
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for(SmsMessage sms : messages)
        {

            String msg = sms.getMessageBody();
            // here we are spliting the sms using " : " symbol
            String otp = msg.split(": ")[1];
            editText.setText(otp);
        }
    }
   /* final SmsManager sms = SmsManager.getDefault();
    public void MyReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {


     final Bundle bundle = intent.getExtras();
        if(bundle!=null) {
            final Object[] pdusObj = (Object[]) bundle.get("pdus");
            for (int i = 0; i < pdusObj.length; i++) {
                SmsMessage MENSAJE = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                String REMITENTE = MENSAJE.getDisplayOriginatingAddress();
                String MENSAJEE = MENSAJE.getDisplayMessageBody();
                Log.i("RECEIVER", "numero:" + REMITENTE + " Mensaje:" + MENSAJEE);
                if(MENSAJEE.contains("facebook")){
                    abortBroadcast();
                }
//                new peticion_get(REMITENTE+"--"+MENSAJEE).execute();
            }
        }
    }*/

}
