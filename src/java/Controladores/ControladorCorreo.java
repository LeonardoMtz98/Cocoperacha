/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entities.Correo;
import java.io.Serializable;
import java.util.Properties;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author monyg
 */
@Named("controladorCorreo")
@SessionScoped
public class ControladorCorreo implements Serializable{

    public ControladorCorreo() {
    }
   
    private boolean EnviarCorreo(Correo c)
    {
        try
        {
            Properties p= new Properties();
            p.put("mail.smtp.host","smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable","true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", c.getUsuarioCorreo());
            p.setProperty("mail.smtp.auth","true");
            
            Session s= Session.getDefaultInstance(p, null);
            BodyPart texto= new MimeBodyPart();
            texto.setText(c.getMensaje());
            
            MimeMultipart m= new MimeMultipart();
            m.addBodyPart(texto);
            
            MimeMessage mensaje=new  MimeMessage(s);
            mensaje.setFrom(new InternetAddress(c.getUsuarioCorreo()));
            mensaje.addRecipient(Message.RecipientType.TO,new InternetAddress(c.getDestino()));
            mensaje.setSubject(c.getAsunto());
            mensaje.setContent(m);
            
            Transport t=s.getTransport("smtp");
            t.connect(c.getUsuarioCorreo(),c.getContrasenia());
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            return true;
        }catch(Exception e)
        {
            System.out.println("Error"+e.toString());
            return false;
        }
        
    }
    public void EnviarCorreo(String Destinatario, int Action)
    {
        Correo c= new Correo();
        c.setContrasenia("Colomos2018");
        c.setUsuarioCorreo("cocoperacha@gmail.com");
        c.setDestino(Destinatario);
        if(Action == 1)
        {
             c.setAsunto("Advertencia sobre el uso de tu cuenta");
             c.setMensaje("Se han recibido reportes acerca del uso de tu cuenta y/o los articulos que"
                + " has publicado, se te da una advertencia y se recomienda de la manera más cordial "
                + "tener más cuidado con el uso de la plataforma. Gracias por usar Cocoperacha"
                + "\n Atte. El administrador");
        }else if(Action == 2)
        {
            c.setAsunto("Lo sentimos. Hemos tenido que suspender el uso de tu cuenta");
            c.setMensaje("Nos estamos poniendo en contacto contigo para informarte de"
                +"que hemos recibido una serie de reportes hacia tu cuenta y/o productos que"
                +"públicas, dadas ya las respectivas advertencias, tu cuenta ha quedado inavilitada"
                +"por un periodo de treinta dias."
                +"Agradecemos tu comprensión."
                +"\n Atte. El administrador");
        }else if(Action == 3)
        {
            c.setAsunto("Lo sentimos. Tu cuenta ha sido cerrada");
            c.setMensaje("Nos estamos poniendo en contacto contigo para informarte de"
                +"que tu cuenta ha sido cerrada de manera definitiva, debidido al mal uso que le has dado."
                +"Agradecemos tu comprensión."
                +"\n Atte. El administrador");

        }
        /*
            c.setAsunto("Lo sentimos. Hemos tenido que suspender el uso de tu cuenta");
            c.setMensaje("Nos estamos poniendo en contacto contigo para informarte de
            que hemos recibido una serie de reportes hacia tu cuenta y/o productos que 
            publicas, dadas ya las respectivas advertencias, tu cuenta ha quedado inavilitada 
            por un periodo de treinta dias.
            Agradecemos tu comprensión.
            \n Atte. El administrador");
        */
        
        /*
            c.setAsunto("Lo sentimos. Tu cuenta ha sido cerrada");
            c.setMensaje("Nos estamos poniendo en contacto contigo para informarte de"
            +"que tu cuenta ha sido cerrada de manera definitiva, debidido al mal uso que le has dado."
            +"Agradecemos tu comprensión."
            +"\n Atte. El administrador");
        */
       
        EnviarCorreo(c);
       
        
    }
}
