//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.12.04 at 03:23:22 PM EET 
//


package com.mcnz.jee.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdentitiesList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentitiesList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ContentIdentity" type="{http://soap.jee.mcnz.com/}ContentIdentity"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentitiesList", propOrder = {
    "contentIdentity"
})
public class IdentitiesList {

    @XmlElement(name = "ContentIdentity", required = true)
    protected ContentIdentity contentIdentity;

    /**
     * Gets the value of the contentIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link ContentIdentity }
     *     
     */
    public ContentIdentity getContentIdentity() {
        return contentIdentity;
    }

    /**
     * Sets the value of the contentIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentIdentity }
     *     
     */
    public void setContentIdentity(ContentIdentity value) {
        this.contentIdentity = value;
    }

}
