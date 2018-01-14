package netconsole2;

import java.nio.charset.StandardCharsets;
import java.nio.ByteBuffer;
import java.util.Formatter;
import java.util.Locale;

public class PrintMessage implements Message {
  public final float timestamp;
  public final int seqNumber;
  public final String line;

  public PrintMessage(ByteBuffer data) {
    data.rewind();
    timestamp = data.getFloat();
    seqNumber = data.getShort() & 0xffff;
    line = StandardCharsets.UTF_8.decode(data).toString();
    if(line.indexOf("Robot program successfully initialized!") > -1) {
    	System.exit(0);
    } else if(line.indexOf("Robots should not quit, but yours did!") > -1) {
    	System.exit(1);
    }
  }

  public Type getType() {
    return Type.kPrint;
  }

  public void render(StyledAppendable output, RenderOptions options) {
    // timestamp
    if (options.showTimestamp) {
      output.startStyle(kStyleTimestamp);
      Formatter formatter = new Formatter(output, Locale.US);
      formatter.format(options.timestampFormat, timestamp);
      output.endStyle();
      output.append('\t');
      output = new IndentAppendable(output, "\t\t");
    }

    // line
    output.startStyle(kStylePrint);
    output.append(line);
    output.endStyle();
  }
}
