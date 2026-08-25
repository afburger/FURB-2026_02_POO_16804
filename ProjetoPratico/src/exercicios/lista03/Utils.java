package exercicios.lista03;

public class Utils {

    public static String getDuracaoFormatada(int duracaoSegundos) {
        int minutos = duracaoSegundos / 60;
        int segundos = duracaoSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

}
