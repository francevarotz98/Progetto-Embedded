package embeddedproject.takethepill;


import android.util.Log;

import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class AssumptionEntity {

    // VARIABILI
    private Date data;
    private Time ora;
    private String nomeFarmaco;
    private Boolean stato;
    private Integer dosaggio;
    private String tipoFarmaco;
    private Integer terapia;

    // costruttore per assunzioni DI SOLA LETTURA
    public AssumptionEntity(Date data, Time ora, String nomeFarmaco, Boolean stato, Integer dosaggio,String tipoFarmaco){
        this.data=data;
        this.ora=ora;
        this.nomeFarmaco=nomeFarmaco;
        this.stato=stato;
        this.dosaggio=dosaggio;
        this.tipoFarmaco=tipoFarmaco;
    }
    //costruttore per database
    public AssumptionEntity(Date data, Time ora, int terapia, boolean stato)
    {
        this.data=data;
        this.ora=ora;
        this.terapia=terapia;
        this.stato=stato;
    }
    // Costruttore
    public AssumptionEntity()
    {
    }


    //METODI GET e SET

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    public int getTerapia(){return terapia;}
    public void setTerapia(Integer terapia){this.terapia=terapia;}
    public Time getOra() {
        return ora;
    }

    public void setOra(Time ora) {
        this.ora = ora;
    }

    public String getNomeFarmaco() {
        return nomeFarmaco;
    }

    public void setNomeFarmaco(String nomeFarmaco) {
        this.nomeFarmaco = nomeFarmaco;
    }

    public Boolean getStato() {
        return stato;
    }

    public void setStato(Boolean stato) {
        this.stato = stato;
    }

    public Integer getDosaggio() {
        return dosaggio;
    }

    public void setDosaggio(Integer dosaggio) {
        this.dosaggio = dosaggio;
    }

    public String getTipoFarmaco() {
        return tipoFarmaco;
    }

    public void setTipoFarmaco(String tipoFarmaco) {
        this.tipoFarmaco = tipoFarmaco;
    }


    /**
     * Given a therapy, return a list of assumption about this therapy
     */
    public List<AssumptionEntity> generateAssumption(TherapyEntityDB th, Time hour)
    {
        List<AssumptionEntity> list= new ArrayList<>();
        if(th.getDays()==null)
        { Log.d("errore terapia","numero giorni non trovato");
            return null;}
        int count=th.getDays();
        TimeZone tz=TimeZone.getDefault();
        Calendar calendar=Calendar.getInstance(tz);
        // torno indietro di un giorno per garantire la correttezza del ciclo
        calendar.add(Calendar.DAY_OF_MONTH,-1);

        while(count>0)
        {
            //scorro il calendario un giorno alla volta
            calendar.add(Calendar.DAY_OF_MONTH,1);
            String data=calendar.getTime().toString(); // occhio al formato..da controllare

            Log.d("data processata",data);

            //1=sunday,..7=saturday
            int day= calendar.get(Calendar.DAY_OF_WEEK);

            Log.d("day found",day+"");

            //compare day found and check if it's a day therapy,then add assumption
            if(day==1 &&th.isSun())
            {
                AssumptionEntity current= new AssumptionEntity(calendar.getTime(),hour,th.getID(),false);
                list.add(current);
                count--;
                continue;
            }
            if(day==2 &&th.isMon())
            {
                AssumptionEntity current= new AssumptionEntity(calendar.getTime(),hour,th.getID(),false);
                list.add(current);
                count--;
                continue;
            }
            if(day==3 &&th.isTue())
            {
                AssumptionEntity current= new AssumptionEntity(calendar.getTime(),hour,th.getID(),false);
                list.add(current);
                count--;
                continue;
            }
            if(day==4 &&th.isWed())
            {
                AssumptionEntity current= new AssumptionEntity(calendar.getTime(),hour,th.getID(),false);
                list.add(current);
                count--;
                continue;
            }
            if(day==5 &&th.isThu())
            {
                AssumptionEntity current= new AssumptionEntity(calendar.getTime(),hour,th.getID(),false);
                list.add(current);
                count--;
                continue;
            }
            if(day==6 &&th.isFri())
            {
                AssumptionEntity current= new AssumptionEntity(calendar.getTime(),hour,th.getID(),false);
                list.add(current);
                count--;
                continue;
            }
            if(day==7 &&th.isSat())
            {
                AssumptionEntity current= new AssumptionEntity(calendar.getTime(),hour,th.getID(),false);
                list.add(current);
                count--;
                continue;
            }



        }



        return list;
    }


}
