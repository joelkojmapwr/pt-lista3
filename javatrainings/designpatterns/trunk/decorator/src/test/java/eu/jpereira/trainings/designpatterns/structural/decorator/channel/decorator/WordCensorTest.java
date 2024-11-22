package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import static org.junit.Assert.assertEquals;

import java.lang.annotation.Target;

import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator.MessageTruncator;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator.URLAppender;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;


public class WordCensorTest extends AbstractSocialChanneldDecoratorTest {
    
    @Test 
    public void testCensor() {
        SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

		// create a spy social channel
		SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
		SocialChannel channel = builder.with(new WordCensor("Microsoft")).getDecoratedChannel(props);

		// Now call channel.deliverMessage
		channel.deliverMessage("Microsoft Windows is bad!");
		
		// Spy channel. Should get the one created before
		TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
		assertEquals("### Windows is bad!", spyChannel.lastMessagePublished());	
    }

    @Test 
    public void ChainCensorDecoratorTest() {
        SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

		// create a spy social channel
		SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
		builder.with(new URLAppender("google.com"));
        builder.with(new WordCensor("Microsoft"));
        builder.with(new MessageTruncator(15));
        builder.with(new WordCensor("ows"));
        

        SocialChannel channel = builder.getDecoratedChannel(props);
		// Now call channel.deliverMessage
		channel.deliverMessage("Microsoft Windows is bad!");
		
		// Spy channel. Should get the one created before
		TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
        System.out.println(spyChannel.lastMessagePublished());
		assertEquals("### Wind### ...", spyChannel.lastMessagePublished());	
    }

}
