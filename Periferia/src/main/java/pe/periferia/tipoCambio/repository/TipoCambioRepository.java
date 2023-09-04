package pe.periferia.tipoCambio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.periferia.tipoCambio.entity.TipoCambio;

import java.util.Optional;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambio, Long> {
    Optional<TipoCambio> findByMonedaOrigenAndMonedaDestino(String monedaOrigen, String monedaDestino);
}
