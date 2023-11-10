/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import dao.OrderDao;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import model.json.WSRequest;

/**
 *
 * @author Dac Dat
 */
@ServerEndpoint("/notification")
public class NotificationEndpoint {
    static Set<Session> users = Collections.synchronizedSet(new HashSet<>());
    @OnOpen
    public void onOpen(Session session) {
        users.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try{
            Gson gson = new Gson();
            OrderDao dao = new OrderDao();
            System.out.println(message);
            WSRequest wsr = gson.fromJson(message, WSRequest.class);
            System.out.println(wsr);
            if (wsr.getAction().equals("order-toast")) {
                System.out.println(wsr.getUserID());
                String orderToast = gson.toJson(dao.getOrderToastInfo(wsr.getUserID()));
                for (Session s : users) {
                    s.getBasicRemote().sendText(orderToast);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        users.remove(session);
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }
}
