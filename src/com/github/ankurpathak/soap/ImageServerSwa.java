/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ankurpathak.soap;

import java.awt.Image;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.annotation.XmlAttachmentRef;

/**
 *
 * @author ankur
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface ImageServerSwa {

    Path PATH_UPLOAD = Paths.get("/","home", "ankur", "soap", "kotak.jpg");

    Path PATH_DOWNLOAD = Paths.get("/", "home", "ankur", "Pictures", "kotak.jpg");

    //download a image from server
   
    
    @WebMethod
    @XmlAttachmentRef
    DataHandler downloadDataHandler();
    
    
    @WebMethod
    void uploadDataHandler(@XmlAttachmentRef DataHandler dataHandler);
    

}
