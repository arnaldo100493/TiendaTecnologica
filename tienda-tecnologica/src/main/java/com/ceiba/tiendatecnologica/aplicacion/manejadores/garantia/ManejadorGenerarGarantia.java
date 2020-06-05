package com.ceiba.tiendatecnologica.aplicacion.manejadores.garantia;

import com.ceiba.tiendatecnologica.dominio.Producto;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioGarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.servicio.garantia.ServicioObtenerGarantia;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorGenerarGarantia {
    
    private final ServicioObtenerGarantia servicioObtenerGarantia;
    
    public ManejadorGenerarGarantia(ServicioObtenerGarantia servicioObtenerGarantia) {
        this.servicioObtenerGarantia = servicioObtenerGarantia;
    }
    
    @Transactional
    public void ejecutar(String codigoProducto, String nombreCliente) {
        this.servicioObtenerGarantia.generarGarantia(codigoProducto, nombreCliente);
    }
}
