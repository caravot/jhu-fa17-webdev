import bookingrate.BookingDay;
import bookingrate.Rates;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class hw6 extends JFrame {
    // Create class level variables
    private final JComboBox<Rates.HIKE> hikeComboBox = new JComboBox<Rates.HIKE>(Rates.HIKE.values());
    private final JComboBox<Integer> hikeRatesComboBox = new JComboBox<Integer>();
    private final JLabel hikeName = new JLabel("Hike Name: ");
    private final JLabel hikeDuration = new JLabel("Hike Duration (days): ");
    private final JLabel startDate = new JLabel("Start Date (MM/DD/YYYY): ");
    private final JLabel totalCost = new JLabel("Total Cost: ");
    private final JButton submitBtn = new JButton("Submit");
    private final JLabel totalCostField = new JLabel("");
    private MaskFormatter mask;
    private JFormattedTextField startDateValue;
    private final GridBagConstraints gbc = new GridBagConstraints();

    // web server socket information
    private final String SERVER_HOSTNAME = "web6.jhuep.com";
    private final int SERVER_PORT = 20025;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new hw6();
    }

    /** Creates a new instance of Main */
    public hw6() {
        // set the title of the jframe
        super(" Beartooth Hiking Company (BHC) Booking");

        // set a row/col layout for readability
        setLayout(new GridBagLayout());

        // load the mask format for the start date
        try {
            // require format MM/DD/YYYY
            mask = new MaskFormatter("##/##/####");
            startDateValue = new JFormattedTextField(mask);
            startDateValue.setFont(new Font("monospaced", Font.PLAIN, 12));
        } catch (ParseException pe) {
            System.err.println("Couldn't parse MaskFormatter field");
            System.exit(0);
        }

        // set default start value
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String todaysDate = sdf.format(cal.getTime());
        startDateValue.setText(todaysDate);

        // First column
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 0.0;
        add(hikeName, gbc);
        add(hikeDuration, gbc);
        add(startDate, gbc);
        add(totalCost, gbc);

        // Second column
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        hikeComboBox.setMaximumSize(hikeComboBox.getPreferredSize());
        hikeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(hikeComboBox, gbc);

        // listen for when the dropdown of a hike changes
        hikeComboBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // get selected item
                String str = hikeComboBox.getSelectedItem().toString();
                Rates rates = new Rates(Rates.HIKE.valueOf(str));

                // remove previous hike durations
                hikeRatesComboBox.removeAllItems();

                // loop over selected hike's duration day options
                for (int i: rates.getDurations()) {
                    hikeRatesComboBox.addItem(i);
                }
            }
        });

        hikeRatesComboBox.setMaximumSize(hikeRatesComboBox.getPreferredSize());
        hikeRatesComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        hikeComboBox.setSelectedIndex(0);
        add(hikeRatesComboBox, gbc);
        add(startDateValue, gbc);
        add(totalCostField, gbc);

        // Row that spans across all columns for the submit button
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(submitBtn, gbc);

        // show jframe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        // submit results to get total cost of hike
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                getCost();
            }
        });
    }

    /**
     * Basic error dialog box
     * @param msg Message to display
     */
    private void showErrorDialog(String msg) {
        JOptionPane dialog = new JOptionPane();
        dialog.showMessageDialog(
                this,
                msg,
                "Error",
                JOptionPane.ERROR_MESSAGE );
    }

    /** Calculate total cost of hike
     */
    private void getCost() {
        // get instance to use for parsing the start date
        Calendar cal = Calendar.getInstance();

        // get what hike is selected
        String hikeSelected = hikeComboBox.getSelectedItem().toString();
        int hikeId = Rates.HIKE.valueOf(hikeSelected).ordinal();

        // get what duration is selected
        Integer selectedDuration = Integer.parseInt(hikeRatesComboBox.getSelectedItem().toString());

        // try to parse the user's start date entered
        try {
            String target = startDateValue.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            cal.setTime(sdf.parse(target));
        }
        // invalid date format
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            showErrorDialog("Invalid date format. Error of: " + ex.getMessage());
        } finally {
            try {
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);

                String result = openSocket(hikeId, year, month, day, selectedDuration);
                System.out.println("Result: " + result);

                // split results into [VALUE:MESSAGE]
                String[] parts = result.split(":");

                // if VALUE in return is -0.01 display error
                if (Double.parseDouble(parts[0]) != -0.01) {
                    // print out final total
                    totalCostField.setText(String.format("$%,.2f", Double.parseDouble(parts[0])));
                } else {
                    // display STRING result
                    showErrorDialog(parts[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Open web server socket to get booking rates from
     * @param hike
     * @param year
     * @param month
     * @param day
     * @param duration
     * @return String in format VALUE:MESSAGE
     *          VALUE = -0.01 indicates an error
     *          VALUE != -0.01 for quoted rate
     *          MESSAGE = Error message or "Quoted Rate"
     * @throws IOException
     */
    private String openSocket(int hike, int year, int month, int day, int duration) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String echo = "";
        String result = "";

        // create final string to pass to socket
        String data = hike + ":" + year + ":" + month + ":" + day + ":" + duration;

        // open socket connection to web server
        try {
            echoSocket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: "  + SERVER_HOSTNAME);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + SERVER_HOSTNAME);
            System.exit(1);
        }

        // ensure data string is populated
        while (data != null) {
            out.println(data);
            echo = in.readLine();
            if (echo == null) {
                break;
            } else {
                result += echo;
            }
        }

        // close connections; readers; sockets; buffers
        out.close();
        in.close();
        echoSocket.close();

        return result;
    }
}
