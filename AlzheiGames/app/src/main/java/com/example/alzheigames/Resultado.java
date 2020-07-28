package com.example.alzheigames;

import java.util.Date;

public class Resultado {
    private String jug_nombre;
    private String jug_puntuacion;
    private String jug_fecha;

    public Resultado(String jug_nombre, String jug_puntuacion, String jug_fecha) {
        this.jug_nombre = jug_nombre;
        this.jug_puntuacion = jug_puntuacion;
        this.jug_fecha = jug_fecha;
    }

    public String getJug_nombre() {
        return jug_nombre;
    }

    public String getJug_puntuacion() {
        return jug_puntuacion;
    }

    public String getJug_fecha() {
        return jug_fecha;
    }

    public void setJug_nombre(String jug_nombre) {
        this.jug_nombre = jug_nombre;
    }

    public void setJug_puntuacion(String jug_puntuacion) {
        this.jug_puntuacion = jug_puntuacion;
    }

    public void setJug_fecha(String jug_fecha) {
        this.jug_fecha = jug_fecha;
    }
}
