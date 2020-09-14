package com.sample;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.*;

import java.io.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        execute();
    }

    public static void execute()
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        StringWriter writer = new StringWriter();

        try {
            HttpPut putRequest = new HttpPut("https://maven.pkg.github.com/catto-ent1/test/com/github/egg/1.2.3/maven-assembly-example-0.1.zip");
            putRequest.addHeader("content-type", "application/octet-stream");
            RandomAccessFile f = new RandomAccessFile("tmp", "rw");
            // create 100MB file for testing
            f.setLength(1024 * 1024 * 100);

            File file = new File("tmp");
            FileEntity entity = new FileEntity(file);
            putRequest.setEntity(entity);

            System.out.println("Sending PUT request...");
            HttpResponse response = httpClient.execute(putRequest);

            //verify the valid error code first
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Status code: " + String.valueOf(statusCode));
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            //Important: Close the connect
            httpClient.getConnectionManager().shutdown();
        }
    }
}
