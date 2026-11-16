package in.ai.chat.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getChatReply(String userMessage) {
        String reply = chatClient.prompt()
                .user(userMessage)
                .call()
                .content();

        return reply == null
                ? ""
                : reply.replaceAll("(?s)<think>.*?</think>", "").trim();
    }
}