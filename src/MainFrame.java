import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainFrame {

    private Frame frame;

    private GridBagConstraints gbc;

    private JLabel selectImageLabel;

    private JButton selectImageButton;

    private JTextField selectImageField;

    private JLabel saveImageLabel;

    private JTextField saveImageField;

    private JButton saveImageButton;

    private JButton generateCipher;

    private JPanel imagePanel;

    private JLabel imageLabel;

    private JComboBox numberBox;

    private JLabel numberOfSecretImages;

    private JSlider thresholdSlider;

    private JLabel thresholdLabel;

    private JTextArea logMessages;

    private JScrollPane logScrollPane;



    private Dimension boundary;

    private BufferedImage bufferedImage;

    private BufferedImage lastInstance;

    public MainFrame(Frame frame) {
        this.frame = frame;
        initializeObjects();
        createLocation();
    }


    private void initializeObjects() {
        selectImageLabel = new JLabel("Open image: ");
        selectImageField = new JTextField(80);
        selectImageButton = new JButton("Browse");

        String[] numbers = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
                "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60",
                "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
                "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"};

        numberBox = new JComboBox(numbers);

        generateCipher = new JButton("Generate encrypted images");

        saveImageLabel = new JLabel("Save encrypted images: ");
        saveImageField = new JTextField(80);
        saveImageButton = new JButton("Browse");

        imageLabel = new JLabel();
        imagePanel = new JPanel();

        thresholdSlider = new JSlider(JSlider.VERTICAL);
        thresholdSlider.setMinimum(0);
        thresholdSlider.setMaximum(255);
        thresholdSlider.setValue(128);

        numberOfSecretImages = new JLabel("Number of secret images");

        thresholdLabel = new JLabel("Threshold");

        logMessages = new JTextArea(100, 100);
        logMessages.setFont(new Font(logMessages.getFont().getFontName(), logMessages.getFont().getStyle(), logMessages.getFont().getSize() + 2));
        logMessages.append(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ": VisCry opened\n");
        logScrollPane = new JScrollPane(logMessages);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);

        boundary = new Dimension(700, 400);

    }

    private void createLocation() {

        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.5;
        gbc.weighty = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(selectImageLabel, gbc);


        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 2;
        gbc.weighty = 2;
        gbc.gridx = 1;
        gbc.gridy = 0;
        selectImageField.addKeyListener(new SelectFileListener());
        frame.add(selectImageField, gbc);


        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 0;
        selectImageButton.addActionListener(new SelectButtonListener());
        frame.add(selectImageButton, gbc);


        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(numberOfSecretImages, gbc);


        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 0;
        gbc.weighty = 0.2;
        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(numberBox, gbc);


        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(saveImageLabel, gbc);


        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 5;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 4;
        saveImageField.addKeyListener(new SelectFileListener());
        frame.add(saveImageField, gbc);


        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 4;
        saveImageButton.addActionListener(new SelectButtonListener());
        frame.add(saveImageButton, gbc);


        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 6;
        generateCipher.addActionListener(new SelectButtonListener());
        frame.add(generateCipher, gbc);


        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 0.5;
        gbc.weighty = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        thresholdSlider.addChangeListener(new ThresholdListener());
        frame.add(thresholdSlider, gbc);


        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(thresholdLabel, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 5;
        gbc.gridx = 1;
        gbc.gridy = 5;
        frame.add(logScrollPane, gbc);

        frame.setVisible(true);
    }

    private static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int new_width = imgSize.width;
        int new_height = imgSize.height;

        if (imgSize.width > boundary.width) {
            new_width = boundary.width;
            new_height = (new_width * imgSize.height) / imgSize.width;
        }

        if (new_height > boundary.height) {
            new_height = boundary.height;
            new_width = (new_height * imgSize.width) / imgSize.height;
        }
        return new Dimension(new_width, new_height);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////                Select File Listener
    private class SelectFileListener implements KeyListener {

        boolean isPressed = false;

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER && !isPressed && e.getSource().equals(selectImageField)) {
                selectImage(selectImageField.getText());
                isPressed = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            isPressed = false;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////                   Threshold Listener
    private class ThresholdListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            if(lastInstance != null) {
                BufferedImage grayImage = GenerateImage.thresholdImage(bufferedImage, thresholdSlider.getValue());

                Dimension d = new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight());
                Dimension scaled = getScaledDimension(d, boundary);
                Image img = grayImage.getScaledInstance(scaled.width, scaled.height, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
                imageLabel.setPreferredSize(scaled);
                imagePanel.add(imageLabel);
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.fill = GridBagConstraints.NONE;
                gbc.weightx = 0;
                gbc.weighty = 2;
                gbc.gridx = 1;
                gbc.gridy = 1;
                frame.add(imagePanel, gbc);
                frame.validate();
                lastInstance = grayImage;
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////                   Select Button Listener
    private class SelectButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(selectImageButton)){
                FileDialog fileDialog = new FileDialog(frame, "Select image");
                fileDialog.setVisible(true);
                if(fileDialog.getFile() != null && fileDialog.getDirectory() != null){
                    selectImage(fileDialog.getDirectory() + fileDialog.getFile());
                }
            }


            if(e.getSource().equals(saveImageButton)){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                    saveImageField.setText(fileChooser.getSelectedFile().toString());
                }
            }


            if(e.getSource().equals(generateCipher)){
                try {
                    File file = new File(saveImageField.getText());
                    if(file.isDirectory()){
                        int width = lastInstance.getWidth();
                        int height = lastInstance.getHeight();

                        BufferedImage lastKey = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

                        int number = Integer.valueOf(numberBox.getSelectedItem().toString());
                        boolean isEven = (number % 2 == 0);

                        for (int i = 0; i < number - 1; i++) {
                            BufferedImage temp = GenerateImage.getRandomizedImage(width, height);
                            ImageIO.write(temp, "png", new File(saveImageField.getText() + "\\VisCry " + (i + 1) + ".png"));
                            lastKey = GenerateImage.getExcludedImage(lastKey, temp, isEven);

                            logMessages.append(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ": Image \"VisCry " + (i + 1) + ".png\" is saved\n");
                        }
                        lastKey = GenerateImage.getExcludedImage(lastKey, lastInstance, isEven);
                        ImageIO.write(lastKey, "png", new File(saveImageField.getText() + "\\VisCry " + (number) + ".png"));

                        logMessages.append(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ": Image \"VisCry " + number + ".png\" is saved\n");
                        logMessages.append("\n=========================ENCRYPTING IS DONE SUCCESSFULLY!============================\n");
                    }
                    else {
                        logMessages.append(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ": Directory \"" + saveImageField.getText() + "\" doesn't exist\n");
                    }
                }
                catch (Exception ex){
                    logMessages.append(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ": Couldn't save images\n");
                }
            }
        }
    }


    private void selectImage(String filePathAndName){
        try {
            selectImageField.setText(filePathAndName);

            bufferedImage = ImageIO.read(new File(filePathAndName));

            BufferedImage grayImage = GenerateImage.thresholdImage(bufferedImage, thresholdSlider.getValue());

            Dimension d = new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight());
            Dimension scaled = getScaledDimension(d, boundary);
            Image img = grayImage.getScaledInstance(scaled.width, scaled.height, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
            imageLabel.setPreferredSize(scaled);
            imagePanel.add(imageLabel);
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            gbc.weighty = 0;
            gbc.gridx = 1;
            gbc.gridy = 1;
            frame.add(imagePanel, gbc);
            frame.validate();
            lastInstance = grayImage;
            logMessages.append(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ": Image loaded\n");

        }catch (Exception ex){
            logMessages.append(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ": File is not supported. Please, use images with extension: jpg, bmp, png\n");
        }
    }
}