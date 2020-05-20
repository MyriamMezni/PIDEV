/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ben younes
 */
public class TestCamera {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Webcam wb =Webcam.getDefault();
        System.out.println("ok");
        
        
        wb.setViewSize(new Dimension(640,480));
        wb.open(true);

        ImageIO.write(wb.getImage(), "JPG", new File("test.jpg"));
    }
}
