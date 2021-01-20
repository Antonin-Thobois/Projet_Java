package client;

import common.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextFlow;

import java.io.IOException;


public class ClientPanel extends Parent {
    private TextArea textToSend;
    private ScrollPane scrollReceivedText;
    private TextFlow receivedText;
    private Button sendBtn;
    private Button clearBtn;
    private Client client;

    public ClientPanel() {
        textToSend = new TextArea();
        scrollReceivedText = new ScrollPane();
        receivedText = new TextFlow();
        sendBtn = new Button();
        clearBtn = new Button();


        scrollReceivedText = new ScrollPane();
        scrollReceivedText.setLayoutX(50);
        scrollReceivedText.setLayoutY(50);
        scrollReceivedText.setPrefWidth(400);
        scrollReceivedText.setPrefHeight(350);
        scrollReceivedText.setContent(receivedText);
        scrollReceivedText.vvalueProperty().bind(receivedText.heightProperty());

        receivedText.setPrefWidth(395);

        sendBtn.setLayoutX(370);
        sendBtn.setLayoutY(450);
        sendBtn.setPrefWidth(80);
        sendBtn.setPrefHeight(30);
        sendBtn.setText("Send");
        sendBtn.setVisible(true);
        sendBtn.setOnAction(event -> {
            Message mess = new Message("Moi", textToSend.getText());
            printNewMessage(mess);
            textToSend.setText("");
            try {
                client.sendMessage(mess);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        clearBtn.setLayoutX(370);
        clearBtn.setLayoutY(520);
        clearBtn.setPrefWidth(80);
        clearBtn.setPrefHeight(30);
        clearBtn.setVisible(true);
        clearBtn.setText("Clear");
        clearBtn.setOnAction(event -> textToSend.setText(""));

        receivedText.setVisible(true);

        textToSend.setLayoutX(50);
        textToSend.setLayoutY(450);
        textToSend.setPrefWidth(310);
        textToSend.setPrefHeight(100);
        textToSend.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)  {
                Message mess = new Message("Moi", textToSend.getText());
                printNewMessage(mess);
                textToSend.setText("");
                try {
                    client.sendMessage(mess);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        this.getChildren().add(scrollReceivedText);
        this.getChildren().add(textToSend);
        this.getChildren().add(clearBtn);
        this.getChildren().add(sendBtn);
    }

    public void printNewMessage(Message mess) {
        Platform.runLater(() -> {
            Label text = new Label(mess.toString() + "\n");
            text.setPrefWidth(receivedText.getPrefWidth() - 20);
            text.setAlignment(Pos.CENTER_LEFT);
            receivedText.getChildren().add(text);
        });
    }

    public TextArea getTextToSend() {
        return textToSend;
    }

    public void setTextToSend(TextArea textToSend) {
        this.textToSend = textToSend;
    }

    public ScrollPane getScrollReceivedText() {
        return scrollReceivedText;
    }

    public void setScrollReceivedText(ScrollPane scrollReceivedText) {
        this.scrollReceivedText = scrollReceivedText;
    }

    public TextFlow getReceivedText() {
        return receivedText;
    }

    public void setReceivedText(TextFlow receivedText) {
        this.receivedText = receivedText;
    }

    public Button getSendBtn() {
        return sendBtn;
    }

    public void setSendBtn(Button sendBtn) {
        this.sendBtn = sendBtn;
    }

    public Button getClearBtn() {
        return clearBtn;
    }

    public void setClearBtn(Button clearBtn) {
        this.clearBtn = clearBtn;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
