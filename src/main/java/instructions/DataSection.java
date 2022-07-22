package instructions;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataSection {

  /* A class representing the data section which is at the beginning of the assembly file. */

  /* A list of all the message labels.
     (We don't need to worry about duplication here since it is handled in the Label class.) */
  private final Map<String, MessageLabel> messageLabels;

  public DataSection() {
    this.messageLabels = new LinkedHashMap<>();
  }

  public void addMessageLabels(MessageLabel messageLabel) {
    // Make sure there is no duplicate message label in the data section.
    if (!messageLabels.containsValue(messageLabel)) {
      messageLabels.put(messageLabel.getMessage(), messageLabel);
    }
  }

  public int getNumOfMessages() {
    return messageLabels.size();
  }

  public Label getMessageLabel(String labelName) {
    return messageLabels.get(labelName);
  }

  @Override
  public String toString() {
    if (messageLabels.isEmpty()) {
      return "";
    }
    StringBuilder stringBuilder = new StringBuilder(".data");
    stringBuilder.append("\n\n");
    for (Label label : messageLabels.values()) {
      stringBuilder.append(label);
    }
    stringBuilder.append("\n");
    return stringBuilder.toString();
  }
}