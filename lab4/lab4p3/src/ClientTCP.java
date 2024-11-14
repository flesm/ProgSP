import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

class ClientTCP extends Frame implements ActionListener, WindowListener {
    TextField tf, tf1, tf2, tf3, tf4, tf5, tf6;
    TextArea ta;
    Label la, la1, la2, la3, la4;
    Socket sock = null;
    InputStream is = null;
    OutputStream os = null;

    public static void main(String args[]) {
        ClientTCP c = new ClientTCP();
        c.GUI();
    }

    private void GUI() {
        setTitle("КЛІЕНТ");
        tf = new TextField("127.0.0.1");
        tf1 = new TextField("1024");
        tf2 = new TextField();
        tf3 = new TextField();
        tf4 = new TextField();
        tf5 = new TextField();
        tf6 = new TextField();
        ta = new TextArea();
        la = new Label("IP АДРАС");
        la1 = new Label("Порт");
        la2 = new Label("Адпраўка дадзеных");
        la3 = new Label("Вынік");
        la4 = new Label(" ");
        Button btn = new Button("Падключыцца");
        Button btn1 = new Button("Адправіць");

        tf.setBounds(200, 50, 70, 25);
        tf1.setBounds(330, 50, 70, 25);
        tf2.setBounds(150, 200, 50, 25);
        tf3.setBounds(210, 200, 50, 25);
        tf4.setBounds(270, 200, 50, 25);
        tf5.setBounds(330, 200, 50, 25);
        tf6.setBounds(390, 200, 50, 25);
        ta.setBounds(150, 300, 300, 100);
        btn.setBounds(50, 50, 100, 25);
        btn1.setBounds(50, 200, 100, 25);
        la.setBounds(130, 50, 150, 25);
        la1.setBounds(280, 50, 150, 25);
        la2.setBounds(150, 150, 150, 25);
        la3.setBounds(160, 250, 150, 25);
        la4.setBounds(600, 10, 150, 25);

        add(tf);
        add(tf1);
        add(tf2);
        add(tf3);
        add(tf4);
        add(tf5);
        add(tf6);
        add(btn);
        add(btn1);
        add(ta);
        add(la);
        add(la1);
        add(la2);
        add(la3);
        add(la4);

        setSize(600, 600);
        setVisible(true);
        addWindowListener(this);

        btn.addActionListener(al);
        btn1.addActionListener(this);
    }

    public void windowClosing(WindowEvent we) {
        if (sock != null && !sock.isClosed()) {
            try {
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.dispose();
    }

    // не юзаюцца, але асяроддзе ругаецца на іх абавязковае прапісванне
    public void windowActivated(WindowEvent we) {}
    public void windowClosed(WindowEvent we) {}
    public void windowDeactivated(WindowEvent we) {}
    public void windowDeiconified(WindowEvent we) {}
    public void windowIconified(WindowEvent we) {}
    public void windowOpened(WindowEvent we) {}

    public void actionPerformed(ActionEvent e) {
        if (sock == null) {
            return;
        }
        try {
            is = sock.getInputStream();
            os = sock.getOutputStream();

            String numbers = "";
            numbers += tf2.getText() + " ";
            numbers += tf3.getText() + " ";
            numbers += tf4.getText() + " ";
            numbers += tf5.getText() + " ";
            numbers += tf6.getText() + " ";

            os.write(numbers.getBytes());
            byte[] bytes = new byte[1024];
            is.read(bytes);

            String str = new String(bytes, "UTF-8");
            ta.append("Колькасць лікаў, кратных тром: " + str.trim() + "\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                os.close(); // закрыццё выхаднога патоку
                is.close(); // закрыццё ўваходнага патоку
                sock.close(); // закрыццё сокета
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                sock = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
