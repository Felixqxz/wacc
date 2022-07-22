import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import instructions.DataSection;
import instructions.Label;
import instructions.MessageLabel;
import org.junit.Test;

public class LabelTest {

  @Test
  public void duplicatedMessageLabelsNotProduced() {
    DataSection dataSection = new DataSection();
    MessageLabel label1 = new MessageLabel("Please input an integer: ");
    MessageLabel label2 = new MessageLabel("Please input an integer: ");
    dataSection.addMessageLabels(label1);
    dataSection.addMessageLabels(label2);
    assertEquals(1, dataSection.getNumOfMessages());
  }

  @Test
  public void slashNotCountIntoWordLength() {
    MessageLabel label = new MessageLabel("\\0");
    String expected = "msg_0:\n"
            + "\t\t.word 1\n"
            + "\t\t.ascii \"\\0\""
            + "\n";
    assertEquals(expected, label.toString());
  }

}