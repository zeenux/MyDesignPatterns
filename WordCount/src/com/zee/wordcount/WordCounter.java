package com.zee.wordcount;

import java.io.IOException;
import java.util.*;
        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WordCounter {
public static void main(String [] args) throws Exception {
	Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "word count");
    job.setJarByClass(WordCounter.class);
    job.setMapperClass(WordMapper.class);
    job.setCombinerClass(WordReducer.class);
    job.setReducerClass(WordReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    double rnd=Math.random();
    FileInputFormat.addInputPath(job, new Path("/home/zeenux/adult.txt"));
    FileOutputFormat.setOutputPath(job, new Path("/home/zeenux/oop_t/DATA_"+rnd));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
}
}
