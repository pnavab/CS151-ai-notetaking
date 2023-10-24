package src;

import javax.swing.*;
import java.awt.*;

public class Root extends JFrame {
    // declare a new container to get the content pain
    Container window = getContentPane();

    /* Set Fonts and Colors ======================================= */



    /* Set Program Components ===================================== */


    /* Constructors =============================================== */
    public Root(String title, int width, int height) {
        // create the window frame
        createWindow(title, width, height);

        // add components to the window

    }

    /* Methods ==================================================== */
    public void createWindow(String title, int width, int height) {
        // set title, visibility, size and default close operation
        setTitle(title);
        setVisible(true);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /* Listeners ================================================== */

    public static void main(String[] args) {
        Root test = new Root("NoteTaking", 500, 500);
    }

}