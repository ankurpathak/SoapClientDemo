/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ankurpathak.soapclient;

import com.github.ankurpathak.soap.ImageServer;
import com.github.ankurpathak.soap.ImageServerNoMtom;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.MTOMFeature;

/**
 *
 * @author ankur
 */
public class ImageUploadClientNoMtom {

    public static void main(String[] args) throws Exception {
        
        File previous = ImageServer.PATH_UPLOAD.toFile();
        
        if(previous.exists()){
            previous.delete();
        }
        
        URL url = new URL("http://localhost:8080/SoapDemo/ImageServerNoMtomImplService?WSDL");
        QName qname = new QName("http://soap.ankurpathak.github.com/", "ImageServerNoMtomImplService");

        Service service = Service.create(url, qname);
        ImageServerNoMtom imageServer = service.getPort(ImageServerNoMtom.class);

        /**
         * ********** test upload ***************
         */
        Image imgUpload = ImageIO.read(ImageServer.PATH_DOWNLOAD.toFile());
        imageServer.uploadImage(imgUpload);

        Image imgUploaded = ImageIO.read(ImageServer.PATH_UPLOAD.toFile());

        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        JLabel label = new JLabel(new ImageIcon(imgUploaded));
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        System.out.println("Success!!");

    }
}
