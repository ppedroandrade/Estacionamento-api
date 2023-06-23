package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface CondutorRepository extends JpaRepository<Condutor, Long> {
    @Query("SELECT c FROM Condutor c WHERE c.ativo = true")
    List<Condutor> findByAtivo();
    Condutor findByCpf(String cpf);
    @Query("SELECT c.tempoDesconto FROM Condutor c WHERE c.tempoDesconto IS NOT NULL")
    LocalTime findTempoDesconto();
}
