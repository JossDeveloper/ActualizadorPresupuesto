package pe.com.claro.eai.ws.actualizapresupuesto.util;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import java.lang.reflect.Field;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

/**
 * Utilitario creado para parsear objetos a XML y viceversa utilizando la API
 * y los marshallers de JAXB.
 * @author Freddy Bendezu Figueroa
 */
public class JAXBUtilitarios {

    private static Logger logger =
        Logger.getLogger(JAXBUtilitarios.class);

	private static HashMap<Class, JAXBContext> mapContexts =
        new HashMap<Class, JAXBContext>();


    private static JAXBContext obtainJaxBContextFromClass(Class clas) {

        JAXBContext context;
        context = mapContexts.get(clas);

        if (context == null) {

            try {
                context =
                        JAXBContext.newInstance(clas); //JaxBContext.newInstance es muy costoso
                mapContexts.put(clas, context);
            } catch (Exception e) {
                logger.error("Error creando JAXBContext:", e);
            }
        }
        return context;
    }

    public static String jaxBToXmlText(Object objJaxB) {
        String commandoRequestEnXml = null;

        JAXBContext context;

        try {
            context = obtainJaxBContextFromClass(objJaxB.getClass()); //se hace esto para mejorar performance.
            Marshaller marshaller = context.createMarshaller();
            StringWriter xmlWriter = new StringWriter();
            marshaller.marshal(objJaxB, xmlWriter);

            XmlObject xmlObj = XmlObject.Factory.parse(xmlWriter.toString());

            commandoRequestEnXml = xmlObj.toString();

        } catch (Exception e) {
            logger.error("Error parseando object to xml:", e);
        }

        return commandoRequestEnXml;
    }

    public static String anyObjectToXmlText(Object anyObject) {
        String commandoRequestEnXml = null;

        JAXBContext context;

        try {
            context =
                    obtainJaxBContextFromClass(anyObject.getClass()); //se hace esto para mejorar performance.
            Marshaller marshaller = context.createMarshaller();

            StringWriter xmlWriter = new StringWriter();
            marshaller.marshal(new JAXBElement(new QName("",
                                                         anyObject.getClass().getSimpleName()),
                                               anyObject.getClass(),
                                               anyObject), xmlWriter);

            XmlObject xmlObj = XmlObject.Factory.parse(xmlWriter.toString());

            commandoRequestEnXml = xmlObj.toString();
        } catch (Exception e) {
            logger.error("Error parseando object to xml:", e);
        }

        return commandoRequestEnXml;
    }

    public static Object xmlTextToJaxB(String xmlText, Class clas) {

        JAXBContext context;
        Object object = null;

        try {
            context =
                    obtainJaxBContextFromClass(clas); //se hace esto para mejorar performance.
            Unmarshaller um = context.createUnmarshaller();

            InputStream is =
                new ByteArrayInputStream(xmlText.getBytes("UTF-8"));

            object = um.unmarshal(is);

        } catch (Exception e) {
            logger.error("Error unmarshalling xmlObject:" + xmlText +
                         ". Detalle Error:", e);
        }
        return object;
    }
    
	static public String log(Object obj) {

		try {
			Class<?> clazz = obj.getClass();

			Field[] classes = clazz.getDeclaredFields();

			HashMap<String, String> map = new HashMap<String, String>();

			for (Field field : classes) {

				boolean flag = field.isAccessible();
				field.setAccessible(true);
				map.put(field.getName(), String.valueOf(field.get(obj)));
				field.setAccessible(flag);
			}

			return map.toString();
		} catch (Exception ex) {

		}

		return null;
	}
}
