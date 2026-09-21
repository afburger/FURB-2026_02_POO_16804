package provas.prova01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste da avaliação prática de POO - Turma 004.
 */
class ProdutoTest {

    // ===== EXEMPLO FORNECIDO PELO PROFESSOR =====
    @Test
    public void deveAdicionarQuantidadeValida() {
        Produto p = new Produto(1, "Caneta");
        p.adicionar(50);
        assertEquals(50, p.getQuantidade());
    }

    // ===== IMPLEMENTE SEUS TESTES A PARTIR DAQUI (Questão 5) =====
    @Test 
    public void removerQuantidadeValida() {
        Produto p = new Produto(1, "Teste", 10);
        p.remover(5);
        assertEquals(5, p.getQuantidade());
    }

    @Test
    public void removerQuantidadeInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            Produto p = new Produto(1, "Teste", 10);
            p.remover(-5);
        });

    }

    @Test
    public void removerComSaldoInsuficiente() {
        assertThrows(IllegalStateException.class, () -> {
            Produto p = new Produto(1, "Teste", 10);
            p.remover(25);
        });

    }
}
