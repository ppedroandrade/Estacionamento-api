package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    @Query("SELECT m FROM Movimentacao m WHERE m.saida IS NULL")
    List<Movimentacao> findByAberta();
    @Query("SELECT m.tempoMulta FROM Movimentacao m WHERE m.tempoMulta IS NOT NULL")
    LocalTime findTempoMulta();
}
