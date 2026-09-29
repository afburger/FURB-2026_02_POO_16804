package exercicios.lista08;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Musica musica1 = new Musica("Musica 1", 120, "Artista", "Album");
        Musica musica2 = new Musica("Um barril de chopp", 245, "Banda Cavalinho", "Oktober");

        Podcast podcast = new Podcast("Aulas POO Furb", 900, "André", 1);

        Conteudo conteudo = new Conteudo("Titulo do conteudo", 50);


        System.out.println(musica1);
        System.out.println(musica2);
        System.out.println(podcast);
        System.out.println(conteudo);
       
    }
}
