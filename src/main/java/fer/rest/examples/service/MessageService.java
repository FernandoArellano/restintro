package fer.rest.examples.service;

import fer.rest.examples.database.Database;
import fer.rest.examples.exceptions.DataNotFoundException;
import fer.rest.examples.model.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class MessageService {

    private Map<Long, Message> messages = Database.getMessages();

    public List<Message> getAllMessages(){
        return new ArrayList<>(messages.values());
    }

    public List<Message> getAllMessagesByYear(int year){
        List<Message> messagesByYear = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for(Message message : messages.values()){
            cal.setTime(message.getCreated());
            if(cal.get(Calendar.YEAR) == year){
                messagesByYear.add(message);
            }
        }
        return messagesByYear;
    }

    public Message getMessage(long id){
        Message message = messages.get(id);
        if(null == message){
            throw new DataNotFoundException("Message id not found: " + id);
        }
        return  message;
    }

    public Message addMessage(Message message){
        message.setId(messages.size()+1);
        messages.put(message.getId(), message);
        return  message;
    }

    public Message updateMessage(Message message){
        if(message.getId() <= 0){
            return null;
        }
        return messages.put(message.getId(), message);
    }

    public Message removeMessage(long id){
        return messages.remove(id);
    }
}
