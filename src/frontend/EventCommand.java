package frontend;

public interface EventCommand {
    String getCommandText();
    Class<?> getPayloadType();
}
