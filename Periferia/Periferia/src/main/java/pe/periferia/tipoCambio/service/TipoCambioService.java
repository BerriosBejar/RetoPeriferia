package pe.periferia.tipoCambio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.periferia.tipoCambio.dto.ConversionRequest;
import pe.periferia.tipoCambio.entity.TipoCambio;
import pe.periferia.tipoCambio.repository.TipoCambioRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TipoCambioService {
    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    public BigDecimal convertirMoneda(ConversionRequest conversionRequest) {
        BigDecimal monto=conversionRequest.getMonto();
        String monedaOrigen=conversionRequest.getMonedaOrigen();
        String monedaDestino=conversionRequest.getMonedaDestino();
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
