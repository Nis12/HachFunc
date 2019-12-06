package MainViewController;

import Services.GetHashFunctionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainViewController extends JFrame {
    private JEditorPane hashFuncEditorPane;
    private JTextField hashFuncTextField;
    private JButton clearButton;
    private JButton getHashFuncButton;
    private JLabel encodeMessage;
    private JLabel hashLabel;
    private JPanel mainPanel;
    private JButton compareButton;

    private GetHashFunctionService getHashFunctionService = new GetHashFunctionService();

    public MainViewController() {

        customUI();
        getHashFuncButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getHashFuncButtonPressed();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearButtonPressed();
            }
        });

        compareButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToCompareSite();
            }
        });
    }

    private void customUI() {
        setContentPane(mainPanel);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - 700) / 2);
        int y = (int) ((dimension.getHeight() - 170) / 2);
        this.setLocation(x, y);
        setSize(700,170);
        localizeText();
        setVisible(true);
    }

    private void goToCompareSite() {
        try {
            Desktop d = Desktop.getDesktop();
            d.browse(new URI("http://www.sha1-online.com"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void localizeText() {
        this.setTitle("Реализация SHA-1");
        clearButton.setText("Очистить");
        getHashFuncButton.setText("Получить хеш функцию");
        compareButton.setText("Сайт SHA-1");
        encodeMessage.setText("Исходное сообщение:");
        hashLabel.setText("Хеш:");
    }

    private void getHashFuncButtonPressed() {
        if (hashFuncEditorPane.getText().trim().equals("")) {
            String message = "Hello World";
            hashFuncEditorPane.setText(message);
            hashFuncTextField.setText(getHashFunctionService.getHashFunction(message));
        } else {
            String message = hashFuncEditorPane.getText();
            hashFuncTextField.setText(getHashFunctionService.getHashFunction(message));
        }
    }

    private void clearButtonPressed() {
        hashFuncEditorPane.setText("");
        hashFuncTextField.setText("");
    }
}

