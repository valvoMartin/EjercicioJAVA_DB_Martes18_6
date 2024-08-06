package org.example;

import org.example.Responses.Response;
import org.example.dtos.postDto;
import org.example.dtos.postNumbersDto;
import org.example.entities.post;
import org.example.services.comentariosServices;
import org.example.services.postsServices;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        postsServices servicePost = new postsServices();
        comentariosServices serviceComentario = new comentariosServices();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Menu:");
            System.out.println("1. Obtener Post pasando un titulo.");
            System.out.println("2. Insertar comentario a un post.");
            System.out.println("3. Obtener el número de comentarios de cada post.");
            System.out.println("4. Obtener todos los posts ordenados por fecha que no tengan comentarios.");

//            System.out.println("3. Obtener libros por autor o año con puntuación de 5");
//            System.out.println("4. Obtener libros con puntuación promedio mayor o igual a 4 y mínimo 4 valoraciones");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    //Obtener Post pasando un titulo
                    System.out.print("Ingrese el titulo del post: ");
                    String tituloPost = scanner.nextLine();
                    Response response = servicePost.getPostIdByTitulo(tituloPost);


                    int post = (int) response.results;

                    if (response.success) {
                        System.out.println(response.message);
                        System.out.println("ID: " + post);

                    } else {
                        System.out.println("Error al obtener el post.");
                    }

                    break;
                case 2:
                    //Insertar comentario a un post
                    System.out.print("Ingrese el titulo del post: ");
                    String titulo = scanner.nextLine();
                    Response postResponse = servicePost.getPostIdByTitulo(titulo);
                    if (postResponse.success) {
                        int postId = (int) postResponse.results; // Assuming the data is the post ID

                        System.out.println("ID: " + postId);
                        System.out.print("Ingrese el autor del comentario: ");
                        String autor = scanner.nextLine();
                        System.out.print("Ingrese el comentario: ");
                        String comentario = scanner.nextLine();

                        Response response2 = serviceComentario.insertComentarioToPost(postId, autor, comentario);
                        System.out.println(response2.message);
                    } else {
                        System.out.println("Error al obtener el ID del post.");
                    }


                    break;
                case 3:
                    //Obtener el número de comentarios de cada post

                    Response response3 = serviceComentario.getNumeroComentariosPorPost();
                    System.out.println(response3.message);

                    if (response3.success) {
                        List<postNumbersDto> results = (List<postNumbersDto>) response3.results;


                        for (postNumbersDto result : results) {
                            System.out.println("Post ID: " + result.getId() + ", Titulo: " + result.getTitulo() + ", Número de comentarios: " + result.getNumeroComentarios());
                        }
                    } else {
                        System.out.println("Error al obtener el número de comentarios por post.");

                    }




                    break;
                case 4:
                    //Obtener todos los posts ordenados por fecha que no tengan comentarios

                    Response response4 = serviceComentario.getPostsSinComentariosOrdenados();
                    System.out.println(response4.message);

                    if (response4.success) {
                        List<postDto> results = (List<postDto>) response4.results;
                        if(results.isEmpty()){
                            System.out.println("No hay posts sin comentarios.");
                        }else {
                            for (postDto result : results) {
                                System.out.println("Titulo: " + result.getTitulo() + ", Contenido: " + result.getContenido() + ", Fecha de publicación: " + result.getFechaPublicacion());
                            }
                        }

                    } else {
                        System.out.println("Error al obtener los posts sin comentarios.");
                    }



                    break;
            }
        } while (option != 0);

        scanner.close();
    }
}
