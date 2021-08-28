package frontend.pages;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.*;
import frontend.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

public class EventListGUI extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private SimpleListComponent simpleListComponent;
    private ButtonElement buttonElement;
    private GUIController controller;
    private JTextArea textField;

    public EventListGUI() {


        simpleListComponent = SimpleListComponent.builder("SLC")
                .font(new Font("SansSerif", Font.ITALIC,10))
                .selectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION)
                .build();

        buttonElement = ButtonElement.builder("BTN-CREATE-EVENT").buttonText("Event erstellen").build();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonElement);

        textField = new JTextArea("Suchen...");
        textField.setBorder( BorderFactory.createLineBorder(Color.BLACK));
        textField.setMargin(new Insets(10, 10, 10, 10));

        this.setLayout(new BorderLayout());

        this.add(textField, BorderLayout.NORTH);
        this.add(simpleListComponent, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public boolean addObserver(EventListener eventListener) {
        return false;
    }

    @Override
    public boolean removeObserver(EventListener eventListener) {
        return false;
    }

    @Override
    public void processUpdateEvent(UpdateEvent updateEvent) {

    }

    public void setController(GUIController controller) {
        this.controller = controller;
        buttonElement.addObserver(controller);
        simpleListComponent.addObserver(controller);
    }
}