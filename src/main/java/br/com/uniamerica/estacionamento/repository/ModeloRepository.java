package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Modelo;
import org.springframework.boot.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    @Query("SELECT x FROM Modelo x WHERE x.ativo = true")
    List<Modelo> findByAtivo();
    Modelo findByNome(String nome);

//    public List<Modelo> findByNome(final String nome);

//    @Query("from Modelo where nome like :nome")
//    public List<Modelo> findByNomeLike(@Param("nome")final String nome);

//    @Query(value = "select * from modelos where nome like :nome", nativeQuery = true)
//    public List<Modelo> findByNomeLikeNative(@Param("nome")final String nome);
}
