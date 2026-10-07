package exemplos.unidade05.interfaces;

public interface Animal extends EmitirSom {

    default boolean isSelvagem() {
        return true;
    }

    int getQuantidadePatas();

}
