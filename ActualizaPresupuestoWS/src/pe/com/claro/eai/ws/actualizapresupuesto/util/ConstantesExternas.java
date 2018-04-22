package pe.com.claro.eai.ws.actualizapresupuesto.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantesExternas{
    
    @Value("${codigo.respuesta.idt.1}")
    public String CODIGO_RESPUESTA_IDT_01;
    @Value("${codigo.respuesta.idt.2}")
    public String CODIGO_RESPUESTA_IDT_02;
        
    @Value("${codigo.respuesta.idf.0}")
    public  String CODIGO_RESPUESTA_IDF_00;
    @Value("${codigo.respuesta.idf.1}")
    public  String CODIGO_RESPUESTA_IDF_01;
    @Value("${codigo.respuesta.idf.2}")
    public  String CODIGO_RESPUESTA_IDF_02;
    
    @Value("${mensaje.respuesta.idf.0}")
    public  String MENSAJE_RESPUESTA_IDF_00;
    @Value("${mensaje.respuesta.idf.1}")
    public  String MENSAJE_RESPUESTA_IDF_01;
    @Value("${mensaje.respuesta.idf.2}")
    public  String MENSAJE_RESPUESTA_IDF_02;
    @Value("${mensaje.respuesta.idt.1}")
    public  String MENSAJE_RESPUESTA_IDT_01;
    @Value("${mensaje.respuesta.idt.2}")
    public  String MENSAJE_RESPUESTA_IDT_02;

    @Value("${mensaje.error.inesperado}")
    public  String MENSAJE_ERROR_INESPERADO;
    @Value("${mensaje.datos.incompletos}")
    public  String MENSAJE_DATOS_INCOMPLETOS;
    
    @Value("${owner.db.sgasap}")
    public  String DB_SGASAP_OWNER;
    @Value("${db.sgasap.jndi}")
    public  String DB_SGASAP_JNDI;
    @Value("${sp.actualiza.presupuesto}")
    public  String SP_ACTUALIZA_PRESUPUESTO;
    @Value("${timeout.bd}")
    public  String DB_SGASAP_TIMEOUT_SECONDS;
    @Value("${caracter.separador}")
    public  String CARACTER_SEPARADOR;
    
}
