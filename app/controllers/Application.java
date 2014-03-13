package controllers;

import play.libs.EventSource;
import play.libs.F;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.WebSocket;

import static java.util.concurrent.TimeUnit.SECONDS;
import static play.libs.EventSource.Event.event;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render("Hello Play Framework"));
    }

    public static Result syncFoo() {
        return ok("sync foo");
    }

    public static F.Promise<Result> asyncFoo() {
        return F.Promise.promise(() -> ok("async foo"));
    }

    public static F.Promise<Result> asyncNonBlockingFoo() {
        return F.Promise.delayed(() -> ok("async non-blocking foo"), 5, SECONDS);
    }

    public static F.Promise<String> getPage(String url) {
        return WS.url(url).get().map(WSResponse::getBody);
    }

    public static F.Promise<Result> reactiveRequest() {
        return getPage("http://www.typesafe.com").map(Results::ok);
    }

    public static F.Promise<Result> reactiveComposition() {
        return getPage("http://www.twitter.com").flatMap(twitter ->
                   getPage("http://www.typesafe.com").map(typesafe ->
                       ok(twitter + typesafe)));
    }

    public static Result events() {
        return ok(EventSource.whenConnected(es -> es.send(event("hello"))));
    }

    public static WebSocket<String> echo() {
        return WebSocket.whenReady((in, out) -> in.onMessage(out::write));
    }

}
