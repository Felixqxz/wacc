package instructions;

import java.util.HashSet;
import java.util.Set;

public class MessageLabel extends Label {

    /* Message labels are for labels in the data section for storing strings. */

    /* Counts how many messages are in the data section. */
    private static int messageCount = 0;

    /* A static set for storing all messages labels created, to make sure there is no duplicated
     * message labels. */
    private static final Set<String> setOfMessages = new HashSet<>();

    /* The actual string for this message label. */
    private final String message;

    public MessageLabel(String message) {
        super("msg_" + messageCount);
        this.message = message;
        // Make sure there is no duplication of message labels.
        if (!setOfMessages.contains(message)) {
            setOfMessages.add(message);
            messageCount++;
        }
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MessageLabel)) {
            return false;
        }
        MessageLabel that = (MessageLabel) obj;
        return this.message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return message.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(label + ":");
        int messageLength = message.length();

        // Make sure "\" doesn't count into the word length.
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == '\\') {
                messageLength--;
            }
        }

        stringBuilder.append("\n\t\t")
                .append(".word ")
                .append(messageLength)
                .append("\n\t\t")
                .append(".ascii ")
                .append("\"")
                .append(message)
                .append("\"")
                .append("\n");
        return stringBuilder.toString();
    }
}