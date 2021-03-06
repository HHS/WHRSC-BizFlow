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
 * <p>Java class for FederalOrganizationTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FederalOrganizationTypeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Department"/&gt;
 *     &lt;enumeration value="Agency"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "FederalOrganizationTypeType", namespace = "http://hspd12.hhs.gov/piv/binding/v1")
@XmlEnum
public enum FederalOrganizationTypeType {


    /**
     * 
     * 					The organization is a federal department.
     * 				
     * 
     */
    @XmlEnumValue("Department")
    DEPARTMENT("Department"),

    /**
     * 
     * 					The organization is a federal agency (OPDIV) within
     * 					a federal department.
     * 				
     * 
     */
    @XmlEnumValue("Agency")
    AGENCY("Agency");
    private final String value;

    FederalOrganizationTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FederalOrganizationTypeType fromValue(String v) {
        for (FederalOrganizationTypeType c: FederalOrganizationTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
