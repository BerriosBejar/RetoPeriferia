package pe.periferia.tipoCambio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.periferia.tipoCambio.entity.TipoCambio;
import pe.periferia.tipoCambio.repository.TipoCambioRepository;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class TipoCambioServiceImpl {
    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    public BigDecimal convertirMoneda(BigDecimal monto, String monedaOrigen, String monedaDestino) {
        Optional<TipoCambio> tipoCambioOptional = tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino);
        if (tipoCambioOptional.isPresent()) {
            TipoCambio tipoCambio = tipoCambioOptional.get();
            BigDecimal valorTipoCambio = tipoCambio.getValorTipoCambio();
            return monto.multiply(valorTipoCambio);
        } else {
            throw new RuntimeException("Tipo de cambio no encontrado.");
        }
    }

    public TipoCambio guardarTipoCambio(TipoCambio tipoCambio) {
        return tipoCambioRepository.save(tipoCambio);
    }
}
