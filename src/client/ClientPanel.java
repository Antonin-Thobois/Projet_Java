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
import jeu.Jeu;
import server.Server;

import java.io.IOException;


public class ClientPanel extends Parent {
    private TextArea textToSend;
    private ScrollPane scrollReceivedText;
    private TextFlow receivedText;
    private Button sendBtn;
    private Button clearBtn;
    private Button jouerBtn;
    private Client client;
    private Server sv;

    public ClientPanel() {
        textToSend = new TextArea();
        scrollReceivedText = new ScrollPane();
        receivedText = new TextFlow();
        sendBtn = new Button();
        clearBtn = new Button();
        jouerBtn = new Button();


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
        sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Message mess = new Message("Moi", textToSend.getText());
                printNewMessage(mess);
                textToSend.setText("");
            }
        });

        clearBtn.setLayoutX(370);
        clearBtn.setLayoutY(485);
        clearBtn.setPrefWidth(80);
        clearBtn.setPrefHeight(30);
        clearBtn.setVisible(true);
        clearBtn.setText("Clear");
        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSend.setText("");
            }
        });

        jouerBtn.setLayoutX(370);
        jouerBtn.setLayoutY(520);
        jouerBtn.setPrefWidth(80);
        jouerBtn.setPrefHeight(30);
        jouerBtn.setVisible(true);
        jouerBtn.setText("Jouer");
        jouerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Server.jouer(client);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        receivedText.setVisible(true);

        textToSend.setLayoutX(50);
        textToSend.setLayoutY(450);
        textToSend.setPrefWidth(310);
        textToSend.setPrefHeight(100);
        textToSend.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
                    Message mess = new Message("Moi", textToSend.getText());
                    printNewMessage(mess);
                    textToSend.setText("");
                }
            }
        });

        this.getChildren().add(scrollReceivedText);
        this.getChildren().add(textToSend);
        this.getChildren().add(clearBtn);
        this.getChildren().add(sendBtn);
        this.getChildren().add(jouerBtn);
    }

    public void printNewMessage(Message mess) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label text = new Label(mess.toString() + "\n");
                text.setPrefWidth(receivedText.getPrefWidth() - 20);
                text.setAlignment(Pos.CENTER_LEFT);
                receivedText.getChildren().add(text);
            }
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
