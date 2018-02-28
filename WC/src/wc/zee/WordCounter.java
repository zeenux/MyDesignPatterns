package wc.zee;


import org.apache.hadoop.io.*;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



public class WordCounter extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		if(args.length<2)
		{
			System.out.println("Usage hadoop jar [JAR FILE] [INPUT 1], [INPUT 2]");
			return -1;
		}
		JobConf conf=new JobConf(WordCounter.class);
		FileInputFormat.setInputPaths(conf,new Path(args[0]));
		FileOutputFormat.setOutputPath(conf,new Path( args[1]));
		
		//Set class for Map and Reduce
		
		conf.setMapperClass(WordMapper.class);
		conf.setReducerClass(WordReducer.class);
		
		//Set Map Key Output Class
		
		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(IntWritable.class);
		
		//Set Key Output Class
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		// Run Job
		JobClient.runJob(conf );
		
		
		return 0;
	}
	public static void main(String [] args) throws Exception {
		int exitCode=ToolRunner.run(new WordCounter(), args);
		System.exit(exitCode);
	}
}
