package pe.periferia.tipoCambio.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ConversionRequest {
    private BigDecimal monto;
    private String monedaOrigen;
    private String monedaDestino;
}
