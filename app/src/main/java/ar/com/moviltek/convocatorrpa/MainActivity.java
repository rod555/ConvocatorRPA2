package ar.com.moviltek.convocatorrpa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public int hora=18;
    public int minutos=0;
    public int convo=36;
    public int warning=0;
    public CheckBox checkconvo, checkwarning;
    public EditText horasantewarn,horasanteconvo;
    public CheckBox checkLU,checkMA,checkMI,checkJU,checkVI,checkSA,checkDO;
//public daysoftheweek

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkconvo=(CheckBox) findViewById(R.id.checkconvo);
        checkwarning=(CheckBox) findViewById(R.id.checkwarning);
        horasantewarn=(EditText) findViewById(R.id.horasantewarn);
        horasanteconvo=(EditText) findViewById(R.id.horasanteconvo);
        checkLU=(CheckBox) findViewById(R.id.checkBoxLU);
        checkMA=(CheckBox) findViewById(R.id.checkBoxMA);
        checkMI=(CheckBox) findViewById(R.id.checkBoxMI);
        checkJU=(CheckBox) findViewById(R.id.checkBoxJU);
        checkVI=(CheckBox) findViewById(R.id.checkBoxVI);
        checkSA=(CheckBox) findViewById(R.id.checkBoxSA);
        checkDO=(CheckBox) findViewById(R.id.checkBoxDO);

     /* radioButtonLU=(RadioButton) findViewById(R.id.radioButtonLU);
        radioButtonMA=(RadioButton) findViewById(R.id.radioButtonMA);
        radioButtonMI=(RadioButton) findViewById(R.id.radioButtonMI);
        radioButtonJU=(RadioButton) findViewById(R.id.radioButtonJU);
        radioButtonVI=(RadioButton) findViewById(R.id.radioButtonVI);
        radioButtonSA=(RadioButton) findViewById(R.id.radioButtonSA);
        radioButtonDO=(RadioButton) findViewById(R.id.radioButtonDO);
     */

     /*       checkconvo.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                              //      if(checkconvo.isChecked()){checkconvo.setChecked(false);}else{checkconvo.setChecked(true);}
                                                               //     radioButtonLU.setChecked(!radioButtonLU.isChecked());
                                                                                            }
                                                                }
        );*/
     /*      radioButtonLU.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              //if(radioButtonLU.isChecked()){radioButtonLU.setChecked(false);}else{radioButtonLU.setChecked(true);}
                                       //       Toast.makeText(getApplicationContext(), new String().valueOf(radioButtonLU.isChecked()), Toast.LENGTH_SHORT).show();
                                              radioButtonLU.setChecked(!radioButtonLU.isChecked());
                                              if (radioButtonLU.isChecked()) {
                                                  radioButtonLU.setChecked(false);
                                              }
                                              //  if(checkconvo.isChecked()){checkconvo.setChecked(false);}else{checkconvo.setChecked(true);}

                                              //     Toast.makeText(getApplicationContext(), new String().valueOf(radioButtonLU.isChecked()), Toast.LENGTH_SHORT).show();
                                          }
                                      }
        );*/
     /*      checkBoxMA.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {

                                               Toast.makeText(getApplicationContext(), new String().valueOf(radioButtonMA.isChecked()), Toast.LENGTH_SHORT).show();
                                                // ((RadioButton) v).toggle();
                                                 //radioButtonMA.toggle();
                                                // radioButtonMA.setChecked(!radioButtonMA.isChecked());
                                                 isToggledRadio1 = !isToggledRadio1; //Switch boolean value
                                                 ((RadioButton) v).setChecked( isToggledRadio1 );

                                                 Toast.makeText(getApplicationContext(), new String().valueOf(radioButtonMA.isChecked()), Toast.LENGTH_SHORT).show();


                                             }
                                         }
        );*/

    }

/*
    public void selectorFecha(View fechas){
        Toast.makeText(this, "calendar", Toast.LENGTH_SHORT).show();
        Calendar mycalendar= Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                //edittext.setText(sdf.format(myCalendar.getTime()));

            }

        };


    }

*/

    public void inicioHilo(View inicio){
        //startService(new Intent(getBaseContext(), SelectedService.class));
        Intent i =new Intent (MainActivity.this, SelectedService.class);

        if (((CheckBox) findViewById(R.id.checkconvo)).isChecked()){
            EditText convot=((EditText) findViewById(R.id.horasanteconvo));
            convo=Integer.parseInt(convot.getText().toString());
            i.putExtra("convo", convo);

        }


        if (((CheckBox) findViewById(R.id.checkconvo)).isChecked()){
            EditText convow=((EditText) findViewById(R.id.horasantewarn));
            warning=Integer.parseInt(convow.getText().toString());
            i.putExtra("warning", warning);
        }

        if(checkDO.isChecked()||checkLU.isChecked()||checkMA.isChecked()||checkMI.isChecked()||checkJU.isChecked()||
                  checkVI.isChecked()||checkSA.isChecked()) {

            int n=0;
            if(checkDO.isChecked()){n++;}
            if(checkLU.isChecked()){n++;}
            if(checkMA.isChecked()){n++;}
            if(checkMI.isChecked()){n++;}
            if(checkJU.isChecked()){n++;}
            if(checkVI.isChecked()){n++;}
            if(checkSA.isChecked()){n++;}

            int[] dias=new int[n];
            n=0;

            if(checkDO.isChecked()){ dias[n]=1; n++;}
            if(checkLU.isChecked()){ dias[n]=2; n++;}
            if(checkMA.isChecked()){ dias[n]=3; n++;}
            if(checkMI.isChecked()){ dias[n]=4; n++;}
            if(checkJU.isChecked()){ dias[n]=5; n++;}
            if(checkVI.isChecked()){ dias[n]=6; n++;}
            if(checkSA.isChecked()){ dias[n]=7;}

            i.putExtra("Dias", dias);
            i.putExtra("Hora", hora);
            i.putExtra("Minutos",minutos);

            startService(i);
        }

        else {
            Toast.makeText(this, "Debe Seleccionar dia/s de partido", Toast.LENGTH_LONG).show();}


    }

    public void finHilo(View fin){
       // Toast.makeText(this, "fin", Toast.LENGTH_SHORT).show();
        stopService(new Intent(getBaseContext(), SelectedService.class));

    }

    public void onClickCheckconvo(View checkconvo){
    }

    public void onClickCheckwarning(View chechwarning){}

    public void selectorHora(View view){
        TimePicker time = new TimePicker(this);
        // date picker dialog
        TimePickerDialog mytime = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hora=hourOfDay;
                minutos=minute;
                TextView display=findViewById(R.id.horaselected);
                display.setText(hora+":"+minutos);
            }
        },0,0, true);
        mytime.show();

    }

    public void cargarEvento(View view){}

}
