//passs github Relentless2001
package ar.com.moviltek.convocatorrpa;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {

    int myStA;
    int myStB;

    @Override
    public void onReceive(Context context, Intent intent) {
        myStA = intent.getExtras().getInt("diapartido");
        myStB = intent.getExtras().getInt("horapartido");
        // Toast.makeText(context, "alarm", Toast.LENGTH_LONG).show();

        Log.d("alarma es:", "activada ");

        if (intent.getExtras().getString("alarm").equals("convo")) {

            Log.d("convo es:", myStA + "te convoco " + myStB);

            String convoMsg = "https://api.whatsapp.com/send?phone=&text=ConvocatorRPA.%20Ya%20esta%20abierta%20la%20convo%20para%20jugar%20el%20" + myStA + "%20a%20las%20" + myStB + ".%20Anotarse%20en%20" + "http://basquecitoin.byethost11.com" + "&source=&data=&app_absent=";
            Log.d("URI", convoMsg);

            Uri uriUrl = Uri.parse(convoMsg);
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            launchBrowser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(launchBrowser);


            //ComponentName cn = new ComponentName(this, TaxiPlexer.class);
            //intent.setComponent(cn);
/*
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            //sendIntent.setAction(Intent.ACTION_MAIN);
            sendIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "wewew");
            sendIntent.setType("text/plain");
            sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            sendIntent.setPackage("com.whatsapp");
            context.startActivity(sendIntent);
*/

//            Intent sendIntent = new Intent();
//            sendIntent.setAction(Intent.ACTION_SEND);
//            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//            sendIntent.setType("text/plain");
//            Intent shareIntent = Intent.createChooser(sendIntent, null);
//            context.startActivity(shareIntent);
            //However, if you prefer to share directly to WhatsApp and bypass the system picker,
            // you can do so by using setPackage in your intent:
            //This would simply be set right before you call startActivity(sendIntent);
        }
        ;

        if (intent.getExtras().getString("alarm").equals("warning")) {
            Log.d("alarma  es:", "vamos che");
//            if (hay suficientes jugadores){
//                confirmado !!!;
//                Lista jugadores;
//                mensaje a dueño cancha;
//            }
//
//            if (faltan jugadores){
//                Muchachos faltan x jugadores;
//                lista;
//                recuerden que la hora de cancelacion es;
//            }

        }

        if (intent.getExtras().getString("alarm").equals("partido")) {
            // Toast.makeText(context, "partido!!", Toast.LENGTH_LONG).show();
            Log.d("alarma  es:", "partidooo");
        }

    }

}

//        context.startActivity(
//            new Intent(Intent.ACTION_VIEW,
//                Uri.parse(
//                        String.format("whatsapp://send?text=", "ConvocatorRPA. Ya esta abierta " +
//                                "la convo para jugar el" + diapartido[0] + "a las" horapartido[0] + "responder con Estoy para anotarse"
//
//                        )
//                )
//            )
//        );
//        //method used to show IMs
//            List<ResolveInfo> list = null;
//            final Intent email = new Intent(Intent.ACTION_SEND);
//            email.setData(Uri.parse("sms:"));
//            email.putExtra(Intent.EXTRA_TEXT, "" + value);
//            email.setType("text/plain"); // vnd.android-dir/mms-sms
//
//            WindowManager.LayoutParams WMLP = dialogCustomChooser.getWindow()
//                    .getAttributes();
//            WMLP.gravity = Gravity.CENTER;
//            dialogCustomChooser.getWindow().setAttributes(WMLP);
//            dialogCustomChooser.getWindow().setBackgroundDrawable(
//                    new ColorDrawable(android.graphics.Color.TRANSPARENT));
//            dialogCustomChooser.setCanceledOnTouchOutside(true);
//            dialogCustomChooser.setContentView(R.layout.about_dialog);
//            dialogCustomChooser.setCancelable(true);
//            ListView lvOfIms = (ListView) dialogCustomChooser
//                    .findViewById(R.id.listView1);
//            PackageManager pm = context.getPackageManager();
//            List<ResolveInfo> launchables = pm.queryIntentActivities(email, 0);
//            // ////////////new
//            list = new ArrayList<ResolveInfo>();
//            for (int i = 0; i < launchables.size(); i++) {
//                String string = launchables.get(i).toString();
//                Log.d("heh", string);
////check only messangers
//                if (string.contains("whatsapp")) {
//                    list.add(launchables.get(i));
//                }
//            }
//            Collections.sort(list, new ResolveInfo.DisplayNameComparator(pm));
//            int size = launchables.size();
//            adapter = new AppAdapter(pm, list, MainActivity.this);
//            lvOfIms.setAdapter(adapter);
//            lvOfIms.setOnItemClickListener(new OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> arg0, View arg1,
//                                        int position, long arg3) {
//                    ResolveInfo launchable = adapter.getItem(position);
//                    ActivityInfo activity = launchable.activityInfo;
//                    ComponentName name = new ComponentName(
//                            activity.applicationInfo.packageName, activity.name);
//                    email.addCategory(Intent.CATEGORY_LAUNCHER);
//                    email.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                            | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//                    email.setComponent(name);
//                    startActivity(email);
//                    dialogCustomChooser.dismiss();
//                }
//            });
//            dialogCustomChooser.show();



        //        pm = context.getPackageManager();
