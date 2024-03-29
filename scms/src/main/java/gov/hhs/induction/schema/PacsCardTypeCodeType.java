//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.01.09 at 11:07:08 PM EST 
//


package gov.hhs.induction.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PacsCardTypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PacsCardTypeCodeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PIV"/&gt;
 *     &lt;enumeration value="Other"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PacsCardTypeCodeType")
@XmlEnum
public enum PacsCardTypeCodeType {


    /**
     * 
     * 						HSPD-12 PIV card
     * 		        	
     * 
     */
    PIV("PIV"),

    /**
     * 
     * 						Generic access card
     * 		        	
     * 
     */
    @XmlEnumValue("Other")
    OTHER("Other");
    private final String value;

    PacsCardTypeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PacsCardTypeCodeType fromValue(String v) {
        for (PacsCardTypeCodeType c: PacsCardTypeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
