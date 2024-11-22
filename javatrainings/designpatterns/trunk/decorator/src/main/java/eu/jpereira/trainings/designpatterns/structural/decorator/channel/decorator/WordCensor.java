package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator.SocialChannelDecorator;

public class WordCensor extends SocialChannelDecorator {
    private String word;

    public WordCensor(String word) {
        this.word = word;
    }

    @Override public void deliverMessage(String message) {
        message = message.replaceAll(word, "###");
        delegate.deliverMessage(message);
    }
}
