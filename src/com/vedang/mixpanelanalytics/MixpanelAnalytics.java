package com.vedang.mixpanelanalytics;

import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.YailDictionary;

import com.mixpanel.mixpanelapi.ClientDelivery;
import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MixpanelAnalytics extends AndroidNonvisibleComponent {

  public MixpanelAnalytics(ComponentContainer container) {
    super(container.$form());
  }
  MessageBuilder messageBuilder;

  @SimpleFunction
  public void Initialize(String projectToken){
    messageBuilder = new MessageBuilder(projectToken);
  }

  @SimpleFunction
  public void SendEvent(String eventName, YailDictionary properties, String distinctId) {
    AsynchUtil.runAsynchronously(new Runnable() {
      @Override
      public void run() {
        String dict = properties.toString();
        JSONObject event = null;
        try {
          event = messageBuilder.event(distinctId, eventName, new JSONObject(dict));
        } catch (JSONException e) {
          e.printStackTrace();
        }
        ClientDelivery delivery = new ClientDelivery();
        delivery.addMessage(event);
        MixpanelAPI mixpanelAPI = new MixpanelAPI();
        try {
          mixpanelAPI.deliver(delivery);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

  }
}
