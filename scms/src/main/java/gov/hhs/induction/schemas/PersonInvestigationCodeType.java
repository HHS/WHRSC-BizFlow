//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.03 at 10:07:04 PM EDT 
//


package gov.hhs.induction.schemas;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PersonInvestigationCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PersonInvestigationCodeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="NONE"/&gt;
 *     &lt;enumeration value="NACI"/&gt;
 *     &lt;enumeration value="CNACI"/&gt;
 *     &lt;enumeration value="ANACI"/&gt;
 *     &lt;enumeration value="SSBI"/&gt;
 *     &lt;enumeration value="NACIC"/&gt;
 *     &lt;enumeration value="BI"/&gt;
 *     &lt;enumeration value="MBI"/&gt;
 *     &lt;enumeration value="LBI"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PersonInvestigationCodeType")
@XmlEnum
public enum PersonInvestigationCodeType {


    /**
     * 
     * 						No background check
     * 		        	
     * 
     */
    NONE,

    /**
     * 
     * 						name & fingerprint checks & written inquiries
     * 		        	
     * 
     */
    NACI,

    /**
     * 
     * 						NACI plus extra checks for employees who work with children.
     * 		        	
     * 
     */
    CNACI,

    /**
     * 
     * 						Access NACI
     * 		        	
     * 
     */
    ANACI,

    /**
     * 
     * 						Single Scope Background Investigation
     * 		        	
     * 
     */
    SSBI,

    /**
     * 
     * 						NACI + credit check
     * 		        	
     * 
     */
    NACIC,

    /**
     * 
     * 						Background Investigation
     * 		        	
     * 
     */
    BI,

    /**
     * 
     * 						Background Investigation
     * 		        	
     * 
     */
    MBI,

    /**
     * 
     * 						Limited Background Investigation
     * 		        	
     * 
     */
    LBI;

    public String value() {
        return name();
    }

    public static PersonInvestigationCodeType fromValue(String v) {
        return valueOf(v);
    }

}