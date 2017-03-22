
Example project with *Spark Streaming* weblog data from Kafka topic to calculate top clicks for a given sliding windows of 5 minutes in real time.

Technology Stack

*SpringBoot*
*Spring Integration*
*Kafka*
*Spark Streaming*


Requirement to run-

Kafka; zookeeper up and running;

create a "weblogs" topic in kafka


1) run the com.example.StreamingKafkaWeblog.scala sparking streaming job to listen to kafka topic 
2) run com.example.KafkaWeblogClickStreamApplication.java (Spring boot application) to have a controller listening to post data of incoming web logs
3) run com.example.WeblogParser.java to simulate some traffic data(loading from clickstreamweblogs.txt) and post it to SpringBoot Controller


***Observe in the the StreamingKafkaWeblog.scala, sparking streaming is calculating top clicks urls over a 5-minute window sliding every second in real time. This is so powerful. FORGET ABOUT ROLLUP!!!!

I'll write more about this when I have more free time, and master these technologies a little better.

In the mean time, keep on learning... it's fun.






