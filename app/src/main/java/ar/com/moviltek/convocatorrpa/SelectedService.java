package ar.com.moviltek.convocatorrpa;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;


public class SelectedService extends Service {

    private Timer jugadas= new Timer();
    private TareaPeriodica task= new TareaPeriodica();
    NotificationChannel mychannel;
    Notification.Builder builder;
    NotificationManager notificationManger;
    Notification notification;
    //AnotherActivity must be finish and send an exit intent to MainActivity
    int horapartido;
    int minutospartido;
    int convohsantes;
    int warninghsantes;
//    int hsprevia;
//    int diasprevia;
    int[] diaspartido;
    int[] diapconvo;
    int[] diapwarning;
    int horaconvo;
    int minutosconvo;
    int horawarning;
    int minutoswarning;

    Calendar warningpartidocalendar;
    Calendar partidocalendar;
    Calendar convopartidocalendar;
    //public static final int REQUEST_CODE=101;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent,int flags,int startId) {

      // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = new StringBuffer("micanal");

            NotificationChannel channel = new NotificationChannel("micanal", name, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("description");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this

            NotificationManager notificationManager = this.<NotificationManager>getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            Notification mynot = new Notification();
            mynot.flags |= Notification.FLAG_ONGOING_EVENT;

            Notification.Builder mybuild = new Notification.Builder(this, "micanal");
            mybuild.build();

        }

// MATEMATICA DE FECHAS, PARA OBTEENR DATES DE PARTIDO WARNING Y CONVO.
// for Alarm 25/12/2012 at 12.00
        diaspartido=intent.getExtras().getIntArray("Dias");
        horapartido=intent.getExtras().getInt("Hora");
        minutospartido=intent.getExtras().getInt("Minutos");

       //si la convo no es el mismo dia que el partido
        if(intent.hasExtra("convo")) {
            convohsantes = intent.getExtras().getInt("convo");
            diapconvo = new int[diaspartido.length];

            if (horapartido - convohsantes < 0) {
                int hsprevia = convohsantes % 24;
                int diasprevia = (convohsantes - hsprevia) / 24;
                for (int n = 0; n < diaspartido.length; n++) {
                    diapconvo[n] = diaspartido[n] - diasprevia;
                    if (diapconvo[n] <= 0) {
                        diapconvo[n] = diapconvo[n] + 7;
                    }
                }
                horaconvo = horapartido - hsprevia;
                minutosconvo = minutospartido;
            } else {
                for (int n = 0; n < diaspartido.length; n++) {
                    diapwarning[n] = diaspartido[n];

                }
                horaconvo = horapartido - convohsantes;
                minutosconvo = minutospartido;
            }
        }

        //El warning si  no es el mismo dia que el partido

        if(intent.hasExtra("warning")) {
            warninghsantes = intent.getExtras().getInt("warning");

            diapwarning = new int[diaspartido.length];

            if (horapartido - warninghsantes < 0) {
                int hsprevia = warninghsantes % 24;
                int diasprevia = (warninghsantes - hsprevia) / 24;
                for (int n = 0; n < diaspartido.length; n++) {
                    diapwarning[n] = diaspartido[n] - diasprevia;
                    if (diapwarning[n] <= 0) {
                        diapwarning[n] = diapwarning[n] + 7;
                    }
                }
                horawarning = horapartido - hsprevia;
                minutoswarning = minutospartido;
            } else {
                for (int n = 0; n < diaspartido.length; n++) {
                    diapwarning[n] = diaspartido[n];

                }
                horawarning = horapartido - warninghsantes;
                minutoswarning = minutospartido;
            }
        }
Log.d("warning",diaspartido[0]+ " " + horawarning + "  " + minutoswarning);
        // CON ESTO YA ESTA CALCULADA LA HORA Y DIAS DE CONVO diapconvo hora convo
        // WARNING diapwar horawar  Y PARTIDO diaspar  horapar
/*
        Log.d("partido es:",(horapartido+" "+minutospartido));
        Log.d("warning es:",(horawarning+" "+minutoswarning));
        Log.d("convo es:",(horaconvo+" "+minutosconvo));

*/
        // foreach day partido

        partidocalendar = Calendar.getInstance();
        //partidocalendar.setTimeInMillis(System.currentTimeMillis());
        //convopartido.set(2020, 5, 3, 17, 28, 0);
        partidocalendar.set(Calendar.DAY_OF_WEEK, diaspartido[0]);
        //convopartido.set(Calendar.YEAR, 2020);
        //convopartido.set(Calendar.MONTH, 5);
        //convopartido.set(Calendar.DAY_OF_MONTH, 3);
        partidocalendar.set(Calendar.MINUTE, minutospartido);
        partidocalendar.set(Calendar.HOUR_OF_DAY, horapartido);
        partidocalendar.set(Calendar.SECOND, 0);
        partidocalendar.set(Calendar.MILLISECOND, 0);

