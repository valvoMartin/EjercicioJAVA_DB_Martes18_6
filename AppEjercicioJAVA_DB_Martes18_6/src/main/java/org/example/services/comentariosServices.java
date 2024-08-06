package org.example.services;

import jakarta.persistence.NoResultException;
import org.example.Responses.Response;
import org.example.connections.HibernateUtil;
import org.example.dtos.postDto;
import org.example.dtos.postNumbersDto;
import org.example.entities.comentario;
import org.example.entities.post;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class comentariosServices {

    //insertar comentario por un id de post pasado como parametro con hql


    public Response insertComentarioToPost(int postId , String autor, String comentarioText) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            String hql = "SELECT p FROM post p WHERE p.id = :postId";
            Query<post> query = session.createQuery(hql, post.class);
            query.setParameter("postId", postId);

            post post;
            try {
                post = query.getSingleResult();
            } catch (NoResultException e) {
                session.getTransaction().rollback();
                return new Response("No existe el post con el ID especificado", false, null);
            }


            comentario newComentario = new comentario();
            newComentario.setAutor(autor);
            newComentario.setComentario(comentarioText);
            newComentario.setFechaComentario(new Date());
            newComentario.setPost(post);

            session.save(newComentario);
            session.getTransaction().commit();

            return new Response("Transacción Exitosa", true, null);
        } catch (Exception ex) {
            return new Response("Error en la transacción", false, null);
        }
    }



    public Response getNumeroComentariosPorPost() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            String hql = "SELECT p.id, p.titulo, COUNT(c) FROM post p LEFT JOIN p.comentarios c GROUP BY p.id, p.titulo";
            Query<Object[]> query = session.createQuery(hql,  Object[].class);

            List<Object[]> results = query.getResultList();


            List<postNumbersDto> postDtos = results.stream()
                    .map(result -> new postNumbersDto((int) result[0], (String) result[1], (long) result[2]))
                    .collect(Collectors.toList());

            session.getTransaction().commit();


            return new Response("Transacción Exitosa", true, postDtos);
        } catch (Exception ex) {
            return new Response("Error en la transacción", false, null);
        }
    }

    public Response getPostsSinComentariosOrdenados() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            String hql = "SELECT p FROM post p LEFT JOIN p.comentarios c WHERE c IS NULL ORDER BY p.fechaPublicacion DESC";
            Query<post> query = session.createQuery(hql, post.class);

            List<post> results = query.getResultList();
            List<postDto> postDtos = results.stream()
                    .map(post -> new postDto(post.getTitulo(), post.getContenido(), post.getFechaPublicacion(), "Sin Comentarios"))
                    .collect(Collectors.toList());

            session.getTransaction().commit();

            return new Response("Transacción Exitosa", true, postDtos);
        } catch (Exception ex) {
            return new Response("Error en la transacción", false, null);
        }
    }




}
