/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator;

import com.barcap.simulator.fix.FixMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import socketnio.client.util.FixMessageCreator;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class RegexpTest {

    @Test
    @Ignore
    public void testRegexp() throws Exception {
        String regexp = "abc";

        Pattern pattern = Pattern.compile(regexp);

        String candidateString = "abcabcde";

        Matcher matcher = pattern.matcher(candidateString);

        List<String> messages = new ArrayList<String>();

        int endPos = 0;

        while(matcher.find()){
            messages.add(matcher.group());
            endPos = matcher.end();
        }

        String remainingBuffer = candidateString.substring(endPos);
        System.err.println("remainingBuff is:" + remainingBuffer);
    }

    @Test
    @Ignore
    public void testRegexpFix() throws Exception {
        String msg = FixMessageCreator.createFixMessage();
        System.err.println(msg);
        String msg2 = FixMessageCreator.createFixMessage();
        //String fixPattern = "^8=Fix*\\w*10=";
        String fixPattern = "\\D8=.*\\D10=...";

        Pattern pattern = Pattern.compile(fixPattern);

        String testString = msg + msg2;

        
        Matcher matcher = pattern.matcher(testString);

        System.err.println("testing.." + testString);

        int count = 0;
        while(matcher.find()){
            System.err.println(matcher.group());
            count++;
        }

        System.err.println("found " + count + " occurrences");


        Assert.assertEquals(2, count);
        //System.err.println("fix msg is:" + msg);

    }

    @Test
    @Ignore
    public void testRegexpPhrase() throws Exception {
        String msg = FixMessageCreator.createFixMessage();
        String msg2 =  FixMessageCreator.createOrderMessage();
                //FixMessageCreator.createFixMessage();
        String fixPattern = //".*?" +
                "8=Fix.*?\\D10=\\d{3}";
        Pattern pattern = Pattern.compile(fixPattern);
        String candidateStr = msg + msg2;
        //candidateStr = candidateStr.replaceAll("\001", ";");

        
        System.err.println("attempting to match:" + candidateStr);
        Matcher matcher = pattern.matcher(candidateStr);
        int count = 0;
        int endPos = 0;

        while(matcher.find()){
            System.err.println("Group found is:" + matcher.group());
            count++;
            endPos = matcher.regionEnd();

        }

        System.err.println("sTRING WAS LONG" + candidateStr.length());

        System.err.println("end char is:" + endPos);

        Assert.assertEquals(2, count);
        
    }

    @Test
    @Ignore
    public void testRegexpPhraseWithHalfMsg() throws Exception {
        String msg = "xfooxxx\nxxxfoo";
        String greedyPattern = ".*foo"; // no. EXACTLY like my fix pattern
        String reluctantPattern = ".*?f.*o"; // ok
        String possessivePattern = ".*+foo"; // not to be used

        Pattern pattern = Pattern.compile(reluctantPattern);
        Matcher matcher = pattern.matcher(msg);
        int count = 0;
        
        while(matcher.find()){
            System.err.println("Group:" + matcher.group());
            count++;
        }

        Assert.assertEquals(2, count);
        
    }

    @Test
    public void testRegexpPhraseWithHalfFixMsg() throws Exception {
        String msg = FixMessageCreator.createFixMessage();
        String msg2 =  FixMessageCreator.createOrderMessage();
                //FixMessageCreator.createFixMessage();
        String fixPattern = //".*?" +
                "8=Fix.*?\\D10=\\d{3}";
        Pattern pattern = Pattern.compile(fixPattern);
        int randomIdx = 10;

        String candidateStr = msg +  msg2.substring(0, randomIdx);

        System.err.println("attempting to match:" + candidateStr);
        Matcher matcher = pattern.matcher(candidateStr);
        int count = 0;
        int endPos = 0;


        StringBuffer tailBuffer = new StringBuffer();

        while(matcher.find()){
            System.err.println("Group found is:" + matcher.group());
            count++;
            endPos = matcher.end();

        }
        System.err.println("end pos is:" + endPos);

        System.err.println("still to parse:" + candidateStr.substring(endPos+1));

        String tst = candidateStr.substring(endPos+1);


        Assert.assertEquals(1, count);

        tst += msg2.substring(randomIdx);
        count = 0;
        matcher = pattern.matcher(tst);
        System.err.println("parsing:" + tst);
        while(matcher.find()){
            System.err.println("Group found is:" + matcher.group());
            count++;
            endPos = matcher.end();

        }
        Assert.assertEquals(1, count);


    }



    

}