//
//        try {
//            Intent waIntent = new Intent(Intent.ACTION_SEND);
//            waIntent.setType("text/plain");
//            String text = "ConvocatorRPA TEST. Para jugar responder con el link(SMS)";
//            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
//            waIntent.setPackage("com.whatsapp");
//            waIntent.putExtra(Intent.EXTRA_TEXT, text);
//            context.startActivity(Intent.createChooser(waIntent, "Share with"));
//        } catch (PackageManager.NameNotFoundException e) {
//            Toast.makeText(context, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
//        }

/*
    DBAdapter mDba;
    SQLiteDatabase mDb;
    Ringtone rt;
    MediaPlayer mp;
    AlertDialog.Builder alertbox;
    Context ctx;

    @Override
    public void onReceive(Context context, Intent intent) {

        DBHelper mDbh = new DBHelper(context, null, null, 1);
        mDb = mDbh.getWritableDatabase();
        mDb.setLockingEnabled(true);
        mDba = new DBAdapter(context);
        mDba.open();
        Cursor cr = mDb.query("mReminderEntry", null, null, null, null,
                null, null);
        if (cr.equals(null)) {
            System.out.println("No Data Found");
        } else {
            Date d = new Date();
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            String today = null;
            if (day == 2) {
                today = "Monday";
            } else if (day == 3) {
                today = "Tuesday";
            } else if (day == 4) {
                today = "Wednesday";
            } else if (day == 5) {
                today = "Thursday";
            } else if (day == 6) {
                today = "Friday";
            } else if (day == 7) {
                today = "Saturday";
            } else if (day == 1) {
                today = "Sunday";
            }

            int system_hour = d.getHours();
            int system_minute = d.getMinutes();
            cr.moveToFirst();
            for (int i = 0; i < cr.getCount(); i++) {
                if (cr.getString(3).equals(system_hour + ":" + system_minute)
                        && cr.getString(1).equals("Daily")) {
                    Intent scheduledIntent = new Intent(context, MyScheduledActivity.class);
                    scheduledIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(scheduledIntent);

                    break;

                } else if (cr.getString(3).equals(
                        system_hour + ":" + system_minute)
                        && cr.getString(1).equals(today)) {

                    Intent scheduledIntent = new Intent(context, MyScheduledActivity.class);
                    scheduledIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(scheduledIntent);

                    break;
                } else {
                    System.out.println("No Matching");
                }
                cr.moveToNext();
            }
        }
        cr.close();
        mDba.close();
    }
*/



















//public class AlarmService_Service extends Service {
// NotificationManager mNM;
//
//@Override
//public void onCreate() {
//        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//
//        // show the icon in the status bar
//        showNotification();
//
//        // Start up the thread running the service.  Note that we create a
//        // separate thread because the service normally runs in the process's
//        // main thread, which we don't want to block.
//        Thread thr = new Thread(null, mTask, "AlarmService_Service");
//        thr.start();
//        }
//
//@Override
//public void onDestroy() {
//        // Cancel the notification -- we use the same ID that we had used to start it
//        mNM.cancel(R.string.alarm_service_started);
//
//        // Tell the user we stopped.
//        Toast.makeText(this, R.string.alarm_service_finished, Toast.LENGTH_SHORT).show();
//        }
//
//        /**
//         * The function that runs in our worker thread
//         */
//        Runnable mTask = new Runnable() {
//                                public void run() {
//                                                    // Normally we would do some work here...  for our sample, we will
//        // just sleep for 30 seconds.
//                                                     long endTime = System.currentTimeMillis() + 15*1000;
//                                                     while (System.currentTimeMillis() < endTime) {
//                                                                                                    synchronized (mBinder) {try {mBinder.wait(endTime - System.currentTimeMillis());
//                                                                                                                                }
//                                                                                                                            catch (Exception e) { }
//                                                                                                                            }
//                                                                                                  }
//                                                    // Done with our work...  stop the service!
//                                                     AlarmService_Service.this.stopSelf();
//                                                 }
//                                        };
//
//@Override
//public IBinder onBind(Intent intent) {
//        return mBinder;
//        }
//
///**
// * Show a notification while this service is running.
// */
//private void showNotification() {
//        // In this sample, we'll use the same text for the ticker and the expanded notification
//        CharSequence text = getText(R.string.alarm_service_started);
//
//        // Set the icon, scrolling text and timestamp
//        Notification notification = new Notification(R.drawable.stat_sample, text,
//        System.currentTimeMillis());
//
//        // The PendingIntent to launch our activity if the user selects this notification
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//        new Intent(this, AlarmService.class), 0);
//
//        // Set the info for the views that show in the notification panel.
//        notification.setLatestEventInfo(this, getText(R.string.alarm_service_label),
//        text, contentIntent);
//
//        // Send the notification.
//        // We use a layout id because it is a unique number.  We use it later to cancel.
//        mNM.notify(R.string.alarm_service_started, notification);
//        }
//
///**
// * This is the object that receives interactions from clients.  See RemoteService
// * for a more complete example.
// */
//private final IBinder mBinder = new Binder() {
//                                                @Override
//                                                protected boolean onTransact(int code, Parcel data, Parcel reply,int flags)
//                                                                            throws RemoteException {return super.onTransact(code, data, reply, flags);}
//                                                };
//
//
//}