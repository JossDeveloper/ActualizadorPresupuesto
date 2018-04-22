package pe.com.claro.eai.ws.actualizapresupuesto.dao;

import pe.com.claro.eai.ws.actualizapresupuesto.dto.DatosTransferencia;
import pe.com.claro.eai.ws.actualizapresupuesto.exception.BDException;



public interface SgaSapDAO{
    
    public String actualizarPresupuestoSGA(String msg, DatosTransferencia datos) throws BDException;

}
