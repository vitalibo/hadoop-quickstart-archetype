package ${package}.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class TokenizerMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final static IntWritable ONE = new IntWritable(1);
    private final Text word = new Text();

    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        StringTokenizer iterator = new StringTokenizer(String.valueOf(value));

        while (iterator.hasMoreTokens()) {
            word.set(iterator.nextToken());
            context.write(word, ONE);
        }
    }

}