        if(intent.hasExtra("convo")) {

            convopartidocalendar = Calendar.getInstance();
            //convopartidocalendar.setTimeInMillis(System.currentTimeMillis());
            convopartidocalendar.set(Calendar.DAY_OF_WEEK, diapconvo[0]);
            convopartidocalendar.set(Calendar.MINUTE, minutosconvo);
            convopartidocalendar.set(Calendar.HOUR_OF_DAY, horaconvo);
            //convopartidocalendar.set(Calendar.DAY_OF_WEEK, diaspartido[0]);
            //convopartidocalendar.set(Calendar.MINUTE, minutospartido+1);
            //convopartidocalendar.set(Calendar.HOUR_OF_DAY, horapartido);
            convopartidocalendar.set(Calendar.SECOND, 0);
            convopartidocalendar.set(Calendar.MILLISECOND, 0);

        }

        if(intent.hasExtra("warning")) {

            warningpartidocalendar = Calendar.getInstance();
            //warningpartidocalendar.setTimeInMillis(System.currentTimeMillis());
            warningpartidocalendar.set(Calendar.DAY_OF_WEEK, diapwarning[0]);
            warningpartidocalendar.set(Calendar.MINUTE, minutoswarning);
            warningpartidocalendar.set(Calendar.HOUR_OF_DAY, horawarning);
            warningpartidocalendar.set(Calendar.SECOND, 0);
            warningpartidocalendar.set(Calendar.MILLISECOND, 0);

        }



/*
        Calendar calendarp = Calendar.getInstance();
        calendarp.setTimeInMillis(partidocalendar.getTimeInMillis());

        int phora = calendarp.get(Calendar.HOUR_OF_DAY);
        int pminuto = calendarp.get(Calendar.MINUTE);

        Calendar calendarw = Calendar.getInstance();
        calendarw.setTimeInMillis((warningpartidocalendar.getTimeInMillis()+6*60*60*1000-5000));

        int whora = calendarw.get(Calendar.HOUR_OF_DAY);
        int wminuto = calendarw.get(Calendar.MINUTE);

        Calendar calendarc = Calendar.getInstance();
        calendarc.setTimeInMillis((convopartidocalendar.getTimeInMillis()+36*60*60*1000-10000));

        int chora = calendarc.get(Calendar.HOUR_OF_DAY);
        int cminuto = calendarc.get(Calendar.MINUTE);

*/

//fin computo de fechas


        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        Intent _myIntent = new Intent(this, AlarmReceiver.class);
        _myIntent.putExtra("diapartido",diaspartido[0]);
        _myIntent.putExtra("horapartido",horapartido);
        //_myIntent.putExtra("minutospartido",minutosconvo);

