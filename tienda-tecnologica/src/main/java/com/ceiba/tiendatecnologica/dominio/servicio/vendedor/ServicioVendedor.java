package com.ceiba.tiendatecnologica.dominio.servicio.vendedor;

import com.ceiba.tiendatecnologica.dominio.GarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.excepcion.GarantiaExtendidaException;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioGarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioProducto;

import java.util.Calendar;

public class ServicioVendedor {

	public static final String EL_PRODUCTO_TIENE_GARANTIA = "El producto ya cuenta con una garantía extendida";

	private RepositorioProducto repositorioProducto;
	private RepositorioGarantiaExtendida repositorioGarantia;

	public ServicioVendedor(RepositorioProducto repositorioProducto, RepositorioGarantiaExtendida repositorioGarantia) {
		this.repositorioProducto = repositorioProducto;
		this.repositorioGarantia = repositorioGarantia;
	}

	public void generarGarantia(String codigo) {
           if(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(codigo) != null){
              repositorioGarantia.agregar(new GarantiaExtendida(repositorioProducto.obtenerPorCodigo(codigo))); 
           }
               
           else
               throw new GarantiaExtendidaException(EL_PRODUCTO_TIENE_GARANTIA);
	}

	public boolean tieneGarantia(String codigo) {
            
            return repositorioGarantia.obtenerProductoConGarantiaPorCodigo(codigo) != null;
           
        }
}
