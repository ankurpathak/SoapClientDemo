/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ankurpathak.soapclient;

import com.github.ankurpathak.soap.ImageServer;
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
import org.apache.commons.io.FileUtils;

/**
 *
 * @author ankur
 */
public class ByteArrayUploadClient {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/SoapDemo/ImageServerImplService?WSDL");
        QName qname = new QName("http://soap.ankurpathak.github.com/", "ImageServerImplService");

        Service service = Service.create(url, qname);
        ImageServer imageServer = service.getPort(ImageServer.class, new MTOMFeature());

        /**
         * ********** test upload ***************
         */
        byte[] data = FileUtils.readFileToByteArray(ImageServer.PATH_DOWNLOAD.toFile());
        imageServer.uploadByteArray(data);
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
