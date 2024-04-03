package pt.ubi.di.pdm;


import static pt.ubi.di.pdm.CalendarUtils.daysInMonthArray;
import static pt.ubi.di.pdm.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
COMO USAR: Clicar no alerta para ver tarefas/eventos cujo o prazo está proximo.
           Vista Semanal Permite a Criaçao/Remoçao/Visualizaçao de Eventos de dada Semana
           Utizar as Setas para navegar entre meses e semanas
 */
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    Button btnclose;
    AlertDialog.Builder builder;
    public String myto;
    private List<Event> lista22;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        LocalDate now1 = LocalDate.now();
        LocalDate now2 = now1.plusDays(13);
        LocalDate now3 = now1.plusDays(40);
        Event newEvent1 = new Event("Frequencia",now2,"12:00","Algebra");
        Event newEvent3 = new Event("Trabalho",now3,"23:59","Programaçao");
        if(newEvent1.getDate().getDayOfYear()-now1.getDayOfYear()<15){
            Event.lista33.add(newEvent1);
            Event.lista44+="Faltam "+(newEvent1.getDate().getDayOfYear()-now1.getDayOfYear()+" dias para ");
            Event.lista44+=newEvent1.getName();
            Event.lista44+=" ";
            Event.lista44+=newEvent1.getDate().toString();
            Event.lista44+=" UC: ";
            Event.lista44+=newEvent1.getUC();
            Event.lista44+=System.lineSeparator();
        }
        Event.eventsList.add(newEvent1);
        Event.eventsList.add(newEvent3);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnclose=findViewById(R.id.alertbutton);
        LocalDateTime now = LocalDateTime.now();
        builder=new AlertDialog.Builder(this);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "";
                for(int i =0;i<Event.lista33.size();i++){
                    s+="Faltam "+(Event.lista33.get(i).getDate().getDayOfYear()-now.getDayOfYear()+" dias para "+Event.lista33.get(i).getName()+" "+Event.lista33.get(i).getUC()+System.lineSeparator());
                }
                if (Event.eventsList.size() != 0) {
                    builder.setTitle("Eventos Com Data Limite Inferior a 15 Dias")
                            .setMessage(s)
                            .setCancelable(true)
                            .setNegativeButton("OK!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                } else
                    builder.setTitle("Eventos Com Data Limite Inferior a 15 Dias")
                            .setMessage("Nenhum")
                            .setCancelable(true)
                            .setNegativeButton("OK!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.cancel();
                                }
                            })
                            .show();


            }


        });

        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();

    }


    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        if(date != null)
        {
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }

    public void weeklyAction(View view)
    {
        startActivity(new Intent(this, WeekViewActivity.class));
    }
}








