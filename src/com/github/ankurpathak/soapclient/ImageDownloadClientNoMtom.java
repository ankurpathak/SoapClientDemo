/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ankurpathak.soapclient;

import com.github.ankurpathak.soap.ImageServer;
import com.github.ankurpathak.soap.ImageServerNoMtom;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author ankur
 */
public class ImageDownloadClientNoMtom{
	
	public static void main(String[] args) throws Exception {
	   
	URL url = new URL("http://localhost:8080/SoapDemo/ImageServerNoMtomImplService?WSDL");
        QName qname = new QName("http://soap.ankurpathak.github.com/", "ImageServerNoMtomImplService");

        Service service = Service.create(url, qname);
        ImageServerNoMtom imageServer = service.getPort(ImageServerNoMtom.class);
 
        /************  test download  ***************/
        Image image = imageServer.downloadImage();
        
        //display it in frame
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        System.out.println("Success!!");
    }

}
