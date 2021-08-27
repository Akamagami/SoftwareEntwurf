package frontend.pages;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.*;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.Person;
import frontend.controller.GUIController;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class EventDetailsKosten extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private SimpleListComponent simpleListComponent;
    private ButtonElement button1;
    private GUIController controller;
    private ButtonElement returnButton;


    public EventDetailsKosten() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        List<IDepictable> elems = new ArrayList<>();
        elems.add(new Person("Willi", "Bald"));
        elems.add(new Person("ASDASD", "ASDASDASDA"));
        elems.add(new Person("JKL", "FGH"));
        elems.add(new Person("RTZ", "HJ;K"));
        elems.add(new Person("DF", "ILÖ"));
        elems.add(new Person("CVBVB", "HJKIUO"));

        simpleListComponent = SimpleListComponent.builder("SLC")
                                .font( new Font("SansSerif", Font.ITALIC,10))
                                .selectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION).build();
        simpleListComponent.setListElements(elems);
        simpleListComponent.setVisible(true);

        button1 = ButtonElement.builder("EDK-BTN").buttonText("Auswählen").type(ButtonElement.Type.BUTTON).build();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button1);

        this.add(simpleListComponent, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);



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
        if (updateEvent.getCmd().getCmdText() == "CHANGE_BACKGROUND_COLOR") {
            this.setBackground(Color.BLUE);
            JOptionPane.showMessageDialog(null, "Backgroundcolor changed to blue");
        }
    }

    public GUIController getController() {
        return controller;
    }

    public void setController(GUIController controller) {
        this.controller = controller;
        button1.addObserver(controller);
    }

    public static enum Commands implements EventCommand {
        CHANGE_BACKGROUND_COLOR("CHANGE_BACKGROUND_COLOR", ButtonElement.class);

        public final Class<?> payloadType;
        public final String cmdText;

        private Commands(String cmdText, Class<?> payloadType) {
            this.cmdText = cmdText;
            this.payloadType = payloadType;
        }

        public String getCmdText() {
            return this.cmdText;
        }

        public Class<?> getPayloadType() {
            return this.payloadType;
        }
    }
}
