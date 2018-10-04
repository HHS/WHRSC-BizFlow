//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.03 at 10:07:04 PM EDT 
//


package gov.hhs.induction.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *                 This Type defines the data items that are associated with a person
 *                 whose identity is managed by the IDM.
 *             
 * 
 * <p>Java class for IdmPersonInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdmPersonInfoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EmployeeID" type="{http://hspd12.hhs.gov/federated/enrollment/v1}OpdivEmployeeIDType" minOccurs="0"/&gt;
 *         &lt;element name="HHSID" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PersonIdType"/&gt;
 *         &lt;element name="FirstName" type="{http://hspd12.hhs.gov/federated/enrollment/v1}FirstNameType"/&gt;
 *         &lt;element name="MiddleName" type="{http://hspd12.hhs.gov/federated/enrollment/v1}MiddleNameType" minOccurs="0"/&gt;
 *         &lt;element name="LastName" type="{http://hspd12.hhs.gov/federated/enrollment/v1}LastNameType"/&gt;
 *         &lt;element name="DOB" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="IdentityInfo"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;choice&gt;
 *                   &lt;element name="SSNSuffix"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="[0-9]{4}"/&gt;
 *                         &lt;length value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="VisaNumber" type="{http://hspd12.hhs.gov/federated/enrollment/v1}VisaNumberType"/&gt;
 *                   &lt;element name="ARN" type="{http://hspd12.hhs.gov/federated/enrollment/v1}AlienRegistrationNumberType"/&gt;
 *                   &lt;element name="ForeignId" type="{http://hspd12.hhs.gov/federated/enrollment/v1}ForeignNationalIdInfoType"/&gt;
 *                   &lt;element name="PlaceOfBirthType"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="CityOfBirth" type="{http://hspd12.hhs.gov/federated/enrollment/v1}CityOfBirthType"/&gt;
 *                             &lt;element name="CountryOfBirth" type="{http://hspd12.hhs.gov/federated/enrollment/v1}CountryCodeType"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/choice&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="primaryEmailAddress" type="{http://hspd12.hhs.gov/federated/enrollment/v1}EmailAddressType" minOccurs="0"/&gt;
 *         &lt;element name="alternateEmailAddress" type="{http://hspd12.hhs.gov/federated/enrollment/v1}EmailAddressType" minOccurs="0"/&gt;
 *         &lt;element name="UPN" type="{http://hspd12.hhs.gov/federated/enrollment/v1}UPNType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdmPersonInfoType", namespace = "http://hspd12.hhs.gov/federated/idm/idmsInterface/v1", propOrder = {
    "employeeID",
    "hhsid",
    "firstName",
    "middleName",
    "lastName",
    "dob",
    "identityInfo",
    "primaryEmailAddress",
    "alternateEmailAddress",
    "upn"
})
public class IdmPersonInfoType {

