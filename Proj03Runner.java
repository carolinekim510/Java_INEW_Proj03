import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import java.net.*;
import java.awt.*;

class Proj03Runner {

    Proj03Runner(){
        System.out.println("I certify that this program is my own work");
        System.out.println("and is not the work of other. I agree not");
        System.out.println("to share my solution with others.");
        System.out.println("Caroline Kim\n");
    }

    public void run(String website) {
        try {
            new HtmlHandler(website);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

// HTML handler for the site to pop in Jframe
class HtmlHandler extends JFrame implements HyperlinkListener {

    JEditorPane html;
    ArrayList<String> toBack;
    ArrayList<String> toForward;

    public HtmlHandler(String website) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Copyright 2020, Caroline Kim");

        try {
            if(website != null) {
                JEditorPane html = new JEditorPane(website);
                html.setEditable(false);
                html.addHyperlinkListener(this);
                // making this html field to not null
                this.html = html;

                // adding back & forward button & address bar
                JPanel panel = new JPanel();
                JButton backBtn = new JButton("Back");
                JButton forwardBtn = new JButton("Forward");
                JTextField address = new JTextField("http://www.dickbaldwin.com/tocdsp.htm");

                panel.add(backBtn);
                panel.add(address);
                panel.add(forwardBtn);

                // adding scroll pane
                JScrollPane scroller = new JScrollPane();
                JViewport vp = scroller.getViewport();
                vp.add(html);


                this.getContentPane().add(panel, BorderLayout.NORTH);
                this.getContentPane().add(scroller, BorderLayout.CENTER);
                this.setSize(669,669);
                this.setVisible(true);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            try {
                // When the link is cliked, this will send the back to the frame
                this.html.setPage(e.getURL());
                // adding the url to the arraylist
                this.toBack.add(e.getURL().toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void actionPerformed (ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            this.toForward.add(this.toBack.toString());
            System.out.println(toBack);

        }
    }



}
