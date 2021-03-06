//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.01.09 at 11:07:08 PM EST 
//


package gov.hhs.induction.schema;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         		This element contains data items related to a PACS persons card.
 *         	
 * 
 * <p>Java class for PacsCardInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PacsCardInformationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CardId" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PacsCardIdentifiersType" minOccurs="0"/&gt;
 *         &lt;element name="CardType" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PacsCardTypeCodeType" minOccurs="0"/&gt;
 *         &lt;element name="CardState" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PacsCardStateCodeType" minOccurs="0"/&gt;
 *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="IssueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="FacialImage" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PacsFacialImageInfoType" minOccurs="0"/&gt;
 *         &lt;element name="FingerPrints" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PacsFingerPrintInfoType" minOccurs="0"/&gt;
 *         &lt;element name="CardLost" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="CardStolen" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PacsCardInformationType", propOrder = {
    "cardId",
    "cardType",
    "cardState",
    "expirationDate",
    "issueDate",
    "facialImage",
    "fingerPrints",
    "cardLost",
    "cardStolen"
})
public class PacsCardInformationType {

    @XmlElementRef(name = "CardId", namespace = "http://hspd12.hhs.gov/federated/enrollment/v1", type = JAXBElement.class, required = false)
    protected JAXBElement<PacsCardIdentifiersType> cardId;
    @XmlElement(name = "CardType")
    @XmlSchemaType(name = "string")
    protected PacsCardTypeCodeType cardType;
    @XmlElement(name = "CardState")
    @XmlSchemaType(name = "string")
    protected PacsCardStateCodeType cardState;
    @XmlElement(name = "ExpirationDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expirationDate;
    @XmlElement(name = "IssueDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar issueDate;
    @XmlElementRef(name = "FacialImage", namespace = "http://hspd12.hhs.gov/federated/enrollment/v1", type = JAXBElement.class, required = false)
    protected JAXBElement<PacsFacialImageInfoType> facialImage;
    @XmlElementRef(name = "FingerPrints", namespace = "http://hspd12.hhs.gov/federated/enrollment/v1", type = JAXBElement.class, required = false)
    protected JAXBElement<PacsFingerPrintInfoType> fingerPrints;
    @XmlElement(name = "CardLost", defaultValue = "false")
    protected Boolean cardLost;
    @XmlElement(name = "CardStolen", defaultValue = "false")
    protected Boolean cardStolen;

    /**
     * Gets the value of the cardId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PacsCardIdentifiersType }{@code >}
     *     
     */
    public JAXBElement<PacsCardIdentifiersType> getCardId() {
        return cardId;
    }

    /**
     * Sets the value of the cardId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PacsCardIdentifiersType }{@code >}
     *     
     */
    public void setCardId(JAXBElement<PacsCardIdentifiersType> value) {
        this.cardId = value;
    }

    /**
     * Gets the value of the cardType property.
     * 
     * @return
     *     possible object is
     *     {@link PacsCardTypeCodeType }
     *     
     */
    public PacsCardTypeCodeType getCardType() {
        return cardType;
    }

    /**
     * Sets the value of the cardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PacsCardTypeCodeType }
     *     
     */
    public void setCardType(PacsCardTypeCodeType value) {
        this.cardType = value;
    }

    /**
     * Gets the value of the cardState property.
     * 
     * @return
     *     possible object is
     *     {@link PacsCardStateCodeType }
     *     
     */
    public PacsCardStateCodeType getCardState() {
        return cardState;
    }

    /**
     * Sets the value of the cardState property.
     * 
     * @param value
     *     allowed object is
     *     {@link PacsCardStateCodeType }
     *     
     */
    public void setCardState(PacsCardStateCodeType value) {
        this.cardState = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the issueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the value of the issueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setIssueDate(XMLGregorianCalendar value) {
        this.issueDate = value;
    }

    /**
     * Gets the value of the facialImage property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PacsFacialImageInfoType }{@code >}
     *     
     */
    public JAXBElement<PacsFacialImageInfoType> getFacialImage() {
        return facialImage;
    }

    /**
     * Sets the value of the facialImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PacsFacialImageInfoType }{@code >}
     *     
     */
    public void setFacialImage(JAXBElement<PacsFacialImageInfoType> value) {
        this.facialImage = value;
    }

    /**
     * Gets the value of the fingerPrints property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PacsFingerPrintInfoType }{@code >}
     *     
     */
    public JAXBElement<PacsFingerPrintInfoType> getFingerPrints() {
        return fingerPrints;
    }

    /**
     * Sets the value of the fingerPrints property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PacsFingerPrintInfoType }{@code >}
     *     
     */
    public void setFingerPrints(JAXBElement<PacsFingerPrintInfoType> value) {
        this.fingerPrints = value;
    }

    /**
     * Gets the value of the cardLost property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCardLost() {
        return cardLost;
    }

    /**
     * Sets the value of the cardLost property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCardLost(Boolean value) {
        this.cardLost = value;
    }

    /**
     * Gets the value of the cardStolen property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCardStolen() {
        return cardStolen;
    }

    /**
     * Sets the value of the cardStolen property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCardStolen(Boolean value) {
        this.cardStolen = value;
    }

}
