package org.example;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import com.azure.ai.openai.models.CompletionsUsage;
import com.azure.core.credential.AzureKeyCredential;

import java.util.ArrayList;
import java.util.List;

public class GetChatCompletionsSample {

    public static void main(String[] args) {
        String azureOpenaiKey = "8e81bb9a18c24ca58fb45d8166455182";
        String endpoint = "https://eus219700.openai.azure.com/";
        String deploymentOrModelId = "test_gpt3_5_turbo";

        OpenAIClient client = new OpenAIClientBuilder()
                .endpoint(endpoint)
                .credential(new AzureKeyCredential(azureOpenaiKey))
                .buildClient();

        List<ChatMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatMessage(ChatRole.SYSTEM, "You are a helpful assistant"));
        chatMessages.add(new ChatMessage(ChatRole.USER, "Does Azure OpenAI support customer managed keys?"));
        chatMessages.add(new ChatMessage(ChatRole.ASSISTANT, "Yes, customer managed keys are supported by Azure OpenAI?"));
        chatMessages.add(new ChatMessage(ChatRole.USER, "Do other Azure AI services support this too?"));

        ChatCompletions chatCompletions = client.getChatCompletions(deploymentOrModelId, new ChatCompletionsOptions(chatMessages));

        System.out.printf("Model ID=%s is created at %s.%n", chatCompletions.getId(), chatCompletions.getCreatedAt());
        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatMessage message = choice.getMessage();
            System.out.printf("Index: %d, Chat Role: %s.%n", choice.getIndex(), message.getRole());
            System.out.println("Message:");
            System.out.println(message.getContent());
        }

        System.out.println();
        CompletionsUsage usage = chatCompletions.getUsage();
        System.out.printf("Usage: number of prompt token is %d, "
                        + "number of completion token is %d, and number of total tokens in request and response is %d.%n",
                usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens());
    }
}