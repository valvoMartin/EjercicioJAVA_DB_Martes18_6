package org.example.dtos;

import java.util.Date;

public class postDto {

    private String titulo;
    private String contenido;
    private Date fechaPublicacion;
    private String comentario;

    public postDto() {
    }

    public postDto(String titulo, String contenido, Date fechaPublicacion, String comentario) {

        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaPublicacion = fechaPublicacion;
        this.comentario = comentario;
    }





    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getComentario() {
        return comentario;
    }



    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
