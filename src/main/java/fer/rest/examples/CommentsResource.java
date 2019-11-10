package fer.rest.examples;

import fer.rest.examples.model.Comment;
import fer.rest.examples.service.CommentsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentsResource {

    private CommentsService commentsService = new CommentsService();

    @GET
    public List<Comment> getComments(@PathParam("messageId") long messageId){
        return commentsService.getAllComments(messageId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId") long messageId,
                              @PathParam("commentId") long commentId){
        return commentsService.getComment(messageId, commentId);
    }

    @POST
    public Comment addComment(@PathParam("messageId") long messageId, Comment comment){
        return commentsService.addComment(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") long messageId,
                                 @PathParam("commentId") long commentId,
                                 Comment comment){
        comment.setId(commentId);
        return commentsService.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public Comment deleteComment(@PathParam("messageId") long messageId,
                                 @PathParam("commentId") long commentId){
        return commentsService.removeComment(messageId, commentId);
    }

}
