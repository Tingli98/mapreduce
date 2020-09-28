package com.drkiettran.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extracted from Hadoop for Dummies (2014)
 */
public class FlightsByOriginReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightsByOriginReducer.class);

	@Override
	protected void reduce(Text token, Iterable<IntWritable> counts, Context context)
			throws IOException, InterruptedException {
		int sum = 0;

		for (IntWritable count : counts) {
			sum += count.get();
		}
		context.write(token, new IntWritable(sum));
		LOGGER.info("{}: {}", token, sum);
	}
}