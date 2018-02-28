package com.zee.wordcount;
import java.io.IOException;
import java.util.*;
        
import org.apache.hadoop.fs.Path;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import mrdp.utils.MRDPUtils;
public class WordMapper extends Mapper<Object, Text, Text, IntWritable>  {
	private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    private static int WORD_COUNT=0;
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
    	
    	
    	Map<String,String>parsed=MRDPUtils.transformXmlToMap(value.toString());
    	String txt = parsed.get("Text");
    	if (txt == null) {
    		// skip this record
    		return;
    		}
    	txt = StringEscapeUtils.unescapeHtml(txt.toLowerCase());
    	
    	txt = txt.replaceAll("'", ""); // remove single quotes (e.g., can't)
    	txt = txt.replaceAll("[^a-zA-Z]", " "); // replace the rest with a space
    	System.out.println(value.toString());
    	if(value.toString().contains("Bachelors"))
    	{
    		WORD_COUNT++;
    		StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
            System.out.println("Found Word "+WORD_COUNT);
           
    	}
    	else {
    		System.out.println("No Data Found");
    	}
    	
        
    }
}
