package ar.com.moviltek.convocatorrpa;

import android.widget.Toast;

import java.util.TimerTask;

public class TareaPeriodica extends TimerTask {
    @Override
    public void run() {
        //Toast.makeText(this, "Servicio", Toast.LENGTH_SHORT).show();
        System.out.println("task run");


        /*
         Intent sendIntent = new Intent();

sendIntent.setAction(Intent.ACTION_SEND);
sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
sendIntent.setType("text/plain");
sendIntent.setPackage("com.whatsapp");

startActivity(sendIntent);
Más o menos sería algo así:
public void enviarMsgApp(View view) {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent .setType("text/plain");
    String texto = "Mensaje para enviar aqui";
    intent .setPackage("com.whatsapp");
    if (intent != null) {
        intent .putExtra(Intent.EXTRA_TEXT, texto);
        startActivity(Intent.createChooser(intent, texto));
    } else {
        Toast.makeText(this,"Nooo whatsapp, whatsapp man",
                       Toast.LENGTH_SHORT)
                       .show();
    }
}*/
    }
}
