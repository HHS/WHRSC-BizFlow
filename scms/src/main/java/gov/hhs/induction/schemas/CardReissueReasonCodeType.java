//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.03 at 10:07:04 PM EDT 
//


package gov.hhs.induction.schemas;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CardReissueReasonCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CardReissueReasonCodeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CardLost"/&gt;
 *     &lt;enumeration value="CardStolen"/&gt;
 *     &lt;enumeration value="AppearanceChange"/&gt;
 *     &lt;enumeration value="NameChange"/&gt;
 *     &lt;enumeration value="Other"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CardReissueReasonCodeType")
@XmlEnum
public enum CardReissueReasonCodeType {

    @XmlEnumValue("CardLost")
    CARD_LOST("CardLost"),
    @XmlEnumValue("CardStolen")
    CARD_STOLEN("CardStolen"),
    @XmlEnumValue("AppearanceChange")
    APPEARANCE_CHANGE("AppearanceChange"),
    @XmlEnumValue("NameChange")
    NAME_CHANGE("NameChange"),
    @XmlEnumValue("Other")
    OTHER("Other");
    private final String value;

    CardReissueReasonCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CardReissueReasonCodeType fromValue(String v) {
        for (CardReissueReasonCodeType c: CardReissueReasonCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
