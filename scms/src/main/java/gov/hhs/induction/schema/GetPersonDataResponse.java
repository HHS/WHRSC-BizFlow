//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.01.09 at 11:07:08 PM EST 
//


package gov.hhs.induction.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ResultCode"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Success"/&gt;
 *               &lt;enumeration value="NotFound"/&gt;
 *               &lt;enumeration value="Failed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PersonInfo" type="{http://hspd12.hhs.gov/scms/idProofing/v1}PersonInformationType" minOccurs="0"/&gt;
 *         &lt;element name="EffectiveIdentityProofLevel" type="{http://hspd12.hhs.gov/scms/idProofing/v1}IdentityProofingLevelType" minOccurs="0"/&gt;
 *         &lt;element name="DesiredIdentityProofLevel" type="{http://hspd12.hhs.gov/scms/idProofing/v1}IdentityProofingLevelType" minOccurs="0"/&gt;
 *         &lt;element name="AssignedUserName" type="{http://hspd12.hhs.gov/scms/idProofing/v1}UserNameType" minOccurs="0"/&gt;
 *         &lt;element name="LastUpdateTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="FailureDetailMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "resultCode",
    "personInfo",
    "effectiveIdentityProofLevel",
    "desiredIdentityProofLevel",
    "assignedUserName",
    "lastUpdateTimestamp",
    "failureDetailMessage"
})
@XmlRootElement(name = "GetPersonDataResponse", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1")
public class GetPersonDataResponse {

    @XmlElement(name = "ResultCode", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1", required = true)
    protected String resultCode;
    @XmlElement(name = "PersonInfo", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1")
    protected PersonInformationType personInfo;
    @XmlElement(name = "EffectiveIdentityProofLevel", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1")
    protected Integer effectiveIdentityProofLevel;
    @XmlElement(name = "DesiredIdentityProofLevel", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1")
    protected Integer desiredIdentityProofLevel;
    @XmlElement(name = "AssignedUserName", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1")
    protected String assignedUserName;
    @XmlElement(name = "LastUpdateTimestamp", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdateTimestamp;
    @XmlElement(name = "FailureDetailMessage", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1")
    protected String failureDetailMessage;

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultCode(String value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the personInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PersonInformationType }
     *     
     */
    public PersonInformationType getPersonInfo() {
        return personInfo;
    }

    /**
     * Sets the value of the personInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonInformationType }
     *     
     */
    public void setPersonInfo(PersonInformationType value) {
        this.personInfo = value;
    }

    /**
     * Gets the value of the effectiveIdentityProofLevel property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEffectiveIdentityProofLevel() {
        return effectiveIdentityProofLevel;
    }

    /**
     * Sets the value of the effectiveIdentityProofLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEffectiveIdentityProofLevel(Integer value) {
        this.effectiveIdentityProofLevel = value;
    }

    /**
     * Gets the value of the desiredIdentityProofLevel property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDesiredIdentityProofLevel() {
        return desiredIdentityProofLevel;
    }

    /**
     * Sets the value of the desiredIdentityProofLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDesiredIdentityProofLevel(Integer value) {
        this.desiredIdentityProofLevel = value;
    }

    /**
     * Gets the value of the assignedUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssignedUserName() {
        return assignedUserName;
    }

    /**
     * Sets the value of the assignedUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssignedUserName(String value) {
        this.assignedUserName = value;
    }

    /**
     * Gets the value of the lastUpdateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    /**
     * Sets the value of the lastUpdateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdateTimestamp(XMLGregorianCalendar value) {
        this.lastUpdateTimestamp = value;
    }

    /**
     * Gets the value of the failureDetailMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailureDetailMessage() {
        return failureDetailMessage;
    }

    /**
     * Sets the value of the failureDetailMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailureDetailMessage(String value) {
        this.failureDetailMessage = value;
    }

}
