package fer.rest.examples;

import fer.rest.examples.model.Message;
import fer.rest.examples.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;


@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@QueryParam("year") int year){
        if(year > 0){
            return messageService.getAllMessagesByYear(year);
        }
        else{
            return messageService.getAllMessages();
        }
    }

    @Path("/{messageId}")
    @GET
    public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo){
        Message message = messageService.getMessage(id);
        message.addLink(getUriForSelf(uriInfo, message), "rel");
        message.addLink(getUriForProfile(uriInfo, message), "profiles");
        message.addLink(getUriForComments(uriInfo, message), "comments");

        return message;
    }

    private String getUriForComments(UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentResource")
                .path(CommentsResource.class)
                .resolveTemplate("messageId", message.getId())
                .build()
                .toString();
    }

    private String getUriForProfile(UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build()
                .toString();
    }

    private String getUriForSelf(@Context UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(Long.toString(message.getId()))
                .build()
                .toString();
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        Message responseMsj = messageService.addMessage(message);
        String newId = String.valueOf(responseMsj.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri)
                .entity(responseMsj)
                .build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(Message message, @PathParam("messageId") long id){
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public Message deleteMessage(@PathParam("messageId") long id){
        return messageService.removeMessage(id);
    }

    @Path("/{messageId}/comments")
    public CommentsResource getCommentResource(){
        return new CommentsResource();
    }
}
