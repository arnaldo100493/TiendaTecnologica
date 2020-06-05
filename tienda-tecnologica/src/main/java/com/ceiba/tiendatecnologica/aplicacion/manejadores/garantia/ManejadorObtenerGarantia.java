package com.ceiba.tiendatecnologica.aplicacion.manejadores.garantia;

import com.ceiba.tiendatecnologica.dominio.GarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.servicio.garantia.ServicioObtenerGarantia;
import com.ceiba.tiendatecnologica.dominio.servicio.vendedor.ServicioVendedor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorObtenerGarantia {

    private final ServicioObtenerGarantia servicioObtenerGarantia;

    public ManejadorObtenerGarantia(ServicioObtenerGarantia servicioObtenerGarantia) {
        this.servicioObtenerGarantia = servicioObtenerGarantia;        

    }

    @Transactional
    public void ejecutar(String codigo, String nombreCliente) {
        this.servicioObtenerGarantia.generarGarantia(codigo,nombreCliente);
    }
    
    @Transactional
    public GarantiaExtendida consultar(String codigo) {
        return this.servicioObtenerGarantia.consultar(codigo);
    }
  
    
}
