package pt.ubi.di.pdm;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity
{
    private EditText eventNameET, eventTimeTV,eventUCET;
    private TextView eventDateTV;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        eventDateTV.setText("Data: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
    }

    private void initWidgets()
    {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV=findViewById(R.id.eventTimeTV);
        eventUCET=findViewById(R.id.eventUCET);
    }

    public void saveEventAction(View view)
    {
        String eventName = eventNameET.getText().toString();
        String eventTime = eventTimeTV.getText().toString();
        LocalDateTime now = LocalDateTime.now();
        LocalDate now1 = LocalDate.now();
        String eventUC= eventUCET.getText().toString();
        LocalDate now2 = now1.plusDays(13);
        LocalDate now3 = now1.plusDays(40);
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate,eventTime,eventUC);
        Event newEvent1 = new Event("Frequencia",now2,"12:00","Algebra");
        Event newEvent3 = new Event("Trabalho",now3,"23:59","Programaçao");



        if(newEvent.getDate().getDayOfYear()-now.getDayOfYear()<15){
            Event.lista33.add(newEvent);
            Event.lista44+="Faltam "+(newEvent.getDate().getDayOfYear()-now.getDayOfYear()+" dias para ");
            Event.lista44+=newEvent.getName();
            Event.lista44+=" ";
            Event.lista44+=newEvent.getDate().toString();
            Event.lista44+=" UC: ";
            Event.lista44+=newEvent.getUC();
            Event.lista44+=System.lineSeparator();
        }
        Event.eventsList.add(newEvent);
        Event.eventsList.add(newEvent1);
        Event.eventsList.add(newEvent3);
        finish();
    }}

