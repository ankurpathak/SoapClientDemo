/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ankurpathak.soapclient;

import com.github.ankurpathak.soap.ImageServer;
import com.github.ankurpathak.soap.ImageServerSwa;
import com.sun.istack.internal.ByteArrayDataSource;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.logging.FileHandler;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.FileTypeMap;
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
public class DataHandlerUploadClientSwa {

    public static void main(String[] args) throws Exception {
         File previous = ImageServer.PATH_UPLOAD.toFile();
        
        if(previous.exists()){
            previous.delete();
        }
        
        URL url = new URL("http://localhost:8081/SoapDemo/ImageServerSwaImplService?WSDL");
        QName qname = new QName("http://soap.ankurpathak.github.com/", "ImageServerSwaImplService");

        Service service = Service.create(url, qname);
        ImageServerSwa imageServer = service.getPort(ImageServerSwa.class);

        byte[] data = FileUtils.readFileToByteArray(ImageServer.PATH_DOWNLOAD.toFile());
        ByteArrayDataSource dataSource = new ByteArrayDataSource(data, "image/jpeg");
        DataHandler dataHandler = new DataHandler(dataSource);
        imageServer.uploadDataHandler(dataHandler);
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
