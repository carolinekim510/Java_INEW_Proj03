import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import java.net.*;
import java.awt.*;
import java.util.Stack;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
class HtmlHandler extends JFrame implements ActionListener, HyperlinkListener {

    JEditorPane html;
    Stack<URL> toBack = new Stack<URL>();
    Stack<URL> toForward = new Stack<URL>();

    public HtmlHandler(String website) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Copyright 2020, Caroline Kim");


        try {
            if(website != null) {

                //converting string into url
                //String s = website;
                URL url = new URL(website);

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

                backBtn.setActionCommand("Back");
                forwardBtn.setActionCommand("Forward");
                backBtn.addActionListener(this);
                forwardBtn.addActionListener(this);

                panel.add(backBtn);
                panel.add(address);
                panel.add(forwardBtn);

                // adding scroll pane
                JScrollPane scroller = new JScrollPane();
                JViewport vp = scroller.getViewport();
                vp.add(html);

                this.getContentPane().add(panel, BorderLayout.NORTH);
                this.getContentPane().add(scroller, BorderLayout.CENTER);
                this.toBack.push(url); // adding default starting page.  The stack of toBack will always have the default page as starter
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
                this.toBack.push(e.getURL());
                this.html.setPage(e.getURL());
                //System.out.println(e.getURL());
                // adding the url to the arraylist

                //TEMP BELOW WILL ERASED
                System.out.println(toBack);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void actionPerformed (ActionEvent e) {
        try{
            if (e.getActionCommand().equals("Back")) {
                URL temp = this.toBack.pop();
                this.html.setPage(temp);
                if (toBack.empty()) {
                    this.toBack.push(temp); // This will keep the stack 0 always was default
                }

                System.out.println(toBack);
                //this.toForward.push(temp);

            }


        }
        catch (Exception io)
        {

        }

    }



}