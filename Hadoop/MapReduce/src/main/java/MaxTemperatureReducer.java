import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/********************************/
/* Reducer for finding the maximum temperature for each
/* year. The input parameter 'key', represents the year
/* and the input 'values' contains an array of temperatures
/* from that year. It simply finds the maximum temperature for
/* that year, and writes the year as the key, and that temperature
/* as the value
/*********************************/

public class MaxTemperatureReducer
  extends Reducer<Text, IntWritable, Text, IntWritable> {

  @Override
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {

    int maxValue = Integer.MIN_VALUE;
    for (IntWritable value : values) {
      maxValue = Math.max(maxValue, value.get());
    }
    context.write(key, new IntWritable(maxValue));
  }
}
