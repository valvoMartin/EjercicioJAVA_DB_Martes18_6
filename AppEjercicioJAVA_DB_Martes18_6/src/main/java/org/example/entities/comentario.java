package org.example.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comentarios")
public class comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "comentario", nullable = false)
    private String comentario;

    @Column(name = "fecha_comentario", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaComentario;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private post post;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public org.example.entities.post getPost() {
        return post;
    }

    public void setPost(org.example.entities.post post) {
        this.post = post;
    }
}
