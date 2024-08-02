package br.com.fedablio.model;

import java.sql.Date;
import java.sql.Time;

public class Acesso {

    private Date data;
    private Time hora;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
}
