package pe.periferia.tipoCambio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.periferia.tipoCambio.dto.ConversionRequest;
import pe.periferia.tipoCambio.dto.ConversionResponse;
import pe.periferia.tipoCambio.entity.TipoCambio;
import pe.periferia.tipoCambio.service.TipoCambioService;
import pe.periferia.tipoCambio.util.JwtUtil;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/tipo-cambio")
public class TipoCambioController {
    @Autowired
    private TipoCambioService tipoCambioService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/convertir")
    public ResponseEntity<ConversionResponse> convertirMoneda(@RequestHeader("Authorization") String token, @RequestBody ConversionRequest request) {
        String username = jwtUtil.extractUsername(token);

        if (jwtUtil.validateJwtToken(token,username)) {
            BigDecimal montoConvertido = tipoCambioService.convertirMoneda(request);

            ConversionResponse response = new ConversionResponse();
            response.setMonto(request.getMonto());
            response.setMontoConTipoCambio(montoConvertido);
            response.setMonedaOrigen(request.getMonedaOrigen());
            response.setMonedaDestino(request.getMonedaDestino());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/tipo-cambio")
    public ResponseEntity<TipoCambio> guardarTipoCambio(@RequestHeader("Authorization") String token,@RequestBody TipoCambio tipoCambio) {
        String username = jwtUtil.extractUsername(token);

        if (jwtUtil.validateJwtToken(token, username)) {
            TipoCambio tipoCambioGuardado = tipoCambioService.guardarTipoCambio(tipoCambio);
            return ResponseEntity.ok(tipoCambioGuardado);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