    @XmlElement(name = "EmployeeID")
    protected String employeeID;
    @XmlElement(name = "HHSID", required = true)
    protected String hhsid;
    @XmlElement(name = "FirstName", required = true)
    protected String firstName;
    @XmlElement(name = "MiddleName")
    protected String middleName;
    @XmlElement(name = "LastName", required = true)
    protected String lastName;
    @XmlElement(name = "DOB", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dob;
    @XmlElement(name = "IdentityInfo", required = true)
    protected IdmPersonInfoType.IdentityInfo identityInfo;
    protected String primaryEmailAddress;
    protected String alternateEmailAddress;
    @XmlElement(name = "UPN")
    protected String upn;

    /**
     * Gets the value of the employeeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the value of the employeeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeID(String value) {
        this.employeeID = value;
    }

    /**
     * Gets the value of the hhsid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHHSID() {
        return hhsid;
    }

    /**
     * Sets the value of the hhsid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHHSID(String value) {
        this.hhsid = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the dob property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDOB() {
        return dob;
    }

    /**
     * Sets the value of the dob property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDOB(XMLGregorianCalendar value) {
        this.dob = value;
    }

    /**
     * Gets the value of the identityInfo property.
     * 
     * @return
     *     possible object is
     *     {@link IdmPersonInfoType.IdentityInfo }
     *     
     */
    public IdmPersonInfoType.IdentityInfo getIdentityInfo() {
        return identityInfo;
    }

    /**
     * Sets the value of the identityInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdmPersonInfoType.IdentityInfo }
     *     
     */
    public void setIdentityInfo(IdmPersonInfoType.IdentityInfo value) {
        this.identityInfo = value;
    }

    /**
     * Gets the value of the primaryEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryEmailAddress() {
        return primaryEmailAddress;
    }

    /**
     * Sets the value of the primaryEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryEmailAddress(String value) {
        this.primaryEmailAddress = value;
    }

    /**
     * Gets the value of the alternateEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternateEmailAddress() {
        return alternateEmailAddress;
    }

    /**
     * Sets the value of the alternateEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternateEmailAddress(String value) {
        this.alternateEmailAddress = value;
    }

    /**
     * Gets the value of the upn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPN() {
        return upn;
    }

    /**
     * Sets the value of the upn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPN(String value) {
        this.upn = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;choice&gt;
     *         &lt;element name="SSNSuffix"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="[0-9]{4}"/&gt;
     *               &lt;length value="4"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="VisaNumber" type="{http://hspd12.hhs.gov/federated/enrollment/v1}VisaNumberType"/&gt;
     *         &lt;element name="ARN" type="{http://hspd12.hhs.gov/federated/enrollment/v1}AlienRegistrationNumberType"/&gt;
     *         &lt;element name="ForeignId" type="{http://hspd12.hhs.gov/federated/enrollment/v1}ForeignNationalIdInfoType"/&gt;
     *         &lt;element name="PlaceOfBirthType"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="CityOfBirth" type="{http://hspd12.hhs.gov/federated/enrollment/v1}CityOfBirthType"/&gt;
     *                   &lt;element name="CountryOfBirth" type="{http://hspd12.hhs.gov/federated/enrollment/v1}CountryCodeType"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/choice&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ssnSuffix",
        "visaNumber",
        "arn",
        "foreignId",
        "placeOfBirthType"
    })
    public static class IdentityInfo {

        @XmlElement(name = "SSNSuffix", namespace = "http://hspd12.hhs.gov/federated/idm/idmsInterface/v1")
        protected String ssnSuffix;
        @XmlElement(name = "VisaNumber", namespace = "http://hspd12.hhs.gov/federated/idm/idmsInterface/v1")
        protected String visaNumber;
        @XmlElement(name = "ARN", namespace = "http://hspd12.hhs.gov/federated/idm/idmsInterface/v1")
        protected String arn;
        @XmlElement(name = "ForeignId", namespace = "http://hspd12.hhs.gov/federated/idm/idmsInterface/v1")
        protected ForeignNationalIdInfoType foreignId;
        @XmlElement(name = "PlaceOfBirthType", namespace = "http://hspd12.hhs.gov/federated/idm/idmsInterface/v1")
        protected IdmPersonInfoType.IdentityInfo.PlaceOfBirthType placeOfBirthType;

        /**
         * Gets the value of the ssnSuffix property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSSNSuffix() {
            return ssnSuffix;
        }

        /**
         * Sets the value of the ssnSuffix property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSSNSuffix(String value) {
            this.ssnSuffix = value;
        }

        /**
         * Gets the value of the visaNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVisaNumber() {
            return visaNumber;
        }

        /**
         * Sets the value of the visaNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVisaNumber(String value) {
            this.visaNumber = value;
        }

        /**
         * Gets the value of the arn property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getARN() {
            return arn;
        }

        /**
         * Sets the value of the arn property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setARN(String value) {
            this.arn = value;
        }

        /**
         * Gets the value of the foreignId property.
         * 
         * @return
         *     possible object is
         *     {@link ForeignNationalIdInfoType }
         *     
         */
        public ForeignNationalIdInfoType getForeignId() {
            return foreignId;
        }

        /**
         * Sets the value of the foreignId property.
         * 
         * @param value
         *     allowed object is
         *     {@link ForeignNationalIdInfoType }
         *     
         */
        public void setForeignId(ForeignNationalIdInfoType value) {
            this.foreignId = value;
        }

        /**
         * Gets the value of the placeOfBirthType property.
         * 
         * @return
         *     possible object is
         *     {@link IdmPersonInfoType.IdentityInfo.PlaceOfBirthType }
         *     
         */
        public IdmPersonInfoType.IdentityInfo.PlaceOfBirthType getPlaceOfBirthType() {
            return placeOfBirthType;
        }

        /**
         * Sets the value of the placeOfBirthType property.
         * 
         * @param value
         *     allowed object is
         *     {@link IdmPersonInfoType.IdentityInfo.PlaceOfBirthType }
         *     
         */
        public void setPlaceOfBirthType(IdmPersonInfoType.IdentityInfo.PlaceOfBirthType value) {
            this.placeOfBirthType = value;
        }


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
         *         &lt;element name="CityOfBirth" type="{http://hspd12.hhs.gov/federated/enrollment/v1}CityOfBirthType"/&gt;
         *         &lt;element name="CountryOfBirth" type="{http://hspd12.hhs.gov/federated/enrollment/v1}CountryCodeType"/&gt;
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
            "cityOfBirth",
            "countryOfBirth"
        })
        public static class PlaceOfBirthType {

            @XmlElement(name = "CityOfBirth", namespace = "http://hspd12.hhs.gov/federated/idm/idmsInterface/v1", required = true)
            protected String cityOfBirth;
            @XmlElement(name = "CountryOfBirth", namespace = "http://hspd12.hhs.gov/federated/idm/idmsInterface/v1", required = true)
            @XmlSchemaType(name = "string")
            protected CountryCodeType countryOfBirth;

            /**
             * Gets the value of the cityOfBirth property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCityOfBirth() {
                return cityOfBirth;
            }

            /**
             * Sets the value of the cityOfBirth property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCityOfBirth(String value) {
                this.cityOfBirth = value;
            }

            /**
             * Gets the value of the countryOfBirth property.
             * 
             * @return
             *     possible object is
             *     {@link CountryCodeType }
             *     
             */
            public CountryCodeType getCountryOfBirth() {
                return countryOfBirth;
            }

            /**
             * Sets the value of the countryOfBirth property.
             * 
             * @param value
             *     allowed object is
             *     {@link CountryCodeType }
             *     
             */
            public void setCountryOfBirth(CountryCodeType value) {
                this.countryOfBirth = value;
            }

        }

    }

}
