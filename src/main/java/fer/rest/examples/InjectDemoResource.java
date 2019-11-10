package fer.rest.examples;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

    @GET
    @Path("annotations")
    public String getParamUsingAnnotations(@MatrixParam("matrixParam") String matrixParam,
                                           @HeaderParam("headerParam") String headerParam,
                                           @CookieParam("name") String cookie){
        return "matrix param: " +matrixParam + " header param: " + headerParam + " cookie param: " +cookie ;
    }

    @GET
    @Path("context")
    public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
        System.out.println("absolute path: "+ uriInfo.getAbsolutePath());
        System.out.println(headers.getCookies().values());
        return "test";
    }
}
