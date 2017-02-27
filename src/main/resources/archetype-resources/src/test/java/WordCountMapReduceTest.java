package ${package};

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

import ${package}.mapper.TokenizerMapper;
import ${package}.reducer.IntSumReducer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordCountMapReduceTest {

    private MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
    private ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
    private MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

    @Before
    public void setUp() {
        final TokenizerMapper mapper = new TokenizerMapper();
        final IntSumReducer reducer = new IntSumReducer();
        mapDriver = MapDriver.newMapDriver(mapper);
        reduceDriver = ReduceDriver.newReduceDriver(reducer);
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }

    @Test
    public void testMapper() throws Exception {
        mapDriver.withAll(Arrays.asList(
            pair(0L, "aa bb aa cc"),
            pair(1L, "aaa bbb ccc bbb")));

        mapDriver.withAllOutput(Arrays.asList(
            pair("aa", 1), pair("bb", 1), pair("aa", 1), pair("cc", 1),
            pair("aaa", 1), pair("bbb", 1), pair("ccc", 1), pair("bbb", 1)));

        mapDriver.runTest();
    }

    @Test
    public void testReducer() throws Exception {
        reduceDriver.withAll(Arrays.asList(
            pair("aa", 1, 1, 1, 3, 1),
            pair("bbb", 1, 2, 1),
            pair("ccc", 1, 1)));

        reduceDriver.withAllOutput(Arrays.asList(
            pair("aa", 7), pair("bbb", 4), pair("ccc", 2)));

        reduceDriver.runTest();
    }

    @Test
    public void testMapReduce() throws Exception {
        mapReduceDriver.withInput(
            pair(0L, "aa bb cc aa dd bb"));

        mapReduceDriver.withAllOutput(Arrays.asList(
            pair("aa", 2), pair("bb", 2), pair("cc", 1), pair("dd", 1)));

        mapReduceDriver.runTest();
    }

    private static Pair<LongWritable, Text> pair(Long key, String value) {
        return new Pair<>(new LongWritable(key), new Text(value));
    }

    private static Pair<Text, IntWritable> pair(String key, Integer value) {
        return new Pair<>(new Text(key), new IntWritable(value));
    }

    private static Pair<Text, List<IntWritable>> pair(String key, Integer... values) {
        List<IntWritable> list = new ArrayList<>();
        for (Integer value : values) {
            list.add(new IntWritable(value));
        }

        return new Pair<>(new Text(key), list);
    }

}
