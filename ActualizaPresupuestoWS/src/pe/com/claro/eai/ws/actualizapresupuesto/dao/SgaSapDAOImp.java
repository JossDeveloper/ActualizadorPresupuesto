package pe.com.claro.eai.ws.actualizapresupuesto.dao;

import java.util.Map;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.com.claro.eai.ws.actualizapresupuesto.dto.DatosTransferencia;
import pe.com.claro.eai.ws.actualizapresupuesto.exception.BDException;
import pe.com.claro.eai.ws.actualizapresupuesto.util.ConstantesExternas;
import oracle.jdbc.OracleTypes;


@Repository
public class SgaSapDAOImp implements SgaSapDAO{
    
    public static final Logger logger = Logger.getLogger(SgaSapDAOImp.class);
    
    @Autowired
    @Qualifier("sgaSapDS")
    private DataSource sgaSapDS = null;
    
    private SimpleJdbcCall objJdbcCall;

    @Autowired
    ConstantesExternas constantesExternas;
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public String actualizarPresupuestoSGA( String msg, DatosTransferencia datos ) throws BDException{
        
        String rptaSalida;
        String mensajeTransaccionMetodo = msg + "[actualizarPresupuestoSGA] ";
        
        try{
            sgaSapDS.setLoginTimeout(Integer.parseInt(constantesExternas.DB_SGASAP_TIMEOUT_SECONDS));
            SqlParameterSource objParametrosIN = new MapSqlParameterSource()
                .addValue( "av_agrupador_ppto", datos.getIdAgrupadorPpto() )
                .addValue( "av_idppto", datos.getIdPresupuesto() )
                .addValue( "av_reserva", datos.getNumeroReserva() )
                .addValue( "av_proveedor", datos.getProveedorSAP() )
                .addValue( "av_pep", datos.getNumeroPep() )
                .addValue( "av_sot", datos.getNumeroSot() )
                .addValue( "av_pep_ppto", datos.getDocumentoPpto() )
                .addValue( "av_ppto_error", datos.getMensajeSap() );
            
            this.objJdbcCall = new SimpleJdbcCall( this.sgaSapDS )
                .withoutProcedureColumnMetaDataAccess()
                .withSchemaName( constantesExternas.DB_SGASAP_OWNER )
                .withProcedureName( constantesExternas.SP_ACTUALIZA_PRESUPUESTO )
                .declareParameters( new SqlParameter("av_agrupador_ppto", OracleTypes.VARCHAR),
                                    new SqlParameter("av_idppto", OracleTypes.VARCHAR),
                                    new SqlParameter("av_reserva", OracleTypes.VARCHAR),
                                    new SqlParameter("av_proveedor", OracleTypes.VARCHAR),
                                    new SqlParameter("av_pep", OracleTypes.VARCHAR),
                                    new SqlParameter("av_sot", OracleTypes.VARCHAR),
                                    new SqlParameter("av_pep_ppto", OracleTypes.VARCHAR),
                                    new SqlParameter("av_ppto_error", OracleTypes.VARCHAR),
                                    new SqlOutParameter("av_respuesta", OracleTypes.VARCHAR));
            
              logger.info(mensajeTransaccionMetodo + "Accediendo BD SGASAP, con JNDI=" + constantesExternas.DB_SGASAP_JNDI);
              logger.info(mensajeTransaccionMetodo + "SP a invocar=" + constantesExternas.SP_ACTUALIZA_PRESUPUESTO);
              logger.info(mensajeTransaccionMetodo + "Datos enviados al SP:"); 
              logger.info(mensajeTransaccionMetodo + "> av_agrupador_ppto=" + datos.getIdAgrupadorPpto());
              logger.info(mensajeTransaccionMetodo + "> av_idppto=" + datos.getIdPresupuesto());
              logger.info(mensajeTransaccionMetodo + "> av_reserva=" + datos.getNumeroReserva());
              logger.info(mensajeTransaccionMetodo + "> av_proveedor=" + datos.getProveedorSAP());
              logger.info(mensajeTransaccionMetodo + "> av_pep=" + datos.getNumeroPep()); 
              logger.info(mensajeTransaccionMetodo + "> av_sot=" + datos.getNumeroSot()); 
              logger.info(mensajeTransaccionMetodo + "> av_pep_ppto=" + datos.getDocumentoPpto()); 
              logger.info(mensajeTransaccionMetodo + "> av_ppto_error=" + datos.getMensajeSap());
              
            objJdbcCall.getJdbcTemplate().setQueryTimeout(Integer.parseInt(constantesExternas.DB_SGASAP_TIMEOUT_SECONDS));
            Map<String,Object> resultMap= objJdbcCall.execute(objParametrosIN);
            rptaSalida = (String) resultMap.get(("av_respuesta"));
            logger.info(mensajeTransaccionMetodo + "Datos devueltos por SP:");
            logger.info(mensajeTransaccionMetodo + "> av_respuesta=" + rptaSalida);

        }catch(Exception e){
            logger.error(mensajeTransaccionMetodo +  "No se ejecuta SP: "+ constantesExternas.SP_ACTUALIZA_PRESUPUESTO, e);
            logger.error(mensajeTransaccionMetodo + e.getMessage());            
            throw new BDException(e.getMessage());
        }
        
        return rptaSalida;
    }
}
