package com.ceiba.tiendatecnologica.dominio.servicio.garantia;

import com.ceiba.tiendatecnologica.dominio.GarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.Producto;
import com.ceiba.tiendatecnologica.dominio.excepcion.GarantiaExtendidaException;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioGarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioProducto;
import static com.ceiba.tiendatecnologica.dominio.servicio.vendedor.ServicioVendedor.EL_PRODUCTO_TIENE_GARANTIA;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class ServicioObtenerGarantia {

    public static final String EL_PRODUCTO_TIENE_GARANTIA = "El producto ya cuenta con una garantía extendida";

    public static final String EL_PRODUCTO_NO_CUENTA_CON_GARANTIA = "Este producto no cuenta con garantía extendida";

    private final RepositorioGarantiaExtendida repositorioGarantiaExtendida;
    private final RepositorioProducto repositorioProducto;

    public ServicioObtenerGarantia(RepositorioGarantiaExtendida repositorioGarantiaExtendidaRepositorio, RepositorioProducto repositorioProducto) {
        this.repositorioGarantiaExtendida = repositorioGarantiaExtendidaRepositorio;
        this.repositorioProducto = repositorioProducto;
    }

    public GarantiaExtendida consultar(String codigo) {
        return this.repositorioGarantiaExtendida.obtener(codigo);
    }

    public void generarGarantia(String codigo, String nombreCliente) {

        //Verificacion para determinar que el producto no tiene una garantia previa
        if (this.repositorioGarantiaExtendida.obtenerProductoConGarantiaPorCodigo(codigo) == null) {
            throw new GarantiaExtendidaException(EL_PRODUCTO_TIENE_GARANTIA);
        }

        //Verificar si el producto no tiene 3 vocales
        if (validarCodigoProducto(codigo)) {
            throw new GarantiaExtendidaException(EL_PRODUCTO_NO_CUENTA_CON_GARANTIA);
        }

        //Obtener valores para crear registro de garantia
        Producto producto = this.repositorioProducto.obtenerPorCodigo(codigo);
        double valorGarantia = obtenerPrecioGarantia(producto);
        Date fechaFinGarantia = obtenerFechaFinGarantia(producto);

        //Persistir la garantia
        GarantiaExtendida garantia = new GarantiaExtendida(producto, new Date(), fechaFinGarantia, valorGarantia, nombreCliente);

        this.repositorioGarantiaExtendida.agregar(garantia);

    }

    private boolean tieneGarantia(String codigo) {
        return this.repositorioGarantiaExtendida.obtenerProductoConGarantiaPorCodigo(codigo) != null;
    }

    private Date obtenerFechaFinGarantia(Producto producto) {
        
        if (producto.getPrecio()> 500000){
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());

            int numberOfDays = 0;
            while (numberOfDays < 200) {
                if (Calendar.MONDAY != calendar.get(Calendar.DAY_OF_WEEK))
                    numberOfDays++;

                calendar.add(Calendar.DATE,1);
            }

            if (Calendar.SUNDAY != calendar.get(Calendar.DAY_OF_WEEK))
                    calendar.add(Calendar.DATE,1);

            return calendar.getTime();
        } else {
            return new Date(100);
        }
    }
    
    

    private double obtenerPrecioGarantia(Producto producto) {
        double precioGarantia = 0;
        double precioProducto = producto.getPrecio();
        if (precioProducto > 500000) {
            precioGarantia = precioProducto * 0.2;
        } else {
            precioGarantia = precioProducto * 0.1;
        }
        return precioGarantia;
    }

    private boolean validarCodigoProducto(String codigo) {
        return validarVocales(codigo);
    }

    private boolean validarVocales(String codigo) {
        int vocales = 0;
        for (int i = 0; i < codigo.length(); i++) {
            if ((codigo.charAt(i) == 'a') || (codigo.charAt(i) == 'A')
                    || (codigo.charAt(i) == 'e') || (codigo.charAt(i) == 'E')
                    || (codigo.charAt(i) == 'i') || (codigo.charAt(i) == 'I')
                    || (codigo.charAt(i) == 'o') || (codigo.charAt(i) == 'O')
                    || (codigo.charAt(i) == 'u') || (codigo.charAt(i) == 'U')) {
                vocales++;
            }
        }
        if (vocales == 3) {
            return false;
        }
        return true;
    }

}
