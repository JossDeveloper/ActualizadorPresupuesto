package pe.com.claro.eai.ws.actualizapresupuesto.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import pe.com.claro.eai.ws.actualizapresupuesto.ActualizarPresupuestoRequest;
import pe.com.claro.eai.ws.actualizapresupuesto.ActualizarPresupuestoResponse;
import pe.com.claro.eai.ws.actualizapresupuesto.AuditResponseType;
import pe.com.claro.eai.ws.actualizapresupuesto.DatosPresupuesto;
import pe.com.claro.eai.ws.actualizapresupuesto.dao.SgaSapDAO;
import pe.com.claro.eai.ws.actualizapresupuesto.dto.DatosTransferencia;
import pe.com.claro.eai.ws.actualizapresupuesto.exception.BDException;
import pe.com.claro.eai.ws.actualizapresupuesto.util.ConstantesExternas;
import pe.com.claro.eai.ws.actualizapresupuesto.util.JAXBUtilitarios;

@Service
public class ActualizaPresupuestoServiceImpl implements ActualizaPresupuestoService{
    
    public static final Logger logger = Logger.getLogger(ActualizaPresupuestoServiceImpl.class);
    
    String rptaSalida;
    
    @Autowired
    private SgaSapDAO sgasapDAO;
    
    @Autowired
    ConstantesExternas constantesExternas;
    
    @Override
    public ActualizarPresupuestoResponse actualizarPresupuesto( ActualizarPresupuestoRequest actualizarPresupuestoRequest ){
        
        String idTransaccion = actualizarPresupuestoRequest.getAuditRequest().getIdTransaccion();
        String idAgrupador = actualizarPresupuestoRequest.getIdAgrupadorPpto(); 
        String mensaje = "[actualizar idTx=" + idTransaccion + "] ";
        double tiempoInicio = System.currentTimeMillis();
        logger.info(mensaje + "[INICIO de metodo actualizarPresupuesto]");
        ActualizarPresupuestoResponse response = new ActualizarPresupuestoResponse();
        DatosTransferencia datos = new DatosTransferencia();
        AuditResponseType audit = new AuditResponseType();
        logger.info(mensaje + "Datos enviados en el Request: "+ JAXBUtilitarios.anyObjectToXmlText(actualizarPresupuestoRequest));
        
        try {
            
            if((idAgrupador != null) && (!idAgrupador.equals(""))){
                List<DatosPresupuesto> lista = new ArrayList<DatosPresupuesto>(actualizarPresupuestoRequest.getListaRequest().getObjetoRequest());
                if(lista.size()>0){
                    logger.info(mensaje + "Cantidad de registros a procesar: " + lista.size());
                    StringBuilder pm[] = new StringBuilder[7];
                    int c = 0;
                    for (DatosPresupuesto dp : lista){
                        c++;
                        if(c>1){
                            for(int i=0;i<pm.length;i++){
                                pm[i].append(constantesExternas.CARACTER_SEPARADOR);
                            }
                        }else{
                            for(int i=0;i<pm.length;i++){
                                pm[i] = new StringBuilder();
                            }
                        }
                        pm[0].append(dp.getIdPresupuesto().toString());
                        pm[1].append(dp.getNumeroReserva().toString());
                        pm[2].append("");
                        pm[3].append("");
                        pm[4].append(dp.getNumeroSot().toString());
                        pm[5].append(dp.getDocumentoPpto().toString());
                        pm[6].append(dp.getMensajeSap().toString());
                    }
                    datos.setIdAgrupadorPpto(actualizarPresupuestoRequest.getIdAgrupadorPpto());
                    datos.setIdPresupuesto(pm[0].toString());
                    datos.setNumeroReserva(pm[1].toString());
                    datos.setProveedorSAP(pm[2].toString());
                    datos.setNumeroPep(pm[3].toString());
                    datos.setNumeroSot(pm[4].toString());
                    datos.setDocumentoPpto(pm[5].toString());
                    datos.setMensajeSap(pm[6].toString());

                    rptaSalida = sgasapDAO.actualizarPresupuestoSGA(mensaje, datos);

                    audit.setCodigoRespuesta(constantesExternas.CODIGO_RESPUESTA_IDF_00);
                    audit.setMensajeRespuesta(constantesExternas.MENSAJE_RESPUESTA_IDF_00);
                    logger.info(mensaje + constantesExternas.MENSAJE_RESPUESTA_IDF_00);
                    
                }else{
                    rptaSalida = constantesExternas.MENSAJE_DATOS_INCOMPLETOS;
                    audit.setCodigoRespuesta(constantesExternas.CODIGO_RESPUESTA_IDF_02);
                    audit.setMensajeRespuesta(constantesExternas.MENSAJE_RESPUESTA_IDF_02);
                    logger.error(mensaje + constantesExternas.MENSAJE_RESPUESTA_IDF_02);
                    logger.error(mensaje + constantesExternas.MENSAJE_ERROR_INESPERADO);
                }
            }else{
                rptaSalida = constantesExternas.MENSAJE_DATOS_INCOMPLETOS;
                audit.setCodigoRespuesta(constantesExternas.CODIGO_RESPUESTA_IDF_01);
                audit.setMensajeRespuesta(constantesExternas.MENSAJE_RESPUESTA_IDF_01);
                logger.error(mensaje + constantesExternas.MENSAJE_RESPUESTA_IDF_01);
                logger.error(mensaje + constantesExternas.MENSAJE_ERROR_INESPERADO);
            }

            audit.setIdTransaccion(idTransaccion);
            response.setAvSalida(rptaSalida);
            
        } catch (BDException e){
            audit.setIdTransaccion(idTransaccion);
            audit.setCodigoRespuesta( constantesExternas.CODIGO_RESPUESTA_IDT_01 );
            audit.setMensajeRespuesta( constantesExternas.MENSAJE_RESPUESTA_IDT_01 );
            logger.error(mensaje + constantesExternas.MENSAJE_RESPUESTA_IDT_01);
            logger.error(mensaje + constantesExternas.MENSAJE_ERROR_INESPERADO);
            logger.error(mensaje + e.getMessage());
        }
        catch (Exception e) {
            audit.setIdTransaccion(idTransaccion);
            audit.setCodigoRespuesta(constantesExternas.CODIGO_RESPUESTA_IDT_02);
            audit.setMensajeRespuesta(constantesExternas.MENSAJE_RESPUESTA_IDT_02);
            logger.error(mensaje + constantesExternas.MENSAJE_RESPUESTA_IDT_02);
            logger.error(mensaje + constantesExternas.MENSAJE_ERROR_INESPERADO);
            logger.error(mensaje + e.getMessage());
        }finally{
            response.setAuditResponse( audit );
        }
        
        logger.info(mensaje + "Datos devueltos en el Response: "+ JAXBUtilitarios.anyObjectToXmlText(response));
        logger.info(mensaje + "[FIN de metodo actualizarPresupuesto]");
        logger.info(mensaje + "Tiempo de ejecucion del metodo actualizarPresupuesto: "+(System.currentTimeMillis()-tiempoInicio));

        return response;
    }
    
}
