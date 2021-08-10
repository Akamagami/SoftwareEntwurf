package frontend;

import backend.event.Event;

public class GUIEvent extends java.util.EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */

    private EventCommand cmd;
    private Object data;


    public GUIEvent(Object source) {
        super(source);
    }

    public GUIEvent(Object source, EventCommand cmd, Object data) {
        super(source);
        this.cmd = cmd;
        this.data = data;
    }

    public EventCommand getCmd() {
        return this.cmd;
    }

    public void setCmd(EventCommand cmd) {
        this.cmd = cmd;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