        //Intent _myIntent = new Intent(this, AlarmReceiver.class);
        //adelanto la convo en 36 horas menos 10 segundos para testeo
        //va a tener que ser en minutos porque no hay mas definicion que eso (las alramas)
        //(parecen activarse por minutos)
      _myIntent.putExtra("alarm", "convo");
        PendingIntent _myPendingIntent = PendingIntent.getBroadcast(this, 123, _myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, (convopartidocalendar.getTimeInMillis()+36*60*60*1000/*-12000*/),_myPendingIntent);
/*
        //warning +6 menos un minuto
        _myIntent.putExtra("alarm", "warning");
        PendingIntent _myPendingIntent2 = PendingIntent.getBroadcast(this, 124, _myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, (warningpartidocalendar.getTimeInMillis()+6*60*60*1000-60000),_myPendingIntent2);


        _myIntent.putExtra("alarm", "partido");
        PendingIntent _myPendingIntent3 = PendingIntent.getBroadcast(this, 125, _myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, partidocalendar.getTimeInMillis(),_myPendingIntent3);
*/
        Log.d("numero",(convopartidocalendar.getTimeInMillis()+36*60*60*1000-60000)+"  "+(warningpartidocalendar.getTimeInMillis()+6*60*60*1000-30000)+ " " + partidocalendar.getTimeInMillis()+"  ");
/*
       // Intent _myIntent3 = new Intent(this, AlarmReceiver.class);
        Intent _myIntent2 = new Intent(this, AlarmReceiver.class);
//adelanto el warning 6 horas -5 segundos para pruebas
        _myIntent2.putExtra("alarm", "warning");
        PendingIntent _myPendingIntent2 = PendingIntent.getBroadcast(this, 124, _myIntent2, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, (warningpartidocalendar.getTimeInMillis()+6*60*60*1000-5000),_myPendingIntent2);

*/
        //Intent _myIntent = new Intent(this, AlarmReceiver.class);
        Toast.makeText(this, "convo act", Toast.LENGTH_SHORT).show();

/*
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");


            Calendar calendarver = Calendar.getInstance();
            calendarver.setTimeInMillis(convopartidocalendar.getTimeInMillis()+36*60*60*1000-10000);
int diamod= calendarver.get(Calendar.DAY_OF_WEEK);
        int horamod = calendarver.get(Calendar.HOUR_OF_DAY);
        int minmod = calendarver.get(Calendar.MINUTE);
        int segmod = calendarver.get(Calendar.SECOND);

Log.d("dateconvoes", diamod+" "+ horamod + " " + minmod + " " + segmod + " " + diaspartido[0]);

 */

/*
        //AlarmManager alarmManager= (AlarmManager) context.getSystemService(ALARM_SERVICE);
        AlarmManager alarmManager= (AlarmManager) this.getSystemService(ALARM_SERVICE);

//CREATE PENDING INTENT
        Intent intentbcast=new Intent(this,AlarmBcast.class);

        //Intent intent = new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intentbcast, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() +1000, pendingIntent);

//Alarm will be triggered approximately after one hour and will be repeated every hour after that

   //     alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), 1000, pendingIntent);

/*
1st Param : Context
2nd Param : Integer request code
3rd Param : Wrapped Intent
4th Intent: Flag

/*
        Always keep a note of the request code used while creating a pending intent. Request code is a unique identifier of that pending intent and can be used to get reference to that PendingIntent anywhere inside the application.
        One more important factor while creating a pending intent is the flag passed in as the 4th parameter. Flags define the behavior of a PendingIntent and in turn the behavior of that Alarm.
        1. FLAG_CANCEL_CURRENT
        Flag indicating that if the described PendingIntent already exists, the current one should be canceled before generating a new one.
        2. FLAG_IMMUTABLE

        Flag indicating that the created PendingIntent should be immutable.
        3. FLAG_NO_CREATE

        Flag indicating that if the described PendingIntent does not already exist, then simply return null instead of creating it.
        4.FLAG_ONE_SHOT

        Flag indicating that this PendingIntent can be used only once.
        5. FLAG_UPDATE_CURRENT

        Flag indicating that if the described PendingIntent already exists, then keep it but replace its extra data with what is in this new Intent.
                Not choosing a proper flag could result in multiple duplicate alarms scheduled around the same time

/*
Step 3: Choosing the Alarm Types
One of the first steps while creating an Alarm is choosing its type. This will define its behavior. There are basically two types of clocks to schedule time based alarms using AlarmManager. First is Elapsed Real Time- which uses the “time since system boot” for reference, second is Real time clock which uses UTC (wall clock) . Both also have their wake up version which defines whether the system should wake up the device when the alarm is triggered.

Here is the list of types:

ELAPSED_REALTIME—Fires the pending intent based on the amount of time since the device was booted, but doesn’t wake up the device. The elapsed time includes any time during which the device was asleep.
ELAPSED_REALTIME_WAKEUP—Wakes up the device and fires the pending intent after a specified length of time has elapsed since device boot.
RTC—Fires the pending intent at the specified time as per Wall Clock but does not wake up the device.
RTC_WAKEUP—Wakes up the device to fire the pending intent at the specified time as per Wall Clock.

/*
Final Step : Setting the Alarm
AlarmManager provides with number of methods which can be used based on how precise you want the alarm to be. Some of the most commonly used methods are

setInexactRepeating
This schedules a repeating alarm which is inexact in its trigger time i.e. if you schedule the alarm to trigger at 8:00am it might not trigger exactly at the same time. These alarms are very power efficient as they adjust delivery times to fire multiple alarms simultaneously. This alarm will be repeated after a scheduled time
AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
Intent intent = new Intent(this, MyReceiver.class);
PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

//Alarm will be triggered approximately after one hour and will be repeated every hour after that

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis() + AlarmManager.INTERVAL_HOUR, AlarmManager.INTERVAL_HOUR, pendingIntent);



/*
setRepeating
This is same as setInExactRepeating except for the fact that the alarm is triggered exactly at the scheduled time. Android suggests us to use this only when it is necessary as this puts unnecessary burden on the system since it wont be able adjust delivery time to bundle multiple alarms together. Like setInExactRepeating this alarm will repeat itself after a scheduled time
AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
Intent intent = new Intent(this, MyReceiver.class);
PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
Calendar calendar = Calendar.getInstance();
calendar.setTimeInMillis(System.currentTimeMillis());
calendar.set(Calendar.HOUR_OF_DAY, 8);
calendar.set(Calendar.MINUTE, 30);

Alarm will be triggered exactly at 8:30 every day

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

1st Param : Type of the Alarm
2nd Parm : Time in milliseconds when the alarm will be triggered first
3rd Param : Interval after which alarm will be repeated .
4th Param : Pending Intent
Note that we have changed the type to RTC_WAKEUP as we are using Wall clock time

/*
set
This will schedule a one time alarm which will be triggered approximately at the scheduled time. OS is allowed to adjust delivery time for these alarms. This alarm will be triggered only once.

AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
Intent intent = new Intent(this, MyReceiver.class);
PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//This alarm will trigger once approximately after 1 hour and
alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis() + AlarmManager.INTERVAL_HOUR, pendingIntent);


/*
setExact
This is same as set(context,timeMillis,pendingIntent) except for the fact that OS is not allowed to adjust the delivery time for these type of alarms. This shouldn’t be used unless there is a strong demand for alarm to be triggered at a precise time

AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    Intent intent = new Intent(this, MyReceiver.class);

    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    Calendar calendar = Calendar.getInstance();

    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.set(Calendar.HOUR_OF_DAY, 5);
    calendar.set(Calendar.MINUTE, 30);

//Alarm will be triggered once exactly at 5:30

        alarmManager.setExact(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

1st Param : Type of the Alarm
2nd Param : Time in milliseconds when the alarm will be triggered first
3rd Param :Pending Intent
Note that we have changed the type to RTC as we are using Wall clock time. Also device wont wake up
when the alarm is triggered

/*
Canceling the Alarm:
Canceling a scheduled alarm is much simpler than creating one. You just need to cancel with the same PendingIntent as shown below

alarmManager.cancel(pendingIntent);


You don’t even need  access to the same PendingIntent object for cancelling. You can create a new pending intent with the same request code and FLAG_NO_CREATE and it will return the same PendingIntent object


With FLAG_NO_CREATE it will return null if the PendingIntent doesnt already exist. If it already exists it returns
reference to the existing PendingIntent

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_NO_CREATE);
        if (pendingIntent != null)
            alarmManager.cancel(pendingIntent);


        Conclusion:
        I hope by now you are pretty clear of how the AlarmManager works and how can it be used. If this article helped please like and feel free to comment any of your questions.
 */
/*
        int horaconvo=partido-previaconvo;
        Bitmap bm = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.imagesjpg),
                getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_width),
                getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_height),
                true);

        //Intent ntent = new Intent(this, SelectedService.class);
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, 01, ntent, Intent.FLAG_ONE_SHOT);

        mychannel= new NotificationChannel("micanal","nombre",NotificationManager.IMPORTANCE_HIGH);
        builder = new Notification.Builder(getApplicationContext());
        //builder.setContentTitle("Inicio de Convo");
        builder.setContentText("El icono te recuerda que la convo automatica esta activa");
        //builder.setSubText("Some sub text");
       //builder.setNumber(101);
        //builder.setContentIntent(pendingIntent);
        builder.setTicker("Fancy Notification");
        builder.setSmallIcon(R.drawable.iconval);
        builder.setLargeIcon(bm);
        builder.setAutoCancel(true);
        builder.setOngoing(true);
        //builder.setPriority(0);
        builder.setChannelId("micanal");
        notification = builder.build();
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        notificationManger =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManger.notify(01, notification);
*/
//        PackageManager pm = this.getPackageManager();
//        try {
//            Intent waIntent = new Intent(Intent.ACTION_SEND);
//            waIntent.setType("text/plain");
//            String text = "YOUR TEXT HERE";
//            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
//            waIntent.setPackage("com.whatsapp");
//            waIntent.putExtra(Intent.EXTRA_TEXT, text);
//            startActivity(Intent.createChooser(waIntent, "Share with"));
//        } catch (PackageManager.NameNotFoundException e) {
//            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
//        }
        //Toast.makeText(this, "Servicio"+System.currentTimeMillis(), Toast.LENGTH_SHORT).show();
        return START_STICKY;

    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy(){
        super.onDestroy();
        notificationManger.cancelAll();
        Toast.makeText(this, "des", Toast.LENGTH_SHORT).show();
    }

}
