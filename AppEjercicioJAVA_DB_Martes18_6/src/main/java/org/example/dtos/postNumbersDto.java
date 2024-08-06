package org.example.dtos;

public class postNumbersDto {
    private int id;
    private String titulo;
    private long numeroComentarios;;

    public postNumbersDto(int id, String titulo, long numeroComentarios) {
        this.id = id;
        this.titulo = titulo;
        this.numeroComentarios = numeroComentarios;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getNumeroComentarios() {
        return numeroComentarios;
    }

    public void setNumeroComentarios(long numeroComentarios) {
        this.numeroComentarios = numeroComentarios;
    }
}
