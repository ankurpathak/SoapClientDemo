/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ankurpathak.soapclient;

import com.github.ankurpathak.soap.ImageServer;
import java.net.URL;
import javax.activation.DataHandler;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author ankur
 */
public class DataHandlerDownloadClient {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:8080/SoapDemo/ImageServerImplService?WSDL");
        QName qname = new QName("http://soap.ankurpathak.github.com/", "ImageServerImplService");

        Service service = Service.create(url, qname);
        ImageServer imageServer = service.getPort(ImageServer.class);

        /**
         * ********** test download  **************
         */
        DataHandler data = imageServer.downloadDataHandler();

        //display it in frame
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        JLabel label = new JLabel(new ImageIcon(IOUtils.toByteArray(data.getInputStream())));
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        System.out.println("Success!!");
    }

}
